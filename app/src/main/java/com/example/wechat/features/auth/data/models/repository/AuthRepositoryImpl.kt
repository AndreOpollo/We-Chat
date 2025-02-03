package com.example.wechat.features.auth.data.models.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
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
    ): Result<User> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            val firebaseUser = authResult.user?:return Result.Failure("User registration failed")

            val user = User(
                id = firebaseUser.uid,
                username = username,
                photoUrl = photoUrl,
                email = email,
                createdAt = System.currentTimeMillis().toString()
            )
            firebaseFirestore.collection("users").document(firebaseUser.uid).set(user).await()
            Result.Success(user)

        }catch (e:Exception){
            Result.Failure("Error creating user: ${e.message}")

        }
    }

    override suspend fun loginUser(email: String, password: String): Result<User>{
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            val firebaseUser = authResult.user?:return Result.Failure("Login failed")

            val snapshot = firebaseFirestore
                .collection("users")
                .document(firebaseUser.uid)
                .get()
                .await()
            val user = snapshot.toObject(User::class.java)?:return Result.Failure("User not found")
            Result.Success(user)

        }catch (e:Exception){
            Result.Failure("Error message: ${e.message}")
        }
    }
}