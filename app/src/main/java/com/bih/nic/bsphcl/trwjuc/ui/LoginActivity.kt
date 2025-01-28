package com.bih.nic.bsphcl.trwjuc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityLoginBinding
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthListner
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthViewModel
import com.bih.nic.bsphcl.trwjuc.utils.toast

class LoginActivity : AppCompatActivity(),AuthListner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :ActivityLoginBinding =DataBindingUtil.setContentView(this,R.layout.activity_login)
        // Get the ViewModel using ViewModelProvider
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        // Set the ViewModel in the binding (optional, depending on if you are using data binding for ViewModel)
        binding.userViewModel = viewModel
        binding.lifecycleOwner = this
        // Set AuthListener for ViewModel
        viewModel.authListener = this
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
}