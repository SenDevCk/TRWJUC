package com.bih.nic.bsphcl.trwjuc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityMainBinding
import com.bih.nic.bsphcl.trwjuc.fragments.MainFragment
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthViewModel
import com.bih.nic.bsphcl.trwjuc.ui.main.MainClickHandler
import com.bih.nic.bsphcl.trwjuc.ui.main.MainViewModel
import com.google.android.material.navigation.NavigationView




class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //var toolbar:Toolbar?=null

    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
           supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, MainFragment()).commit()
           navigationView.setCheckedItem(R.id.nav_home)
       }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                   .replace(R.id.fragment_container, MainFragment()).commit()
                }
                R.id.nav_jir -> {
                    val intent = Intent(this, JointInspectionActivity::class.java)
                    this.startActivity(intent)
                }
                R.id.nav_new -> {
                    val intent = Intent(this, ApplyForNewUC::class.java)
                    this.startActivity(intent)
                }
                R.id.nav_about -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
//                    supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, AboutFragment()).commit()
                R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
            }

            return true
        }
        override fun onBackPressed() {
            super.onBackPressed()
            if (drawerLayout.isDrawerOpen(GravityCompat.START)!!) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

