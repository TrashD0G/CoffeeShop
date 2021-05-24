package com.artem.coffeeshop.utilites

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    CURRENT_USER = AUTH.currentUser!!
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference


}