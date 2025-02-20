package com.bih.nic.bsphcl.trwjuc.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab2Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel
import com.bih.nic.bsphcl.trwjuc.fragments.tab2.Tab2ViewModel
import com.bih.nic.bsphcl.trwjuc.ui.viewmodels.SharedViewModel
import com.bih.nic.bsphcl.trwjuc.utils.YearPickerDialog
import java.util.Calendar
import androidx.activity.viewModels
import androidx.room.Room
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Tab1Fragment : Fragment(), Tab1Listner {
    private lateinit var binding: FragmentTab1Binding
    private lateinit var viewModel: Tab1ViewModel
    var viewPager:ViewPager2?=null

    var appDataBase : AppDatabase?=null
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appDataBase= Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "trw_db"
        ).build()
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
        // Inflate the layout for this fragment using DataBinding
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab1,
            container,
            false
        )
        binding.calViw.setOnClickListener {
            onCalendarIconClick()
        }
        // Check if binding is not null before accessing it
        binding?.let {
            // Obtain ViewModel instance
            viewModel = ViewModelProvider(this).get(Tab1ViewModel::class.java)

            // Set the ViewModel to the binding
            it.tab1ViewModel = viewModel

            // Set the lifecycle owner to make LiveData observable
            it.lifecycleOwner = viewLifecycleOwner
            viewModel.tab1Listner = this
            //spiner
            populateCircle()
            populateDivision()
            populateSubdivision()
            populateSection()
            populateTRWStation()
            //checkbox
            dtrBodyFound()
            htStud()
            ltStud()
            htbussiing()
            ltbussiing()

            // Observe the form state from ViewModel
            viewModel.formState.observe(requireActivity(), Observer { formState ->
                formState.errorMessage?.let {
                    // Show the error message
                    Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
                }

                // Enable or disable the submit button based on form validity
                //binding?.buttonTab1?.isEnabled = formState.isValid
            })

            // Return the root view of the binding
            return it.root
        }
        // Return null if binding is null (shouldn't happen)
        return null
    }
    private fun dtrBodyFound(){
        binding?.radioGroupDtr?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio_group_dtr_ok->{
                    binding?.tab1ViewModel?.setSelectedDtr("OK")
                }
                R.id.radio_group_dtr_notok->{
                    binding?.tab1ViewModel?.setSelectedDtr("NOT")
                }
            }
        }
    }

    private fun htStud(){
        binding?.radioGroupHtstud?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio_group_htstud_ok->{
                    binding?.tab1ViewModel?.setSelectedHTSTUD("OK")
                }
                R.id.radio_group_htstud_def->{
                    binding?.tab1ViewModel?.setSelectedHTSTUD("NOT")
                }
                R.id.radio_group_htstud_miss->{
                    binding?.tab1ViewModel?.setSelectedHTSTUD("MISSING")
                }
            }
        }
    }

    private fun ltStud(){
        binding?.radioGroupLtstud?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio_group_ltstud_ok->{
                    binding?.tab1ViewModel?.setSelectedLTSTUD("OK")
                }
                R.id.radio_group_ltstud_def->{
                    binding?.tab1ViewModel?.setSelectedLTSTUD("NOT")
                }
                R.id.radio_group_ltstud_miss->{
                    binding?.tab1ViewModel?.setSelectedLTSTUD("MISSING")
                }
            }
        }
    }

    private fun htbussiing(){
        binding?.radioGroupHtbuss?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio_group_htbuss_ok->{
                    binding?.tab1ViewModel?.setSelectedHTBussing("OK")
                }
                R.id.radio_group_htbuss_def->{
                    binding?.tab1ViewModel?.setSelectedHTBussing("NOT")
                }
                R.id.radio_group_htbuss_miss->{
                    binding?.tab1ViewModel?.setSelectedHTBussing("MISSING")
                }
            }
        }
    }

    private fun ltbussiing(){
        binding?.radioGroupLtbuss?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio_group_ltbuss_ok->{
                    binding?.tab1ViewModel?.setSelectedLTBussing("OK")
                }
                R.id.radio_group_ltbuss_def->{
                    binding?.tab1ViewModel?.setSelectedLTBussing("NOT")
                }
                R.id.radio_group_ltbuss_miss->{
                    binding?.tab1ViewModel?.setSelectedLTBussing("MISSING")
                }
            }
        }
    }

    private fun populateCircle() {
        // Observe the subdivisionList LiveData
        viewModel?.circleList?.observe(viewLifecycleOwner, Observer { circles ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                circles
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.optionSubdivision?.adapter = adapter
        })

        binding?.optionCircle?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //val selectedSubdivision = parent.getItemAtPosition(position) as String
                val item = binding?.tab1ViewModel?.circles?.get(position)
                if (item != null) {
                    binding?.tab1ViewModel?.onCircleSelected(item.circleId)
                }

                // Handle the selected subdivision

                //Toast.makeText(requireContext(), "Selected: $selectedSubdivision", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }
    private fun populateDivision() {
        // Observe the subdivisionList LiveData
        viewModel.divisionList.observe(viewLifecycleOwner, Observer { divisions ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                divisions
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.optionDivision?.adapter = adapter
        })

        binding?.optionDivision?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // val selectedDivision = parent.getItemAtPosition(position) as String
                // Handle the selected subdivision
                val item = binding?.tab1ViewModel?.divisions?.get(position)
                if (item != null) {
                    binding?.tab1ViewModel?.onDivisionSelect(item.divId)
                }
                //Toast.makeText(requireContext(), "Selected: $selectedDivision", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }
    private fun populateSubdivision() {
        // Observe the subdivisionList LiveData
        viewModel.subdivisionList.observe(viewLifecycleOwner, Observer { subdivisions ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                subdivisions
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.optionSubdivision.adapter = adapter
        })

        binding?.optionSubdivision?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //val selectedSubdivision = parent.getItemAtPosition(position) as String
                // Handle the selected subdivision
                val item = binding?.tab1ViewModel?.subdivisions?.get(position)
                if (item != null) {
                    binding?.tab1ViewModel?.onSubDivisionSelect(item.subDivId)
                }
                //Toast.makeText(requireContext(), "Selected: $selectedSubdivision", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }

    private fun populateSection() {
        // Observe the subdivisionList LiveData
        viewModel?.sectionList?.observe(viewLifecycleOwner, Observer { sections ->
            // Set up the Spinner with the subdivision data
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                sections
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.optionSubdivision?.adapter = adapter
        })

        binding?.optionSubdivision?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //val selectedsection = parent.getItemAtPosition(position) as String
                // Handle the selected section
                val item = binding?.tab1ViewModel?.sections?.get(position)
//                if (item != null) {
//                    binding?.tab1ViewModel?.onSubDivisionSelect(item.secId)
//                }
                //Toast.makeText(requireContext(), "Selected: $selectedsection", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }

    private fun populateTRWStation(){
        binding?.optionTrwStation?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position>0){
                    viewModel._selectedPlace.value=String.format("%d",position)
                }else{
                    viewModel._selectedPlace.value=""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }

    private fun populateCapacity(){
        binding?.optionCapacity?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position>0){
                    viewModel._selectedCapacity.value=String.format("%d",position)
                }else{
                    viewModel._selectedCapacity.value=""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }
    private fun populateScheme(){
        binding?.optionScheme?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position>0){
                    viewModel._selectedScheme.value=String.format("%d",position)
                }else{
                    viewModel._selectedScheme.value=""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle when nothing is selected
            }
        }
    }
    private fun showYearPickerDialog() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        // Show YearPickerDialog and pass the selected year back to the ViewModel
        val yearPickerDialog = YearPickerDialog(requireContext(), currentYear) { selectedYear ->
            // Send the selected year to the ViewModel
            viewModel.setSelectedYear(selectedYear)
        }
        yearPickerDialog.show(requireActivity().supportFragmentManager, "Select Year of Manufacturing")
    }
    fun onCalendarIconClick() {
        showYearPickerDialog()
    }
    override fun onSuccess() {
        Log.d("log","hi onSuccess")
        // Insert the data in the background to prevent blocking the UI
        viewModel.trwUniqueCode?.let { sharedViewModel.updateData(it) }
        viewPager?.currentItem = 1
        //sharedViewModel?.updateData("Data from Tab 1")
    }

    override fun onFailure(value: String) {
      Log.d("error msg",value)
    }


}