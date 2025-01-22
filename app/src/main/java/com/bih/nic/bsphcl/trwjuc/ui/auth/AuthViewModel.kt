package com.bih.nic.bsphcl.trwjuc.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel


/**
 *Created by Chandan Singh on 1/22/2025.
 */
class AuthViewModel : ViewModel() {
    var userId : String?=null
    var password : String?=null

    fun login(view : View){
        if (userId.isNullOrEmpty()||userId.isNullOrBlank()){

        }else if (password.isNullOrEmpty()||password.isNullOrBlank()){

        }else{

        }
    }
}