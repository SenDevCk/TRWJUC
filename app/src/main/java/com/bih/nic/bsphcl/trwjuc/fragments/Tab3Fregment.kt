package com.bih.nic.bsphcl.trwjuc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bih.nic.bsphcl.trwjuc.R



class Tab3Fregment : Fragment() {

    var recycler_view : RecyclerView ?=null
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
           var view:View = inflater.inflate(R.layout.fragment_tab3, container, false)
           recycler_view= view.findViewById(R.id.recycler_view)
        return view
    }
}