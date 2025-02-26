package com.bih.nic.bsphcl.trwjuc.ui.joint_inspection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bih.nic.bsphcl.trwjuc.data.JointInspectionReport
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *Created by Chandan Singh on 2/25/2025.
 */
class JointInspectionViewModel(application: Application) : AndroidViewModel(application){
    var jointInspectionHandler: JointInspectionHandler?=null
    var appDataBase : AppDatabase?=null
    private val _jointInspectionData = MutableLiveData<List<JointInspectionReport>>()
    val jointInspectionData: LiveData<List<JointInspectionReport>> get() = _jointInspectionData
    init {
        // Example list of subdivision objects (replace with actual data)
        appDataBase= Room.databaseBuilder(
            application,
            AppDatabase::class.java, "trw_db"
        ).build()
        viewModelScope.launch {
            _jointInspectionData.value=
                withContext(Dispatchers.IO) {
                    appDataBase?.jointInspectionReportDao()?.getAll()
                }!!
        }


    }

}