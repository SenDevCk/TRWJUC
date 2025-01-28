package com.bih.nic.bsphcl.trwjuc.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel


/**
 *Created by Chandan Singh on 1/22/2025.
 */
class AuthViewModel : ViewModel() {
    var userId : String?=null
    var password : String?=null

    var authListener:AuthListner?=null

    fun loginButtonClick(view : View){
        Log.d("onclick","working userId : "+userId+" password : "+password);
        authListener?.onStartLogin()

        // Validate user input
        when {
            userId.isNullOrEmpty() -> {
                authListener?.onFailure("User ID must not be blank!")
                //return
            }
            password.isNullOrEmpty() -> {
                authListener?.onFailure("Password must not be blank!")
                //return
            }
            else -> {
                // Authentication successful
                authListener?.onSuccess()
            }
        }
    }
}