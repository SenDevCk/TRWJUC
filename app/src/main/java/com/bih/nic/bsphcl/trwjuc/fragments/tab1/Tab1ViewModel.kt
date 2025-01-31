package com.bih.nic.bsphcl.trwjuc.fragments.tab1

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo

class Tab1ViewModel : ViewModel() {
    var tab1Listner:Tab1Listner?=null
    val circle: String? = null
    val division: String? = null
    val subDiv: String? = null
    val section: String? = null
    val place: String? = null
    val dob: String? = null
    val capacity: String? = null
    val yearOfManufacturing: String? = null
    val make: String? = null
    val oilCapacity: String? = null
    val oilFound: String? = null
    // LiveData to store the selected option (OK or NOT) dtrBodyFound
    private val _dtrBodySelection = MutableLiveData<String>()
    val dtrBodySelection: LiveData<String> get() = _dtrBodySelection


    val htStud: String? = null
    val ltStud: String? = null
    val htBushing: String? = null
    val ltBushing: String? = null
    val remarks: String? = null
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
        _circleList.value = listOf(
            "Select Circle",
            "Circle 1",
            "Circle 2",
           "Circle 3"
        )
        _divisionList.value = listOf(
            "Select Division",
            "Division 1",
            "Division 2",
            "Division 3"
        )
        _subdivisionList.value = listOf(
            "Select Subdivision",
            "Subdivision 1",
            "Subdivision 2",
            "Subdivision 3"
        )
        _sectionList.value = listOf(
            "Select Section",
            "Section 1",
            "Section 2",
            "Section 3"
        )
    }

    data class Subdivision(val name: String, val id: Int)
    fun onNextButtonClicked(view :View){
      Log.d("log","onNextButtonClicked Coming....")
        tab1Listner?.onSuccess()

    }

    // Method to update the selected radio button (when user changes selection)
    fun onRadioButtonSelected(selectedOption: String) {
        Log.d("selectedOption : ",""+selectedOption)
        _dtrBodySelection.value = selectedOption
    }
}