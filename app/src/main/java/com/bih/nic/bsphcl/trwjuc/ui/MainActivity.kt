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
//       val binding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
//        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//
//        // Set the ViewModel in the binding (optional, depending on if you are using data binding for ViewModel)
//        binding.mainViewHolder = viewModel
//        binding.lifecycleOwner = this
//        // Set AuthListener for ViewModel
//        viewModel.mainClickHandler = this
//        toolbar=findViewById<Toolbar>(R.id.toolbar_sel_main)
//        toolbar.title=""



   // }

//    override fun applyNewClick() {
////        TODO("Not yet implemented")
//        val intent = Intent(this, ApplyForNewUC::class.java)
//        this.startActivity(intent)
//    }

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
            when (item.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
                //                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, HomeFragment()).commit()
                R.id.nav_settings -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
//                    supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, SettingsFragment()).commit()
                R.id.nav_share -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
//                    supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, ShareFragment()).commit()
                R.id.nav_about -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
//                    supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, AboutFragment()).commit()
                R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
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

