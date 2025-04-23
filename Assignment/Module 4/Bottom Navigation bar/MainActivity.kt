package com.example.newbundle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)

        loadFragment(HomeFragment())

        bottomNav.setOnItemSelectedListener {
            var fragment: Fragment? = null
            when (it.itemId) {
                R.id.nav_home -> fragment = HomeFragment()
                R.id.nav_profile -> fragment = ProfileFragment()
                R.id.nav_settings -> fragment = SettingsFragment()
            }
            if (fragment != null) {
                loadFragment(fragment)
            }
            true
        }
    }

    fun loadFragment(fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}