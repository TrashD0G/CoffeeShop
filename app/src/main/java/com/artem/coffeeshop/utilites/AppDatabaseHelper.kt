package com.artem.coffeeshop.utilites

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_USER: FirebaseUser
lateinit var CURRENT_UID:String
lateinit var REF_DATABASE_ROOT:DatabaseReference

const val TAG = "MyTag"

const val NODE_USERS = "users"
const val NODE_PRODUCTS = "products"
const val NODE_PRODUCTS_COFFEE = "cofee"
const val NODE_PRODUCTS_SWEETS = "sweets"
