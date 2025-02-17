package com.bih.nic.bsphcl.trwjuc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab3Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab3.Tab3Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab3.Tab3ViewModel


class Tab3Fregment : Fragment(),Tab3Listner {
    private lateinit var binding: FragmentTab3Binding
    private lateinit var viewModel: Tab3ViewModel
    var viewPager: ViewPager2?=null
    //var recycler_view : RecyclerView ?=null
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
           // Inflate the layout for this fragment using DataBinding
           binding = DataBindingUtil.inflate(
               inflater,
               R.layout.fragment_tab3,
               container,
               false
           )
           // Check if binding is not null before accessing it
           binding?.let {
               // Obtain ViewModel instance
               viewModel = ViewModelProvider(this).get(Tab3ViewModel::class.java)

               // Set the ViewModel to the binding
               it.tab3ViewModel = viewModel

               // Set the lifecycle owner to make LiveData observable
               it.lifecycleOwner = viewLifecycleOwner
               viewModel.tab3Listner = this
               val myList: MutableList<String> = mutableListOf()
               // Set up the AutoCompleteTextView
               var adapter = ArrayAdapter(
                   requireActivity(),
                   android.R.layout.simple_dropdown_item_1line, // Layout for dropdown items
                   myList
               )
               binding.spYearMan.setAdapter(adapter)

               // Observe the material list and update the adapter
               viewModel.materialList.observe(requireActivity(), Observer { materials ->
                   adapter.clear()
                   adapter.addAll(materials)
                   adapter.notifyDataSetChanged()
               })

               // Observe the selected material
               viewModel.selectedMaterial.observe(requireActivity(), Observer { selected ->
                   // Do something with the selected material (for example, show a Toast)
                   Toast.makeText(requireActivity(), "Selected Material: $selected", Toast.LENGTH_SHORT).show()
               })
               // Return the root view of the binding
               return it.root
           }
           // Return null if binding is null (shouldn't happen)
           return null
    }

    override fun onSuccess() {

    }

    override fun onFailure() {

    }
}