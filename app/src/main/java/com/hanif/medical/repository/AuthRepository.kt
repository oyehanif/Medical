package com.hanif.medical.repository

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.hanif.medical.models.Resource
import com.hanif.medical.models.UserModel
import com.hanif.medical.utils.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) {

    suspend fun login(loginRequest: LoginRequest): Flow<Resource<FirebaseUser>> {
        return flow {
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(
                    loginRequest.email,
                    loginRequest.password
                ).await()
                emit(Resource.Success(result.user!!))
            } catch (e: FirebaseAuthException) {
                emit(Resource.Error(e.message.toString()))
            } catch (e: FirebaseException) {
                emit(Resource.Error(e.message.toString()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun currentUser(): FirebaseUser? = firebaseAuth.currentUser

    suspend fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Flow<Resource<FirebaseUser>> {
        return flow {
            try {
                val user = UserModel(name, email, phone, password)
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                firebaseDatabase.getReference(USERS).child(result.user!!.uid).setValue(user).await()
                emit(Resource.Success(result.user!!))
            } catch (e: FirebaseAuthException) {
                emit( Resource.Error(e.message.toString()))
            } catch (e: FirebaseException) {
                emit( Resource.Error(e.message.toString()))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    suspend fun forgetPassword(email: String): Resource<Boolean> {
        return try {
            val result = firebaseAuth.sendPasswordResetEmail(email).isSuccessful
            if (result) {
                Resource.Success(result)
            } else {
                Resource.Error("User not found")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }
}

data class LoginRequest(
    val email: String,
    val password: String,
    val isCheck: Boolean = false,
)