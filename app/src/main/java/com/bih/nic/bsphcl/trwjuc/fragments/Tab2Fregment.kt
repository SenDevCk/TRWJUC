package com.bih.nic.bsphcl.trwjuc.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab2Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel
import com.bih.nic.bsphcl.trwjuc.fragments.tab2.Tab2Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab2.Tab2ViewModel
import java.util.Calendar


class Tab2Fregment : Fragment(), Tab2Listner {
    private lateinit var binding: FragmentTab2Binding
    private lateinit var viewModel: Tab2ViewModel
    var viewPager: ViewPager2?=null
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
           // Inflate the layout for this fragment using DataBinding
           viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
           binding = DataBindingUtil.inflate(
               inflater,
               R.layout.fragment_tab2,
               container,
               false
           )
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
               // Observe showDatePicker LiveData and show the DatePickerDialog
               viewModel.showDatePicker.observe(viewLifecycleOwner, { show ->
                   if (show) {
                       // Show the DatePickerDialog when the event is triggered
                       showDatePickerDialogRD("RD")
                       // Reset the event so the dialog doesn't show again on configuration changes
                       viewModel.resetDatePickerEvent("RD")
                   }
               })
               viewModel.showDatePickerDT.observe(viewLifecycleOwner, { show ->
                   if (show) {
                       // Show the DatePickerDialog when the event is triggered
                       showDatePickerDialogRD("DT")
                       // Reset the event so the dialog doesn't show again on configuration changes
                       viewModel.resetDatePickerEvent("DT")
                   }
               })
               viewModel.showDatePickerSVR.observe(viewLifecycleOwner, { show ->
                   if (show) {
                       // Show the DatePickerDialog when the event is triggered
                       showDatePickerDialogRD("DSVR")
                       // Reset the event so the dialog doesn't show again on configuration changes
                       viewModel.resetDatePickerEvent("DSVR")
                   }
               })
               viewModel.showDatePickerIssue.observe(viewLifecycleOwner, { show ->
                   if (show) {
                       // Show the DatePickerDialog when the event is triggered
                       showDatePickerDialogRD("DISSUE")
                       // Reset the event so the dialog doesn't show again on configuration changes
                       viewModel.resetDatePickerEvent("DISSUE")
                   }
               })
               return it.root
    }
           // Return null if binding is null (shouldn't happen)
           return null
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
        viewPager?.currentItem = 2
    }

    override fun onFailure() {

    }

}