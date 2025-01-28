package com.bih.nic.bsphcl.trwjuc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel


class Tab1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using DataBinding
        val binding: ViewDataBinding? = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab1,
            container,
            false
        )
        // Obtain ViewModel instance
        val viewModel = ViewModelProvider(this).get(Tab1ViewModel::class.java)

        // Setting the ViewModel to the binding
        binding.viewModel = viewModel

        // Setting the lifecycle owner to make LiveData observable
        binding.lifecycleOwner = viewLifecycleOwner

        // Observing the LiveData for credential validation
      /*  viewModel.isValidCredential.observe(viewLifecycleOwner, Observer { isValid ->
            if (isValid) {
                // Handle valid credentials (e.g., navigate to another fragment)
            } else {
                // Handle invalid credentials (e.g., show an error message)
            }
        })*/

        // Return the root view of the binding
        return binding.root
    }
}