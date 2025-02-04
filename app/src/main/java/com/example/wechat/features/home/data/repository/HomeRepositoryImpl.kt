package com.example.wechat.features.home.data.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.home.domain.repository.HomeRepository
import com.example.wechat.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

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
        TODO("Not yet implemented")
    }
}