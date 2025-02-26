package com.bih.nic.bsphcl.trwjuc.fragments.tab1

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.Division
import com.bih.nic.bsphcl.trwjuc.data.FormState
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport
import com.bih.nic.bsphcl.trwjuc.data.Section
import com.bih.nic.bsphcl.trwjuc.data.Subdivision
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class Tab1ViewModel(application: Application) : AndroidViewModel(application) {
    var tab1Listner:Tab1Listner?=null
    private val _selectedCircle = MutableLiveData<String>()
    val selectedCircle: LiveData<String> get() = _selectedCircle
    private val _selectedDicision = MutableLiveData<String>()
    val selectedDivision: LiveData<String> get() = _selectedDicision
    private val _selectedSubDivision = MutableLiveData<String>()
    val selectedSubDivision: LiveData<String> get() = _selectedSubDivision

    private val _selectedSection = MutableLiveData<String>()
    val selectedSection: LiveData<String> get() = _selectedSection

     val _selectedPlace = MutableLiveData<String>()
    val selectedPlace: LiveData<String> get() = _selectedPlace
    val _selectedCapacity = MutableLiveData<String>()
    val selectedCapacity: LiveData<String> get() = _selectedCapacity
    val _selectedScheme = MutableLiveData<String>()
    val selectedScheme: LiveData<String> get() = _selectedScheme
    val trwSerialNo = MutableLiveData<String>()

    var trwUniqueCode : String?=null
    val dob= MutableLiveData<String>()
    val make= MutableLiveData<String>()
    val oilCapacity= MutableLiveData<String>()
    val oilFound= MutableLiveData<String>()
    private val appDataBase = AppDatabase.getDatabase(application)

    // LiveData to store the selected option (OK or NOT) dtrBodyFound
    private val _dtrBodySelection = MutableLiveData<String>()
    val dtrBodySelection: LiveData<String> get() = _dtrBodySelection

    private val _htStudSelection = MutableLiveData<String>()
    val htStudSelection: LiveData<String> get() = _htStudSelection

    private val _ltStudSelection = MutableLiveData<String>()
    val ltStudSelection: LiveData<String> get() = _ltStudSelection
    private val _htBushingSelection = MutableLiveData<String>()
    val htBushingSelection: LiveData<String> get() = _htBushingSelection

    private val _ltBushingSelection = MutableLiveData<String>()
    val ltBushingSelection: LiveData<String> get() = _ltBushingSelection
    val remarks= MutableLiveData<String>()
    val selectedYear = MutableLiveData<String>()
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
    var circles:List<Circle>?=null
    var divisions:List<Division>?=null
    var subdivisions:List<Subdivision>?=null
    var sections:List<Section>?=null
    private val _formState = MutableLiveData<FormState>()
    val formState: LiveData<FormState> get() = _formState



    init {
        // Example list of subdivision objects (replace with actual data)
        dob.value="--/--/----"
        selectedYear.value="----"
        fetchCircles()
        fetchDivisions()
        fetchSubDivisions()
        fetchSections()

    }





    // Method to update the selected radio button (when user changes selection)

    private fun fetchCircles() {
        viewModelScope.launch(Dispatchers.IO) { // ✅ Run database call on background thread
            circles = appDataBase?.circleDao()?.getAll() ?: emptyList()
            withContext(Dispatchers.Main) {
                _circleList.value =listOf("--Select Circle--" )+ (circles?.map { it?.cirName.toString() }?: emptyList()) // ✅ Update UI on main thread
            }
        }
    }
    private fun fetchDivisions() {
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            divisions = appDataBase?.divisionDao()?.getAllDivision() ?: emptyList()
            withContext(Dispatchers.Main) {
                _divisionList.value =listOf("--Select Division--" )+ (divisions?.map { it?.divName.toString() }?: emptyList()) // Assuming `Circle` has a `name` property
            }
        }
    }
    private fun fetchDivisionsByCircle(mcircle:String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            divisions = appDataBase?.divisionDao()?.getAllDivisionFromCircle(mcircle) ?: emptyList()
            withContext(Dispatchers.Main) {
                _divisionList.value =listOf("--Select Division--" )+ (divisions?.map { it?.divName.toString() }?: emptyList())
            }// Assuming `Circle` has a `name` property
        }
    }


    private fun fetchSubDivisionsByDivision(division:String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            subdivisions = appDataBase?.subDivisionDao()?.getSubdivisionByDivision(division) ?: emptyList()
            withContext(Dispatchers.Main) {
                _subdivisionList.value =
                    listOf("--Select Subdivision--" )+
                            (subdivisions?.map { it?.subDivName.toString() }?: emptyList())
                // Assuming `Circle` has a `name` property
            }
        }
    }

    private fun fetchSubDivisionsByCircle(circle:String) {
        viewModelScope.launch(Dispatchers.IO){
            // Launch a coroutine to fetch data asynchronously
            subdivisions = appDataBase?.subDivisionDao()?.getSubdivisionByCircle(circle) ?: emptyList()
            withContext(Dispatchers.Main) {
                _subdivisionList.value =
                    listOf("--Select Subdivision--" )+
                            (subdivisions?.map { it?.subDivName.toString() }?: emptyList())// Assuming `Circle` has a `name` property
            }
        }
    }

    private fun fetchSubDivisions() {
        viewModelScope.launch(Dispatchers.IO){
            // Launch a coroutine to fetch data asynchronously
            subdivisions = appDataBase?.subDivisionDao()?.getAllSubDivision() ?: emptyList()
            withContext(Dispatchers.Main) {
                _subdivisionList.value =
                    listOf("--Select Subdivision--" )+
                            (subdivisions?.map { it?.subDivName.toString() }?: emptyList()) // Assuming `Circle` has a `name` property
            }
        }
    }

    private fun fetchSections() {
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            sections = appDataBase?.sectionDao()?.getAllSection() ?: emptyList()
            withContext(Dispatchers.Main) {
                _sectionList.value =listOf("--Select Section--" )+
                        (sections?.map { it?.secName.toString() }?: emptyList())// Assuming `Circle` has a `name` property
            }
        }
    }

    private fun fetchSectionsByCircle(circle : String){
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            sections = appDataBase?.sectionDao()?.getAllSectionByCircle(circle) ?: emptyList()
            withContext(Dispatchers.Main) {
                _sectionList.value =listOf("--Select Section--" )+
                        (sections?.map { it?.secName.toString() }?: emptyList()) // Assuming `Circle` has a `name` property
            }
        }
    }

    private fun fetchSectionsByDivision(division : String){
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            sections = appDataBase?.sectionDao()?.getAllSectionByDivision(division) ?: emptyList()
            withContext(Dispatchers.Main) {
                _sectionList.value =listOf("--Select Section--" )+
                        (sections?.map { it?.secName.toString() }?: emptyList()) // Assuming `Circle` has a `name` property
            }
        }
    }

    private fun fetchSectionsBySubDivision(subdiv : String){
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            sections = appDataBase?.sectionDao()?.getAllSectionBySubdiv(subdiv) ?: emptyList()
            withContext(Dispatchers.Main) {
                _sectionList.value =listOf("--Select Section--" )+
                        (sections?.map { it?.secName.toString() }?: emptyList())// Assuming `Circle` has a `name` property
            }
        }
    }

    // Method to handle circle selection
    fun onCircleSelected(selectedCircle: String) {
        Log.d("selectedCircle",selectedCircle)
        _selectedCircle.value = selectedCircle
        fetchDivisionsByCircle(_selectedCircle.value.toString())
        fetchSubDivisionsByCircle(_selectedCircle.value.toString())
        fetchSectionsByCircle(_selectedCircle.value.toString())
    }

    fun onDivisionSelect(selectedDivId: String) {
        Log.d("selectedDivId",selectedDivId)
        _selectedDicision.value = selectedDivId
        fetchSubDivisionsByDivision(_selectedDicision.value.toString())
        fetchSectionsByDivision(_selectedDicision.value.toString())
    }

    fun onSubDivisionSelect(selectedSubDivId: String) {
        Log.d("selectedSubDivId",selectedSubDivId)
        _selectedSubDivision.value = selectedSubDivId
        fetchSectionsBySubDivision(_selectedSubDivision.value.toString())
    }

    fun onSectionSelect(selectedSecId: String) {
        Log.d("selectedSecId",selectedSecId)
        _selectedSection.value = selectedSecId
    }

    fun setSelectedDtr(option: String) {
        _dtrBodySelection.value = option
    }

    fun setSelectedHTSTUD(option: String) {
        _htStudSelection.value = option
    }

    fun setSelectedLTSTUD(option: String) {
        _ltStudSelection.value = option
    }

    fun setSelectedLTBussing(option: String) {
        _ltBushingSelection.value = option
    }

    fun setSelectedHTBussing(option: String) {
        _htBushingSelection.value = option
    }

    fun setSelectedYear(year: Int) {
        selectedYear.value = String.format("%d",year)
    }
    fun onNextButtonClicked(view :View){
        Log.d("log","onNextButtonClicked Coming....")
        validateForm()
        if (_formState.value?.isValid == true){
            val dataToSave=JointInspectionReport(
                trwUniqueCode?.takeIf { it.isNotBlank() } ?: "",
                _selectedCircle.value,
                selectedDivision.value,
                _selectedSubDivision.value,
                _selectedSection.value,
                selectedPlace.value,
                dob.value,
                selectedCapacity.value,
                selectedYear.value?.trim(),
                make.value,
                oilCapacity.value,
                oilFound.value,
                _dtrBodySelection.value,
                _htStudSelection.value,
                _ltStudSelection.value,
                _htBushingSelection.value,
                _ltBushingSelection.value,
                remarks.value
            );
            viewModelScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        appDataBase?.jointInspectionReportDao()?.insert(dataToSave)
                    }
                    Log.d("log", "Report inserted successfully")
                } catch (e: Exception) {
                    Log.e("onSuccess", "Error inserting report: ${e.message}")
                    // Optionally show a toast or other error handling UI
                }
            }
            trwUniqueCode?.trim()?.let { tab1Listner?.onSuccess(it) }
        }else{
            _formState.value?.errorMessage?.let { tab1Listner?.onFailure(it) }
        }
    }
    fun validateForm() {
        when {
            trwSerialNo.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Enter TRW Unique Code")
            }
            _selectedCircle.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Circle Should be Selected")
            }
            _selectedDicision.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Division Should be Selected")
            }
            _selectedSubDivision.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "SubDivision Should be Selected")
            }
            _selectedSection.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Section should be Selected")
            }
            _selectedPlace.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select Trw Place")
            }
            dob.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select date of burning")
            }
            dob.value.equals("--/--/----") -> {
                _formState.value = FormState(errorMessage = "Select date of burning")
            }
            _selectedCapacity.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select Capacity")
            }
            _selectedScheme.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select Scheme")
            }
            selectedYear.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select Manufacturing year")
            }
            selectedYear.value.equals("----") -> {
                _formState.value = FormState(errorMessage = "Year of Manufacturing must be 4 digits long")
            }
            make.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Enter Make")
            }
            oilCapacity.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Enter Oil Capacity")
            }
            oilFound.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Enter Oil Found")
            }
            _dtrBodySelection.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select DTR Body Found")
            }
            _htStudSelection.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select HT Stud")
            }
            _ltStudSelection.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select LT Stud")
            }
            _htBushingSelection.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select HT Bussing")
            }
            _ltBushingSelection.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Select LT Bussing")
            }
            remarks.value.isNullOrBlank() -> {
                _formState.value = FormState(errorMessage = "Enter Remarks")
            }
            else -> {

                trwUniqueCode=StringBuilder().append(if (selectedPlace.value?.length == 1) "0${selectedPlace.value}" else selectedPlace.value)
                    .append(trwSerialNo.value)
                    .append(_selectedCapacity.value)
                    .append(_selectedScheme.value)
                    .append(selectedYear.value?.substring(selectedYear.value?.length?.minus(2) ?: 0))
                    .toString()
                _formState.value = FormState(isValid = true)
            }
        }
    }
    fun setDate(date: String) {
        dob.value=date
    }

}