package com.bih.nic.bsphcl.trwjuc.fragments.tab2

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1Listner


/**
 *Created by Chandan Singh on 2/13/2025.
 */
class Tab2ViewModel(application: Application): AndroidViewModel(application) {

    var tab2Listner: Tab2Listner?=null
    // LiveData to observe the selected date
    private val _dateOfReceiving = MutableLiveData<String>()
    val dateOfReceiving: LiveData<String> get() = _dateOfReceiving
    private val _dateOfTesting = MutableLiveData<String>()
    val dateOfTesting: LiveData<String> get() = _dateOfTesting

    private val _dateOfSVR = MutableLiveData<String>()
    val dateOfSvr: LiveData<String> get() = _dateOfSVR
    private val _dateOfIssue = MutableLiveData<String>()
    val dateOfIssue: LiveData<String> get() = _dateOfIssue
    // Event to trigger showing DatePickerDialog
    private val _showDatePicker = MutableLiveData<Boolean>()
    val showDatePicker: LiveData<Boolean> get() = _showDatePicker

    private val _showDatePickerDT = MutableLiveData<Boolean>()
    val showDatePickerDT: LiveData<Boolean> get() = _showDatePickerDT
    private val _showDatePickerSVR = MutableLiveData<Boolean>()
    val showDatePickerSVR: LiveData<Boolean> get() = _showDatePickerSVR

    private val _showDatePickerIssue = MutableLiveData<Boolean>()
    val showDatePickerIssue: LiveData<Boolean> get() = _showDatePickerIssue



    val place:String?=null
    val capacity:String?=null
    val svrNo:String?=null
    val gatePassNo:String?=null
    val sivCdtNo:String?=null
    val issuedTo:String?=null
    val remarks:String?=null
    init {
        // Set default value
        _dateOfReceiving.value = "--/--/----"
        _dateOfTesting.value = "--/--/----"
        _dateOfSVR.value = "--/--/----"
        _dateOfIssue.value = "--/--/----"
    }
    // Update the date value
    fun setDate(date: String,fag: String) {
        when (fag){
            "RD"->_dateOfReceiving.value = date
            "DT"->_dateOfTesting.value = date
            "DSVR"->_dateOfSVR.value = date
            "DISSUE"->_dateOfIssue.value = date
        }

    }

    // Reset the DatePicker event
    fun resetDatePickerEvent(fag: String) {
        when (fag) {
            "RD"->_showDatePicker.value = false
            "DT"->_showDatePickerDT.value = false
            "DSVR"->_showDatePickerSVR.value = false
            "DISSUE"->_showDatePickerIssue.value = false
        }
    }
}