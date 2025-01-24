package com.bih.nic.bsphcl.trwjuc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityLoginBinding
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthListner
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthViewModel

class LoginActivity : AppCompatActivity(),AuthListner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding :ActivityLoginBinding =DataBindingUtil.setContentView(this,R.layout.activity_login)
        // Get the ViewModel using ViewModelProvider
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        // Set the ViewModel in the binding (optional, depending on if you are using data binding for ViewModel)
        binding.userViewModel = viewModel
        binding.lifecycleOwner = this
    }
    override fun onStartLogin(){
        TODO("Not yet implemented")
    }
    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}