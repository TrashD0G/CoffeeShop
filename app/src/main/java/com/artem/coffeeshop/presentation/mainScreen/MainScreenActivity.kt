package com.artem.coffeeshop.presentation.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.Navigation
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.ActivityMainScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

const val TAG = "MyTag"

class MainScreenActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root


        auth = Firebase.auth
        val currentUser = auth.currentUser
        val menu = binding.navView.menu

        if (currentUser != null){
            menu.clear()
            Log.i(TAG,"MainScreenAcntivity: Текущий ользователь: " + currentUser.email)
            binding.navView.inflateMenu(R.menu.nav_drawer_enter_menu)
        } else{
            Log.i(TAG,"MainScreenAcntivity: Нет пользователя: " + currentUser)
        }




        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {

            when(it.itemId) {

                R.id.entry -> Navigation.findNavController(this, R.id.main_screen_fragment).navigate(R.id.accountActivity)

                R.id.exit -> {
                    auth.signOut()
                    menu.clear()
                    Log.i(TAG,"MainScreenAcntivity: Вышли из аккаунта " + currentUser?.email)
                    binding.navView.inflateMenu(R.menu.nav_drawer_not_enter_menu)
                }

            }

            true
        }
        setContentView(view)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}