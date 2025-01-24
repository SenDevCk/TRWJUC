package com.bih.nic.bsphcl.trwjuc.ui.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.bih.nic.bsphcl.trwjuc.ui.LoginActivity


/**
 *Created by Chandan Singh on 1/23/2025.
 */
class MainViewModel: ViewModel() {

     var mainClickHandler: MainClickHandler?=null
    fun ApplyButtonClick(view : View){
        mainClickHandler?.applyNewClick()
    }
}