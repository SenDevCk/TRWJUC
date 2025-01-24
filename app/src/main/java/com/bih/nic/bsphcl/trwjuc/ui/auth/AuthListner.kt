package com.bih.nic.bsphcl.trwjuc.ui.auth


/**
 *Created by Chandan Singh on 1/23/2025.
 */
interface AuthListner {
    fun onStartLogin()
    fun onSuccess()
    fun onFailure(message:String)
}