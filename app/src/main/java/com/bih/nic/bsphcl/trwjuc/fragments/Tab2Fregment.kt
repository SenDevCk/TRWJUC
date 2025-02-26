package com.bih.nic.bsphcl.trwjuc.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab2Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel
import com.bih.nic.bsphcl.trwjuc.fragments.tab2.Tab2Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab2.Tab2ViewModel
import com.bih.nic.bsphcl.trwjuc.ui.viewmodels.SharedViewModel
import java.util.Calendar


class Tab2Fregment : Fragment(), Tab2Listner {
    private lateinit var binding: FragmentTab2Binding
    private lateinit var viewModel: Tab2ViewModel
    var viewPager: ViewPager2?=null
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment using DataBinding
        viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab2,
            container,
            false
        )
        // Observe the shared data
        sharedViewModel.data.observe(viewLifecycleOwner, Observer { data ->
            binding.trwSerialNo.text = Editable.Factory.getInstance().newEditable(data)
        })
        // Check if binding is not null before accessing it
        binding?.let {
            // Obtain ViewModel instance
            viewModel = ViewModelProvider(this).get(Tab2ViewModel::class.java)

            // Set the ViewModel to the binding
            it.tab2ViewModel = viewModel

            // Set the lifecycle owner to make LiveData observable
            it.lifecycleOwner = viewLifecycleOwner
            viewModel.tab2Listner = this
            // Return the root view of the binding
            // Observe sho
            // wDatePicker LiveData and show the DatePickerDialog
            binding.clickDor.setOnClickListener {
                // Show the DatePickerDialog when the event is triggered
                showDatePickerDialogRD("RD")
                // Reset the event so the dialog doesn't show again on configuration changes
                viewModel.resetDatePickerEvent("RD")
            }
            binding.clickDot.setOnClickListener {
                // Show the DatePickerDialog when the event is triggered
                showDatePickerDialogRD("DT")
                // Reset the event so the dialog doesn't show again on configuration changes
                viewModel.resetDatePickerEvent("DT")
            }
            binding.clickSvr.setOnClickListener {
                showDatePickerDialogRD("DSVR")
                // Reset the event so the dialog doesn't show again on configuration changes
                viewModel.resetDatePickerEvent("DSVR")
            }
            binding.clickDoi.setOnClickListener {
                showDatePickerDialogRD("DISSUE")
                // Reset the event so the dialog doesn't show again on configuration changes
                viewModel.resetDatePickerEvent("DISSUE")
            }
            // Observe the form state from ViewModel
            viewModel.formState.observe(requireActivity(), Observer { formState ->
                formState.errorMessage?.let {
                    // Show the error message
                    Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                }

                // Enable or disable the submit button based on form validity
                //binding?.buttonTab1?.isEnabled = formState.isValid
            })
            return it.root// Inflate the layout using DataBinding
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab2, container, false)


            // Initialize ViewModel
            viewModel = ViewModelProvider(this).get(Tab2ViewModel::class.java)

            // Set ViewModel and lifecycle owner
            binding.tab2ViewModel = viewModel
            binding.lifecycleOwner = viewLifecycleOwner

            // Set listener for ViewModel actions
            viewModel.tab2Listner = this

            // Initialize shared ViewModel
            sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

            // Observe shared data and update UI
            sharedViewModel.data.observe(viewLifecycleOwner, Observer { data ->
                Toast.makeText(requireActivity(),"data : "+data,Toast.LENGTH_LONG).show()
                binding.trwSerialNo.text = Editable.Factory.getInstance().newEditable(data)
            })

            // Set click listeners for date pickers
            binding.clickDor.setOnClickListener {
                showDatePickerDialogRD("RD")
                viewModel.resetDatePickerEvent("RD")
            }
            binding.clickDot.setOnClickListener {
                showDatePickerDialogRD("DT")
                viewModel.resetDatePickerEvent("DT")
            }
            binding.clickSvr.setOnClickListener {
                showDatePickerDialogRD("DSVR")
                viewModel.resetDatePickerEvent("DSVR")
            }
            binding.clickDoi.setOnClickListener {
                showDatePickerDialogRD("DISSUE")
                viewModel.resetDatePickerEvent("DISSUE")
            }

            // Observe form state and display error messages
            viewModel.formState.observe(viewLifecycleOwner, Observer { formState ->
                formState.errorMessage?.let {
                    Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                }
            })

            // Initialize ViewPager after inflating layout
            viewPager = requireActivity().findViewById(R.id.viewPager)

            return binding.root
        }
        // Return null if binding is null (shouldn't happen)
        return null
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.data.value?.let { data ->
            binding.trwSerialNo.text = Editable.Factory.getInstance().newEditable(data)
        }
    }
    private fun showDatePickerDialogRD(flag : String) {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create and show the DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                // When the user selects a date, update the date in ViewModel
                val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                viewModel.setDate(selectedDate,flag)
            },
            year, month, day
        )
        datePickerDialog.show()
    }
    override fun onSuccess() {

        Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()
        requireActivity().finish()
        //viewPager?.currentItem = 2
    }

    override fun onFailure(error :String) {
       Log.d("error",error)
    }

}