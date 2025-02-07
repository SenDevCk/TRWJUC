package com.bih.nic.bsphcl.trwjuc.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentMainBinding
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel
import com.bih.nic.bsphcl.trwjuc.ui.ApplyForNewUC
import com.bih.nic.bsphcl.trwjuc.ui.main.MainClickHandler
import com.bih.nic.bsphcl.trwjuc.ui.main.MainViewModel

class MainFragment : Fragment(),MainClickHandler {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMainBinding? = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        // Check if binding is not null before accessing it
        binding?.let {
            // Obtain ViewModel instance
            val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

            // Set the ViewModel to the binding
            it.mainViewModel = viewModel

            // Set the lifecycle owner to make LiveData observable
            it.lifecycleOwner = viewLifecycleOwner
            viewModel.mainClickHandler = this
            return it.root
        }

        // Return null if binding is null (shouldn't happen)
        return null
    }

    override fun applyNewClick() {
        val intent = Intent(requireContext(), ApplyForNewUC::class.java)
       this.startActivity(intent)
    }
}