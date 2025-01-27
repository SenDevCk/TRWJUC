package com.bih.nic.bsphcl.trwjuc.adapters

/**
 * Created by Chandan Singh on 1/27/2025.
 */

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bih.nic.bsphcl.trwjuc.fragments.Tab1Fragment
import com.bih.nic.bsphcl.trwjuc.fragments.Tab2Fregment  // Fixed the typo here (Tab2Fregment -> Tab2Fragment)

class MyAdapter(activity: FragmentActivity, private val totalTabs: Int) : FragmentStateAdapter(activity) {

    // Return the fragment for the given position
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1Fragment()
            1 -> Tab2Fregment()  // Correct the fragment name here
            // Add more fragments as necessary
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    // Return total number of tabs
    override fun getItemCount(): Int {
        return totalTabs
    }
}

