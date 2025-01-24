package com.bih.nic.bsphcl.trwjuc.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel


/**
 *Created by Chandan Singh on 1/22/2025.
 */
class AuthViewModel : ViewModel() {
    var userId : String?=null
    var password : String?=null

    var authListner:AuthListner?=null

    fun loginButtonClick(view : View){
         authListner?.onStartLogin()
        if (userId.isNullOrEmpty()||userId.isNullOrBlank()){
            authListner?.onFailure("User Id must not be blanck !")
        }else if (password.isNullOrEmpty()||password.isNullOrBlank()){
            authListner?.onFailure("Password must not be blanck !")
        }else{
            authListner?.onSuccess()
        }
    }
}