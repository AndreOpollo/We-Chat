package com.example.wechat.features.auth.data.models.repository

import android.util.Log
import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
):AuthRepository{
    override suspend fun createUser(
        email: String,
        username: String,
        password: String,
        photoUrl: String
    ): Flow<Result<AuthResult>> {
        return flow {
            try {
                emit(Result.Loading)
                val authResult = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
                val user = authResult.user?.let {
                    User(
                        id = it.uid,
                        email = email,
                        photoUrl = photoUrl,
                        username = username,
                        createdAt = System.currentTimeMillis().toString()
                    )
                }
                if (user != null) {
                    firebaseFirestore
                        .collection("users")
                        .document(authResult.user?.uid.toString())
                        .set(user)
                        .await()
                }
                emit(Result.Success(data = authResult))

            }catch (e:Exception){
                emit(Result.Failure(e.localizedMessage?:"Something went wrong"))

            }
        }
    }

    override suspend fun loginUser(email: String, password: String): Flow<Result<AuthResult>> {
        return flow {
            try {
                emit(Result.Loading)
                val authResult = firebaseAuth.signInWithEmailAndPassword(email,password).await()
                emit(Result.Success(authResult))
            }catch (e:Exception){
                emit(Result.Failure(e.localizedMessage?:"Something went wrong"))
            }
        }
    }

    override suspend fun logout() = firebaseAuth.signOut()

    override suspend fun currentUserId(): String = firebaseAuth.currentUser?.uid ?: ""

    override suspend fun isLoggedIn(): Boolean = firebaseAuth.currentUser == null


}