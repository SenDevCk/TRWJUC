package com.bih.nic.bsphcl.trwjuc.ui.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *Created by Chandan Singh on 2/17/2025.
 */

class SharedViewModel : ViewModel() {

    // MutableLiveData for the data you want to share
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> get() = _data

    // Function to update the data
    fun updateData(newData: String) {
        _data.value = newData
    }
}
