package com.bih.nic.bsphcl.trwjuc.fragments.tab3

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import kotlinx.coroutines.launch


/**
 *Created by Chandan Singh on 2/14/2025.
 */
class Tab3ViewModel(application: Application) : ViewModel(){
    var tab3Listner: Tab3Listner?=null
    var appDataBase : AppDatabase?=null

    // List of materials (options for the AutoCompleteTextView)
    private val _materialList = MutableLiveData<List<String>>()
    val materialList: LiveData<List<String>> get() = _materialList

    // Selected material (this will be bound to the AutoCompleteTextView)
    val selectedMaterial: MutableLiveData<String> = MutableLiveData()
    init {
        // Example list of subdivision objects (replace with actual data)
        appDataBase= Room.databaseBuilder(
            application,
            AppDatabase::class.java, "trw_db"
        ).build()
        populateMaterialList()
    }

    private fun populateMaterialList() {
        viewModelScope.launch {
            // Launch a coroutine to fetch data asynchronously
            val circles = appDataBase?.jobwiseMatUtilDao()?.getAllMatSeg() ?: emptyList()
            _materialList.value = circles.map { it?.materialName.toString() } // Assuming `Circle` has a `name` property
        }
    }

    fun onMaterialSelected(material: String) {
        selectedMaterial.value = material
    }

}