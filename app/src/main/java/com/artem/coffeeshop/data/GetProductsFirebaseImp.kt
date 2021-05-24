package com.artem.coffeeshop.data

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.artem.coffeeshop.domain.GetProductsFirebase
import com.artem.coffeeshop.domain.Products
import com.artem.coffeeshop.utilites.NODE_PRODUCTS
import com.artem.coffeeshop.utilites.REF_DATABASE_ROOT
import com.artem.coffeeshop.utilites.TAG
import com.google.firebase.FirebaseException
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class GetProductsFirebaseImp : GetProductsFirebase, CoroutineScope {

    override suspend fun getProducts(productsList: ArrayList<Products>, adapter: RecyclerView) {
        try {

            val dbref = REF_DATABASE_ROOT.child(NODE_PRODUCTS)
            val childEventListener = object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val productAdd = snapshot.getValue(Products::class.java)

                        if (productAdd != null) {
                            productsList.add(productAdd)
                            adapter.adapter?.notifyDataSetChanged()
                            Log.i(TAG, "onChildAdded: Отработал! ")
                        }

                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val productChanged = snapshot.getValue(Products::class.java)
                    for ( i in productsList){

                        if (i.productID == productChanged!!.productID){
                            Log.i(TAG, "onChildChanged:  Отработал!")
                            productsList.remove(i)
                            productsList.add(productChanged)
                            adapter.adapter?.notifyDataSetChanged()
                            break
                        }
                    }


                    Log.i(TAG, "onChildChanged: Отработал! ")
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    val productRemove = snapshot.getValue(Products::class.java)
                    productsList.remove(productRemove)
                    adapter.adapter?.notifyDataSetChanged()
                    Log.i(TAG, "onChildRemoved: Отработал! ")
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    Log.i(TAG, "onChildMoved: Отработал! ")
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i(TAG, "onCancelled: ERROR! $error")
                }
            }


            dbref.addChildEventListener(childEventListener)

        }
        catch (e: FirebaseException) {
            Log.i(TAG, "CreateAccountFirebaseImp: Ошибка! " + e.toString())
        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


}
