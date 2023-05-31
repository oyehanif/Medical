package com.hanif.medical.repository

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hanif.medical.Screens.shopping.ShoppingProcessModel
import com.hanif.medical.models.Resource
import com.hanif.medical.utils.MEDICINE_ORDER
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) {
    val orderList: MutableList<ShoppingProcessModel> = mutableListOf()
    private val uid = firebaseAuth.uid

    suspend fun insertShoppingOrder(shoppingProcessModel: ShoppingProcessModel) {
        try {
            firebaseDatabase.getReference(MEDICINE_ORDER).child(uid!!).push()
                .setValue(shoppingProcessModel).await()
        } catch (e: FirebaseException) {
            Log.e("TAG", "insertAppointment: ${e.message.toString()}")
        }
    }


    fun getOrderList(): MutableStateFlow<Resource<List<ShoppingProcessModel>>> {

        val getOrderList =
            MutableStateFlow<Resource<List<ShoppingProcessModel>>>(Resource.Loading())
        try {
            firebaseDatabase.getReference(MEDICINE_ORDER).child(uid!!)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        orderList.clear()
                        if (snapshot.exists()) {
                            for (dataSnapshot in snapshot.children) {
                                val model =
                                    dataSnapshot.getValue(ShoppingProcessModel::class.java)!!
                                orderList.add(model)
                            }
                            getOrderList.value = Resource.Success(orderList)
                        } else {
                            getOrderList.value = Resource.Success(emptyList())
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        getOrderList.value = Resource.Error(error.message)
                    }
                })
        } catch (e: Exception) {
            getOrderList.value = Resource.Error(e.message.toString())
        }

        return getOrderList
    }
}