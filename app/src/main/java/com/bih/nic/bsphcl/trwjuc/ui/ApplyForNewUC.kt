package com.bih.nic.bsphcl.trwjuc.ui

import MyPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.bih.nic.bsphcl.trwjuc.R

class ApplyForNewUC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_for_new_uc)
        // Initialize Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_app_new)
        setSupportActionBar(toolbar)
        // Set the back button icon (if not already set in XML)
        //toolbar.setNavigationIcon(R.drawable.ic_back) // Your custom back button icon

  // Enable the back navigation button (up button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Apply New" // Set Toolbar title
        // Set the color of the back button
        //supportActionBar?.setN
        val tabLayout = findViewById<com.google.android.material.tabs.TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<androidx.viewpager2.widget.ViewPager2>(R.id.viewPager)

        // Set up the adapter for ViewPager2
        val adapter = MyPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = true
        // Disable nested scrolling for ViewPager2
        //viewPager.isNestedScrollingEnabled = false
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.isNestedScrollingEnabled = false
        viewPager.setPageTransformer { page, position ->
            page.translationY = position * page.height // ✅ Makes pages scroll smoothly
        }
        // Link TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Tab 1"
                1 -> "Tab 2"
                else -> "Tab ${position + 1}"
            }
        }.attach()
    }

    // Handle the back navigation button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Close the activity and navigate back
        return true
    }
}
