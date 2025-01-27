package com.bih.nic.bsphcl.trwjuc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.adapters.MyAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ApplyForNewUC : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_for_new_uc)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        // Create the adapter and set it on the ViewPager2
        val adapter = MyAdapter(this, tabLayout.tabCount)  // Pass activity to the adapter
        viewPager.adapter = adapter

        // Link TabLayout with ViewPager2 using TabLayoutMediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"  // Optionally, set tab titles
        }.attach()
    }
}