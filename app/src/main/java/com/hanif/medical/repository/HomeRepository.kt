package com.hanif.medical.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hanif.medical.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class DoctorModel(val image: String = "", val name: String = "", val specialize: String = "")
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
}
