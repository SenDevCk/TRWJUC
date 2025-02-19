package com.bih.nic.bsphcl.trwjuc.ui

import MyPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.ui.viewmodels.SharedViewModel
import com.google.android.material.tabs.TabLayout

class ApplyForNewUC : AppCompatActivity() {
    private lateinit var sharedViewModel: SharedViewModel
    private var trwId:String?=null
    private var previousTabIndex: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_for_new_uc)
        // Initialize Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_app_new)
        setSupportActionBar(toolbar)
        // Set the back button icon (if not already set in XML)
        //toolbar.setNavigationIcon(R.drawable.ic_back) // Your custom back button icon
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
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
        sharedViewModel.data.observe(this, Observer { data ->
            // Update UI with shared data
            trwId= data
            toolbar.subtitle=trwId
        })
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Check your condition here
                if (!trwId.isNullOrBlank()) {
                    // Prevent tab selection
                    toolbar.subtitle="please enter serial number"
                    tabLayout.getTabAt(previousTabIndex)?.select() // Optional: Revert to previous tab
                } else {
                    // Allow the tab to be selected
                    previousTabIndex = tab?.position ?: 0 // Save the current tab index
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Do nothing, just ensure the tab remains disabled
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Optionally handle reselection of the tab
            }
        })
    }

    // Handle the back navigation button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Close the activity and navigate back
        return true
    }
}
