package com.bih.nic.bsphcl.trwjuc.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
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
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.FragmentTab1Binding
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1Listner
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1ViewModel
import com.bih.nic.bsphcl.trwjuc.ui.viewmodels.SharedViewModel
import com.bih.nic.bsphcl.trwjuc.utils.YearPickerDialog
import java.util.Calendar
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
            val trwId=requireActivity().intent.getStringExtra("trwNo")
            //spiner
            populateCircle()
            populateDivision()
            populateSubdivision()
            populateSection()
            populateTRWStation()
            populateCapacity()
            populateScheme()
            //checkbox
            /*dtrBodyFound()
            htStud()
            ltStud()
            htbussiing()
            ltbussiing()*/
            binding.clickDob.setOnClickListener {
                showDatePickerDialogRD()
                // Reset the event so the dialog doesn't show again on configuration changes
                //viewModel.resetDatePickerEvent()
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
            if (trwId!=null){
                sharedViewModel.updateData(trwId)
                viewModel.trwUniqueCode=trwId
                viewModel.loadData()
            }
            viewModel.dataToSave2.observe(viewLifecycleOwner){ dataJir->
                //val position = viewModel.getCirclePosition(dataJir.circleId)
                //binding.optionCircle.setSelection(position+1,true)
               /*when(dataJir.dtrBodyFound){
                   "OK"->binding.radioGroupDtr.check(R.id.radio_group_dtr_ok)
                   "NOT"->binding.radioGroupDtr.check(R.id.radio_group_dtr_notok)
               }
                when(dataJir.htStudFound){
                    "OK"->binding.radioGroupHtstud.check(R.id.radio_group_htstud_ok)
                    "DEF"->binding.radioGroupHtstud.check(R.id.radio_group_htstud_def)
                    "MISSING"->binding.radioGroupHtstud.check(R.id.radio_group_htstud_miss)
                }
                when(dataJir.ltStudFound){
                    "OK"->binding.radioGroupLtstud.check(R.id.radio_group_ltstud_ok)
                    "DEF"->binding.radioGroupLtstud.check(R.id.radio_group_ltstud_def)
                    "MISSING"->binding.radioGroupLtstud.check(R.id.radio_group_ltstud_miss)
                }
                when(dataJir.htBushingFound){
                    "OK"->binding.radioGroupHtbuss.check(R.id.radio_group_htbuss_ok)
                    "DEF"->binding.radioGroupHtbuss.check(R.id.radio_group_htbuss_def)
                    "MISSING"->binding.radioGroupHtbuss.check(R.id.radio_group_htbuss_miss)
                }
                when(dataJir.ltBushingStudFound){
                    "OK"->binding.radioGroupLtbuss.check(R.id.radio_group_ltbuss_ok)
                    "DEF"->binding.radioGroupLtbuss.check(R.id.radio_group_ltbuss_def)
                    "MISSING"->binding.radioGroupLtbuss.check(R.id.radio_group_ltbuss_miss)
                }*/

                lifecycleScope.launch(Dispatchers.IO) {
                    val circlePos = viewModel.getCirclePosition(dataJir.circleId)
                    val divPos = viewModel.getDivPosition(dataJir.divisionId)
                    val subDivPos = viewModel.getSubDivPosition(dataJir.subdivId)
                    val secPos = viewModel.getSectionPosition(dataJir.sectionId)

                    withContext(Dispatchers.Main) {
                        binding?.textTrwSlno?.isEnabled=false
                        binding?.optionCircle?.setSelection(circlePos + 1)
                        binding?.optionCircle?.isEnabled = false
                        binding?.optionDivision?.setSelection(divPos +1)
                        binding?.optionDivision?.isEnabled = false
                        binding?.optionSubdivision?.setSelection(subDivPos + 1)
                        binding?.optionSubdivision?.isEnabled = false
                        binding?.optionSection?.setSelection(secPos + 1)
                        binding?.optionSection?.isEnabled = false
                        binding?.optionTrwStation?.setSelection(Integer.parseInt(dataJir.place))
                        binding?.optionTrwStation?.isEnabled = false
                        binding?.optionCapacity?.setSelection(Integer.parseInt(dataJir.capacity))
                        binding?.optionCapacity?.isEnabled = false
                        binding?.optionScheme?.setSelection(Integer.parseInt(dataJir.uId?.getOrNull(8).toString()))
                        binding?.optionScheme?.isEnabled = false
                       // binding.calViw.isEnabled=false
                        binding.calViw.isClickable=false
                    }
                }
            }
            // Return the root view of the binding
            return it.root
        }
        // Return null if binding is null (shouldn't happen)
        return null
    }
    /*private fun dtrBodyFound(){
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
                    binding?.tab1ViewModel?.setSelectedHTSTUD("DEF")
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
                    binding?.tab1ViewModel?.setSelectedLTSTUD("DEF")
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
                    binding?.tab1ViewModel?.setSelectedHTBussing("DEF")
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
                    binding?.tab1ViewModel?.setSelectedLTBussing("DEF")
                }
                R.id.radio_group_ltbuss_miss->{
                    binding?.tab1ViewModel?.setSelectedLTBussing("MISSING")
                }
            }
        }
    }*/

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
                if (position>0) {
                    val item = binding?.tab1ViewModel?.circles?.get(position-1)
                    if (item != null) {
                        binding?.tab1ViewModel?.onCircleSelected(item.circleId)
                    }

                }else{
                    binding?.tab1ViewModel?.onCircleSelected("")
                }
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
                if (position>0) {
                    val item = binding?.tab1ViewModel?.divisions?.get(position-1)
                    if (item != null) {
                        binding?.tab1ViewModel?.onDivisionSelect(item.divId)
                    }
                    if (viewModel.dataToSave2.value?.divisionId!=null) {
                        val divPos =
                            viewModel.getDivPosition(viewModel.dataToSave2.value?.divisionId)
                        binding?.optionDivision?.setSelection(divPos + 1)
                    }
                }else{
                    binding?.tab1ViewModel?.onDivisionSelect("")
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
                if (position>0) {
                    val item = binding?.tab1ViewModel?.subdivisions?.get(position-1)
                    if (item != null) {
                        binding?.tab1ViewModel?.onSubDivisionSelect(item.subDivId)
                    }
                }else{
                    binding?.tab1ViewModel?.onSubDivisionSelect("")
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
            binding?.optionSection?.adapter = adapter
        })

        binding?.optionSection?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //val selectedsection = parent.getItemAtPosition(position) as String
                // Handle the selected section
                if (position>0) {
                    val item = binding?.tab1ViewModel?.sections?.get(position-1)
                    if (item != null) {
                        binding?.tab1ViewModel?.onSectionSelect(item.secId)
                    }
                }else{
                    binding?.tab1ViewModel?.onSectionSelect("")
                }

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

    private fun showDatePickerDialogRD() {
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
                viewModel.setDate(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }
    override fun onSuccess(datatoshare:String) {
        Log.d("log", "hi onSuccess"+datatoshare)
        sharedViewModel.updateData(datatoshare)
        viewPager?.currentItem = 1
        //sharedViewModel?.updateData("Data from Tab 1")
    }

    override fun onFailure(value: String) {
      Log.d("error msg",value)
    }

    override fun onResume() {
        super.onResume()

    }

}