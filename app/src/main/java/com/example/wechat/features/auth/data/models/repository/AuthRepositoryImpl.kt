package com.example.wechat.features.auth.data.models.repository

import com.example.wechat.features.auth.data.models.User
import com.example.wechat.features.auth.domain.repository.AuthRepository
import com.example.wechat.util.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
):AuthRepository{
    private val _isLoggedIn = MutableStateFlow(firebaseAuth.currentUser!=null)
    override val isLoggedIn: StateFlow<Boolean>
        get() = _isLoggedIn

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
            firebaseFirestore.collection("users")
                .document(firebaseUser.uid)
                .set(user)
                .await()
            _isLoggedIn.value = true
            Result.Success(user)

        }catch (e:Exception){
            _isLoggedIn.value = false
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
            _isLoggedIn.value = true
            Result.Success(user)

        }catch (e:Exception){
            _isLoggedIn.value = false
            Result.Failure("Error message: ${e.message}")
        }
    }

    override suspend fun logout():Result<Unit> {
        return try {
            firebaseAuth.signOut()
            _isLoggedIn.value = false
                Result.Success(Unit)


        }catch (e:Exception){
            Result.Failure("Error occurred: ${e.message}")

        }
    }
}