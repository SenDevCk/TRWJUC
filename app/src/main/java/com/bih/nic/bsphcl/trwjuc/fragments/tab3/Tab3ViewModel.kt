package com.bih.nic.bsphcl.trwjuc.fragments.tab3

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bih.nic.bsphcl.trwjuc.data.FormState
import com.bih.nic.bsphcl.trwjuc.data.JobwiseMaterialUtilizationSegment
import com.bih.nic.bsphcl.trwjuc.data.MaterialUtilized
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *Created by Chandan Singh on 2/14/2025.
 */
class Tab3ViewModel(application: Application) : AndroidViewModel(application){
    var tab3Listner: Tab3Listner?=null
    var appDataBase : AppDatabase?=null

    // List of materials (options for the AutoCompleteTextView)
    private val _materialList = MutableLiveData<List<String>>()
    val materialList: LiveData<List<String>> get() = _materialList
    val _materialSelected = MutableLiveData<Int>()
    val materialSelected: LiveData<Int> get() = _materialSelected
    // Selected material (this will be bound to the AutoCompleteTextView)
    val selectedMaterial: MutableLiveData<String> = MutableLiveData()
    var size= MutableLiveData<String>()
    var weight= MutableLiveData<String>()
    var materials : List<JobwiseMaterialUtilizationSegment>?=null
    var trwNo= MutableLiveData<String>()
    private val _formState = MutableLiveData<FormState>()
    val formState: LiveData<FormState> get() = _formState
    private val _materialUtilized1 = MutableLiveData<List<MaterialUtilized>>()
    val materialUtilized1: LiveData<List<MaterialUtilized>> get() = _materialUtilized1
    init {
        // Example list of subdivision objects (replace with actual data)
        trwNo.value="No data Found"
        appDataBase= Room.databaseBuilder(
            application,
            AppDatabase::class.java, "trw_db"
        ).build()
        populateMaterialList()
        viewModelScope.launch {
            _materialUtilized1.value = withContext(Dispatchers.IO) {
                appDataBase?.materialUtilizedDaoDao()?.getAllMaterialUtilized()
            }!!
        }


    }

    private fun populateMaterialList() {
        viewModelScope.launch(Dispatchers.IO) {
            // Launch a coroutine to fetch data asynchronously
            materials = appDataBase?.jobwiseMatUtilDao()?.getAllMatSeg() ?: emptyList()
            withContext(Dispatchers.Main) {
                _materialList.value =
                    materials?.map { it?.materialName.toString() } // Assuming `Circle` has a `name` property
            }
        }
    }

    fun onMaterialSelected(material: String) {
        val material = materials?.find { it.materialName == material }
        _materialSelected.value=material?.id
    }

    fun saveData(view :View){
        Log.d("saveData","saveDataClicked fragmet 3")
        validateForm()
        if (_formState.value?.isValid == true){

                 var materialUtilized2 : MaterialUtilized=
                     trwNo.value?.let {
                         MaterialUtilized( String.format("%d",_materialSelected.value), it,
                             size.value,weight.value)
                     }!!
                    viewModelScope.launch {
                        appDataBase?.materialUtilizedDaoDao()?.insertAll(materialUtilized2)
                        _materialUtilized1.value=appDataBase?.materialUtilizedDaoDao()?.getAllMaterialUtilized()
                    }

        }

    }
    fun validateForm(){
        if(_materialSelected.value==0){
            _formState.value = FormState(errorMessage = "Select Material")
        }else if (weight.value.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter value")
        }else if (!(_materialSelected?.value == 11 || _materialSelected?.value == 12) && size.value.isNullOrBlank()){
            _formState.value = FormState(errorMessage = "Enter size")
        }else{
            _formState.value = FormState(isValid = true)
        }
    }
}