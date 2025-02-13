package com.bih.nic.bsphcl.trwjuc.fragments.tab1

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import kotlinx.coroutines.launch

class Tab1ViewModel(application: Application) : ViewModel() {
    var tab1Listner:Tab1Listner?=null
    private val _selectedCircle = MutableLiveData<String>()
    val selectedCircle: LiveData<String> get() = _selectedCircle
    private val _selectedDicision = MutableLiveData<String>()
    val selectedDivision: LiveData<String> get() = _selectedDicision
    private val _selectedSubDicision = MutableLiveData<String>()
    val selectedSubDivision: LiveData<String> get() = _selectedSubDicision

    private val _selectedSection = MutableLiveData<String>()
    val selectedSection: LiveData<String> get() = _selectedSection
    val place: String? = null
    val dob: String? = null
    val capacity: String? = null
    val yearOfManufacturing: String? = null
    val make: String? = null
    val oilCapacity: String? = null
    val oilFound: String? = null
    var appDataBase : AppDatabase?=null
    // LiveData to store the selected option (OK or NOT) dtrBodyFound
    private val _dtrBodySelection = MutableLiveData<String>()
    val dtrBodySelection: LiveData<String> get() = _dtrBodySelection
    val htStud: String? = null
    val ltStud: String? = null
    val htBushing: String? = null
    val ltBushing: String? = null
    val remarks: String? = null
    private val _selectedYear = MutableLiveData<Int>()
    val selectedYear: LiveData<Int> get() = _selectedYear
    //circleList
    private val _circleList = MutableLiveData<List<String>>()
    val circleList: LiveData<List<String>> get() = _circleList
    //divisionList
    private val _divisionList = MutableLiveData<List<String>>()
    val divisionList: LiveData<List<String>> get() = _divisionList
    //subdivisionlist
    private val _subdivisionList = MutableLiveData<List<String>>()
    val subdivisionList: LiveData<List<String>> get() = _subdivisionList
    //subdivisionlist
    private val _sectionList = MutableLiveData<List<String>>()
    val sectionList: LiveData<List<String>> get() = _sectionList

    init {
        // Example list of subdivision objects (replace with actual data)
        appDataBase= Room.databaseBuilder(
            application,
            AppDatabase::class.java, "trw_db"
        ).build()
        fetchCircles()
        fetchDivisions()
        fetchSubDivisions()
        fetchSections()
//        _circleList.value = listOf(
//            "Select Circle",
//            "Circle 1",
//            "Circle 2",
//           "Circle 3"
//        )
//        _divisionList.value = listOf(
//            "Select Division",
//            "Division 1",
//            "Division 2",
//            "Division 3"
//        )
//        _subdivisionList.value = listOf(
//            "Select Subdivision",
//            "Subdivision 1",
//            "Subdivision 2",
//            "Subdivision 3"
//        )
//        _sectionList.value = listOf(
//            "Select Section",
//            "Section 1",
//            "Section 2",
//            "Section 3"
//        )
    }



    fun onNextButtonClicked(view :View){
      Log.d("log","onNextButtonClicked Coming....")
        tab1Listner?.onSuccess()

    }

    // Method to update the selected radio button (when user changes selection)

    private fun fetchCircles() {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.circleDao()?.getAll() ?: emptyList()
            _circleList.value = circles.map { it?.circleName.toString() } // Assuming `Circle` has a `name` property
        }
    }
    private fun fetchDivisionsByCircle(mcircle:String) {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.divisionDao()?.getAllDivisionFromCircle(mcircle) ?: emptyList()
            _circleList.value = circles.map { it?.divName.toString() } // Assuming `Circle` has a `name` property
        }
    }
    private fun fetchDivisions() {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.divisionDao()?.getAllDivision() ?: emptyList()
            _circleList.value = circles.map { it?.divName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSubDivisionsByDivision(division:String) {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.subDivisionDao()?.getSubdivisionByDivision(division) ?: emptyList()
            _circleList.value = circles.map { it?.subDivName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSubDivisionsByCircle(circle:String) {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.subDivisionDao()?.getSubdivisionByCircle(circle) ?: emptyList()
            _circleList.value = circles.map { it?.subDivName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSubDivisions() {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.subDivisionDao()?.getAllSubDivision() ?: emptyList()
            _circleList.value = circles.map { it?.subDivName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSections() {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val sections = appDataBase?.sectionDao()?.getAllSection() ?: emptyList()
            _sectionList.value = sections.map { it?.secName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSectionsByCircle(circle : String){
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val sections = appDataBase?.sectionDao()?.getAllSectionByCircle(circle) ?: emptyList()
            _sectionList.value = sections.map { it?.secName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSectionsByDivision(division : String){
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val sections = appDataBase?.sectionDao()?.getAllSectionByDivision(division) ?: emptyList()
            _sectionList.value = sections.map { it?.secName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    private fun fetchSectionsBySubDivision(subdiv : String){
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val sections = appDataBase?.sectionDao()?.getAllSectionBySubdiv(subdiv) ?: emptyList()
            _sectionList.value = sections.map { it?.secName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    // Method to handle circle selection
    fun onCircleSelected(selectedCircle: String) {
        _selectedCircle.value = selectedCircle
        fetchDivisionsByCircle(_selectedCircle.value.toString())
        fetchSubDivisionsByCircle(_selectedCircle.value.toString())
        fetchSectionsByCircle(_selectedCircle.value.toString())
    }

    fun onDivisionSelect(selectedDiv: String) {
        _selectedDicision.value = selectedDiv
        fetchSubDivisionsByDivision(_selectedDicision.value.toString())
        fetchSectionsByDivision(_selectedDicision.value.toString())
    }

    fun onSubDivisionSelect(selectedSubDiv: String) {
        _selectedSubDicision.value = selectedSubDiv
        fetchSectionsBySubDivision(_selectedSubDicision.value.toString())
    }

    fun onSectionSelect(selectedSec: String) {
        _selectedSection.value = selectedSec
    }

    fun setSelectedDtr(option: String) {
        _dtrBodySelection.value = option
    }

    fun setSelectedHTSTUD(option: String) {
        _dtrBodySelection.value = option
    }
    fun setSelectedYear(year: Int) {
        _selectedYear.value = year
    }
}