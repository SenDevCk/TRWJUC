package com.bih.nic.bsphcl.trwjuc.fragments.tab2

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bih.nic.bsphcl.trwjuc.data.FormState
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport
import com.bih.nic.bsphcl.trwjuc.data.MasterRegisterDetails
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import com.bih.nic.bsphcl.trwjuc.fragments.tab1.Tab1Listner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *Created by Chandan Singh on 2/13/2025.
 */
class Tab2ViewModel(application: Application): AndroidViewModel(application) {
    private val appDataBase = AppDatabase.getDatabase(application)
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

    private val _formState = MutableLiveData<FormState>()
    val formState: LiveData<FormState> get() = _formState

    val place:String?=null
    val capacity:String?=null
    val svrNo:String?=null
    val gatePassNo:String?=null
    val sivCdtNo:String?=null
    val issuedTo:String?=null
    val remarks:String?=null
    val trwCode:String?=null
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

    fun onNextButtonClicked(view :View){
        Log.d("log","onNextButtonClicked Coming....")
        validateForm()
        if (_formState.value?.isValid == true){
            val dataToSave= trwCode?.let {
                MasterRegisterDetails(
                    it,
                    dateOfReceiving.value,
                    place,
                    capacity,
                    dateOfTesting.value,
                    svrNo,
                    dateOfSvr.value,
                    dateOfIssue.value,
                    gatePassNo,
                    sivCdtNo,
                    issuedTo,
                    remarks
                )
            };
            viewModelScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        if (dataToSave != null) {
                            appDataBase.masterRegisterDao().insertAll(dataToSave)
                        }else{
                            Log.e("log", "dataToSave is null on tab2 view model")
                        }
                    }
                    Log.d("log", "Report inserted successfully")
                } catch (e: Exception) {
                    Log.e("onSuccess", "Error inserting report: ${e.message}")
                    // Optionally show a toast or other error handling UI
                }
            }
            tab2Listner?.onSuccess()
        }else{
            _formState.value?.errorMessage?.let { tab2Listner?.onFailure(it) }
        }
    }

    private fun validateForm() {
        if (_dateOfReceiving.value.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Select Date of Receiving")
        }
        else if (place.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter Place")
        }
        else if (capacity.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter Capacity")
        }
        else if (_dateOfTesting.value.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Select Date of Testing")
        }
        else if (svrNo.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter SVR No")
        }
        else if (_dateOfSVR.value.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Select date of SVR")
        }
        else if (_dateOfIssue.value.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Select date of Issue")
        }
        else if (gatePassNo.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter Gate pass no.")
        }
        else if (sivCdtNo.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter SIV/CDT no.")
        }
        else if (issuedTo.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter Issued to")
        }
        else if (remarks.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter Remarks")
        }else{
            _formState.value = FormState(isValid = true)
        }
    }
}