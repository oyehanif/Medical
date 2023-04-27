package com.hanif.medical.repository

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.hanif.medical.models.Resource
import com.hanif.medical.models.UserModel
import com.hanif.medical.utils.USERS
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) {

    suspend fun register(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val user = UserModel(name, email, phone, password)
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            firebaseDatabase.getReference(USERS).child(result.user!!.uid).setValue(user).await()
            Resource.Success(result.user!!)
        } catch (e: FirebaseAuthException) {
            Resource.Error(e.message.toString())
        } catch (e: FirebaseException) {
            Resource.Error(e.message.toString())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun login(email: String,password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(result.user!!)
        } catch (e: FirebaseAuthException) {
            Resource.Error(e.message.toString())
        } catch (e: FirebaseException) {
            Resource.Error(e.message.toString())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    fun currentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun logout() = firebaseAuth.signOut()
}