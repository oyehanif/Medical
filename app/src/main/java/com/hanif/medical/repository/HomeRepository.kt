package com.hanif.medical.repository

import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hanif.medical.Screens.doctor.BookingProcess
import com.hanif.medical.application.MedicalAppClass
import com.hanif.medical.models.Resource
import com.hanif.medical.utils.Appointment
import com.hanif.medical.utils.DOCTOR
import com.hanif.medical.utils.USERS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class DoctorModel(
    val image: String = "",
    val name: String = "",
    val specialize: String = "",
    val exp: Long = 0L,
    val rating: String = "",
    val fees: Long = 0L,
    val email: String = "",
    val password: String = "",
    val phone: Long = 0L,
    val desc: String = "",
    val patient: Long = 0L,
    val id: Long = 0L
)

data class MedicineModel(
    val image: String = "",
    val name: String = "",
    val desc: String = "",
    val price: Long = 0L
)

class HomeRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) {

    val packagesList: MutableList<DoctorModel> = mutableListOf()
    val userAppointmentLIst: MutableList<BookingProcess> = mutableListOf()
    val medicineList: MutableList<MedicineModel> = mutableListOf()
    /*fun getDoctorList() {

        val result =
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    if (snapshot.exists()) {
                        for (postSnapshot in snapshot.children) {
                            val post: DoctorModel = snapshot.getValue(DoctorModel::class.java)!!
                            list.add(post)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }*/


    fun fetchData(): MutableStateFlow<Resource<List<DoctorModel>>> {
        val fetchDataFromFirebase =
            MutableStateFlow<Resource<List<DoctorModel>>>(Resource.Loading())
        try {

            firebaseDatabase.getReference("Doctors")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        packagesList.clear()
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                val model = dataSnapshot.getValue(DoctorModel::class.java)!!
                                packagesList.add(model)
                            }
                            fetchDataFromFirebase.value = Resource.Success(packagesList)
                        } else {
                            fetchDataFromFirebase.value = Resource.Success(emptyList())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        fetchDataFromFirebase.value = Resource.Error(error.message)
                    }
                })
        } catch (e: Exception) {
            fetchDataFromFirebase.value = Resource.Error(e.message.toString())
        }
        return fetchDataFromFirebase

        /*return flow {
            firebaseDatabase.getReference("Doctors")
                .child(firebaseAuth.currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        packagesList.clear()
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                val model = dataSnapshot.getValue(DoctorModel::class.java)!!
                                packagesList.add(model)
                            }
                        //    fetchDataFromFirebase.value = Resource.Success(packagesList)
                        } else {
                       //     fetchDataFromFirebase.value = Resource.Success(emptyList())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                      //  fetchDataFromFirebase.value = Resource.Error(error.message)

                        Log.e("TAG", "onCancelled: ERROR IN DOCTOR LIST", )
                    }
                })
            emit(packagesList)
        }*/
    }

    fun getMedicineList(): MutableStateFlow<Resource<List<MedicineModel>>> {

        val fetchDataFromFirebase =
            MutableStateFlow<Resource<List<MedicineModel>>>(Resource.Loading())
        try {
            firebaseDatabase.getReference("Medicine")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        packagesList.clear()
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                val model = dataSnapshot.getValue(MedicineModel::class.java)!!
                                medicineList.add(model)
                            }
                            fetchDataFromFirebase.value = Resource.Success(medicineList)
                        } else {
                            fetchDataFromFirebase.value = Resource.Success(emptyList())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        fetchDataFromFirebase.value = Resource.Error(error.message)
                    }
                })
        } catch (e: Exception) {
            fetchDataFromFirebase.value = Resource.Error(e.message.toString())
        }

        return fetchDataFromFirebase
    }

    suspend fun insertAppointment(bookingProcess: BookingProcess) {
        val uid = firebaseAuth.uid
        try {
            firebaseDatabase.getReference(Appointment).child(USERS).child(uid!!).push().setValue(bookingProcess)
                .await()
            firebaseDatabase.getReference(Appointment).child(DOCTOR).child(bookingProcess.model!!.id.toString()).push().setValue(bookingProcess)
                .await()
//            Toast.makeText(MedicalAppClass.getAppContext(), "Appointment Add Successfully", Toast.LENGTH_LONG).show()

        } catch (e: FirebaseException) {
            Log.e("TAG", "insertAppointment: ${e.message.toString()}", )
        }
    }

    fun getUserAppointment(): MutableStateFlow<Resource<List<BookingProcess>>> {
        val fetchAppointmentOfUser =
            MutableStateFlow<Resource<List<BookingProcess>>>(Resource.Loading())
        val uid = firebaseAuth.uid
        try {
            firebaseDatabase.getReference(Appointment).child(USERS).child(uid!!).
                addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        userAppointmentLIst.clear()
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                val model = dataSnapshot.getValue(BookingProcess::class.java)!!
                                userAppointmentLIst.add(model)
                            }
                            fetchAppointmentOfUser.value = Resource.Success(userAppointmentLIst)
                        } else {
                            fetchAppointmentOfUser.value = Resource.Success(emptyList())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        fetchAppointmentOfUser.value = Resource.Error(error.message)
                    }
                })
        } catch (e: Exception) {
            fetchAppointmentOfUser.value = Resource.Error(e.message.toString())
        }

        return fetchAppointmentOfUser
    }
}

