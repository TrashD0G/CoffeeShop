package com.artem.coffeeshop.presentation.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.Navigation
import com.artem.coffeeshop.R
import com.artem.coffeeshop.databinding.ActivityMainScreenBinding
import com.artem.coffeeshop.utilites.AUTH
import com.artem.coffeeshop.utilites.CURRENT_USER
import com.artem.coffeeshop.utilites.initFirebase


class MainScreenActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root
        val menu = binding.navView.menu

        //Firebase
        initFirebase()


        if (CURRENT_USER != null){
            menu.clear()
            Log.i(com.artem.coffeeshop.utilites.TAG,"MainScreenAcntivity: Текущий ользователь: " + CURRENT_USER.email)
            binding.navView.inflateMenu(R.menu.nav_drawer_enter_menu)
        } else{
            Log.i(com.artem.coffeeshop.utilites.TAG, "MainScreenAcntivity: Нет пользователя: $CURRENT_USER")
        }


        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.navView.setNavigationItemSelectedListener {

            when(it.itemId) {

                R.id.entry -> Navigation.findNavController(this, R.id.main_screen_fragment).navigate(R.id.accountActivity)

                R.id.exit -> {
                    AUTH.signOut()
                    menu.clear()
                    Log.i(com.artem.coffeeshop.utilites.TAG,"MainScreenAcntivity: Вышли из аккаунта " + CURRENT_USER.email)
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