package com.artem.coffeeshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artem.coffeeshop.R

const val TAG = "MyTag"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}