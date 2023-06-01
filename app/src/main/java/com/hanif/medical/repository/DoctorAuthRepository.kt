package com.hanif.medical.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.models.Resource
import com.hanif.medical.utils.DOCTOR
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DoctorAuthRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,private val dataStore: DataStore
) {

    suspend fun doctorLogin(email: String, password: String): MutableStateFlow<Resource<String>> {
        val doctorLoginResult =
            MutableStateFlow<Resource<String>>(Resource.Loading())
        firebaseDatabase.getReference(DOCTOR).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild(email.trim())) {
                    val getpassword =
                        snapshot.child(email.trim()).child("password").getValue(String::class.java)
                    val getDoctorID =
                        snapshot.child(email.trim()).child("id").getValue(String::class.java)
                    if (getpassword.equals(password)) {
                      //  dataStore.putPreference(doctorID,getDoctorID)
                        doctorLoginResult.value =  Resource.Success("Login Successful")
                    } else {
                        doctorLoginResult.value =  Resource.Error("Wrong Password")
                    }
                } else {
                    doctorLoginResult.value =   Resource.Error("Email Not Found")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                doctorLoginResult.value =   Resource.Error(error.message)
            }

        })
        return doctorLoginResult
    }

}

