package com.bih.nic.bsphcl.trwjuc.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityLoginBinding
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthListner
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthViewModel
import com.bih.nic.bsphcl.trwjuc.utils.showPermissionExplanationUI
import com.bih.nic.bsphcl.trwjuc.utils.toast

class LoginActivity : AppCompatActivity(),AuthListner {
    var deviceId:String?=null
    var binding :ActivityLoginBinding?=null
    var viewModel : AuthViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =DataBindingUtil.setContentView(this,R.layout.activity_login)
        // Get the ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        // Set the ViewModel in the binding (optional, depending on if you are using data binding for ViewModel)
        binding?.userViewModel = viewModel
        binding?.lifecycleOwner = this
        // Set AuthListener for ViewModel
        viewModel?.authListener = this
    }

    override fun onResume() {
        super.onResume()
        checkPermission()
    }
    override fun onStartLogin(){
        //TODO("Not yet implemented")
        Log.d("log","onStartLogin")
        //toast("onStartLogin")
    }
    override fun onSuccess() {
        //TODO("Not yet implemented")
        Log.d("log","onSuccess")
        //toast("onSuccess")
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

    override fun onFailure(message: String) {
        //TODO("Not yet implemented")
        Log.d("log","onSuccess : "+message)
        toast(message)
    }

    fun checkPermission(){
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED -> {
                getADeviceId()
                binding?.textDeviceId?.text=deviceId
                binding?.textDeviceId?.visibility=View.GONE
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.READ_PHONE_STATE) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                showPermissionExplanationUI(this)
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_PHONE_STATE)
            }
        }
    }
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                getADeviceId()
                binding?.textDeviceId?.text=deviceId
                binding?.textDeviceId?.visibility=View.GONE
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                finish()
            }
        }
    fun getADeviceId(){
        try {
            deviceId = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
            println("Device ID: $deviceId")
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

}