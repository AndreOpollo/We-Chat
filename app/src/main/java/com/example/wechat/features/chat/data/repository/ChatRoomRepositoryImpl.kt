package com.example.wechat.features.chat.data.repository

import com.example.wechat.features.chat.data.model.Message
import com.example.wechat.features.chat.domain.repository.ChatRoomRepository
import com.example.wechat.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ChatRoomRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
):ChatRoomRepository {
    override suspend fun sendChat(message: String,receiverId: String): Flow<Result<Unit>> {
        return flow {
            try {
                emit(Result.Loading)
                val currentUser = firebaseAuth.currentUser
                if(currentUser == null){
                    emit(Result.Failure("User not authenticated"))
                    return@flow
                }
                val senderId = currentUser.uid
                val chatRoomId = getChatRoomId(senderId,receiverId)
                val senderSnapshot = firebaseFirestore
                    .collection("users")
                    .document(senderId)
                    .get()
                    .await()
                val senderData = senderSnapshot.data
                val senderName = senderData?.get("username").toString()
                val photoUrl = senderData?.get("photoUrl").toString()
                val messageData = Message(
                    senderId = senderId,
                    photoUrl = photoUrl,
                    senderName = senderName,
                    text = message,
                    createdAt = System.currentTimeMillis().toString()
                )
                firebaseFirestore
                    .collection("chatrooms")
                    .document(chatRoomId)
                    .collection("messages")
                    .add(messageData)
                    .await()
                emit(Result.Success(Unit))

            }catch (e:Exception){
                Result.Failure(e.localizedMessage?:"Something went wrong")
            }
        }
    }

    override suspend fun fetchChats(senderId:String,receiverId: String): Flow<Result<List<Message>>> {
        return callbackFlow {
            try {
                trySend(Result.Loading)
                val currentUser = firebaseAuth.currentUser
                if(currentUser == null){
                    trySend(Result.Failure("User not authenticated"))
                    close()
                    return@callbackFlow
                }
                val sender = currentUser.uid
                val chatRoomId = getChatRoomId(sender,receiverId)

                    val listener = firebaseFirestore
                        .collection("chatrooms")
                        .document(chatRoomId)
                        .collection("messages")
                        .orderBy("createdAt")
                        .addSnapshotListener{
                            snapshot,exception->
                            if(exception!=null){
                                trySend(Result.Failure(exception.localizedMessage?:"Something went wrong"))
                                return@addSnapshotListener
                            }
                            if(snapshot!=null){
                                val chatMessages = snapshot.documents.map { document->
                                    document.toObject(Message::class.java)!!
                                }

                                trySend(Result.Success(chatMessages))

                            }


                        }
                awaitClose{listener.remove()}






            }catch (e:Exception){
                Result.Failure(e.localizedMessage?:"Something went wrong")

            }
        }
    }
}

private fun getChatRoomId(senderId:String,receiverId:String):String{
    return if(senderId<receiverId){
        "$senderId-$receiverId"
    }else{
        "$receiverId-$senderId"
    }

}