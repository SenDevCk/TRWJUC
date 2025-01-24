package com.bih.nic.bsphcl.trwjuc.utils

import android.content.Context
import android.widget.Toast


/**
 *Created by Chandan Singh on 1/23/2025.
 */
fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show();
}