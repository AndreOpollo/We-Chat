package com.example.wechat.features.home.data.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.chat.data.repository.getChatRoomId
import com.example.wechat.features.home.domain.repository.HomeRepository
import com.example.wechat.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
):HomeRepository {
    override suspend fun getAllUsers(): Flow<Result<List<User>>> {
        return flow{
            try {
                val currentUserId = firebaseAuth.currentUser?.uid
                val snapshot = firebaseFirestore.collection("users").get().await()
                val users = snapshot.toObjects<User>().filter { it.id!=currentUserId }
                emit(Result.Success(users))
            }catch (e:Exception){
                emit(Result.Failure(e.localizedMessage?:"Unknown error"))
            }
        }
    }

    override suspend fun getAllUsersWithActiveChats(): Flow<Result<List<User>>> {
        return flow {
            try {
                emit(Result.Loading)
                val currentUserId = firebaseAuth.currentUser?.uid
                val usersSnapshot = firebaseFirestore
                    .collection("users")
                    .get()
                    .await()
                val users = usersSnapshot.toObjects<User>().filter { it.id!=currentUserId }

                val usersWithMessages = users.map { user->
                    val chatroomId = getChatRoomId(currentUserId!!,user.id)
                    val lastMessageSnapshot = firebaseFirestore
                        .collection("chatrooms")
                        .document(chatroomId)
                        .collection("messages")
                        .orderBy("createdAt",Query.Direction.DESCENDING)
                        .limit(1)
                        .get()
                        .await()
                    val lastMessage = lastMessageSnapshot.documents.firstOrNull()?.getString("text")
                    val lastMessageTimestamp = lastMessageSnapshot
                        .documents
                        .firstOrNull()?.getString("createdAt")
                    val formatTimeStamp = if(lastMessageTimestamp!=null)
                        formatTimeStamp(lastMessageTimestamp) else
                            "00:00"




                    user.copy(
                        lastMessage = lastMessage,
                        lastMessageTimeStamp = formatTimeStamp
                    )

                }
                emit(Result.Success(usersWithMessages))

            }catch (e:Exception){
                emit(Result.Failure(e.localizedMessage?:"Failed to fetch active chats"))
            }
        }
    }

    private fun formatTimeStamp(timeStamp:String):String{
        val date = Date(timeStamp.toLong())
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(date)
    }
}