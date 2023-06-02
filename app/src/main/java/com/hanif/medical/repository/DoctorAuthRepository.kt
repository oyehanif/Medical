package com.hanif.medical.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hanif.medical.Screens.doctor.BookingProcess
import com.hanif.medical.datastore.DataStore
import com.hanif.medical.models.Resource
import com.hanif.medical.utils.Appointment
import com.hanif.medical.utils.DOCTOR
import com.hanif.medical.utils.DOCTOR_AUTH
import com.hanif.medical.utils.USERS
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.properties.Delegates

class DoctorAuthRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase, private val dataStore: DataStore
) {
    val doctorAppointmentLIst: MutableList<BookingProcess> = mutableListOf()
    var getDoctorID :Long? = null
    suspend fun doctorLogin(phone: String, password: String): MutableStateFlow<Resource<String>> {
        val doctorLoginResult =
            MutableStateFlow<Resource<String>>(Resource.Loading())
        firebaseDatabase.getReference(DOCTOR_AUTH)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(phone.trim())) {
                        val getpassword =
                            snapshot.child(phone.trim()).child("password")
                                .getValue(String::class.java)
                        if (getpassword.equals(password)) {
                            getDoctorID =
                                snapshot.child(phone.trim()).child("doctorId").getValue(Long::class.java)!!
                            doctorLoginResult.value = Resource.Success("Login Successful")
                        } else {
                            doctorLoginResult.value = Resource.Error("Wrong Password")
                        }
                    } else {
                        doctorLoginResult.value = Resource.Error("Email Not Found")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    doctorLoginResult.value = Resource.Error(error.message)
                }

            })
        return doctorLoginResult
    }

    fun getDoctorAppointment(): MutableStateFlow<Resource<List<BookingProcess>>> {
        val fetchAppointmentOfUser =
            MutableStateFlow<Resource<List<BookingProcess>>>(Resource.Loading())
        try {
            Log.e("TAG", "getDoctorAppointment: ${getDoctorID.toString()}", )
            getDoctorID?.let {
                Log.e("TAG", "getDoctorAppointment: ${getDoctorID.toString()}", )
                firebaseDatabase.getReference(Appointment).child(DOCTOR).child(it.toString())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            doctorAppointmentLIst.clear()
                            if (snapshot.exists()) {
                                for (dataSnapshot in snapshot.children) {
                                    val model = dataSnapshot.getValue(BookingProcess::class.java)!!
                                    doctorAppointmentLIst.add(model)
                                }
                                fetchAppointmentOfUser.value = Resource.Success(doctorAppointmentLIst)
                            } else {
                                fetchAppointmentOfUser.value = Resource.Success(emptyList())
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            fetchAppointmentOfUser.value = Resource.Error(error.message)
                        }
                    })
            }
        } catch (e: Exception) {
            fetchAppointmentOfUser.value = Resource.Error(e.message.toString())
        }

        return fetchAppointmentOfUser
    }

}

