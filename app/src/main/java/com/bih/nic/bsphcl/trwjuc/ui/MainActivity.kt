package com.bih.nic.bsphcl.trwjuc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityMainBinding
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthViewModel
import com.bih.nic.bsphcl.trwjuc.ui.main.MainClickHandler
import com.bih.nic.bsphcl.trwjuc.ui.main.MainViewModel

class MainActivity : AppCompatActivity(), MainClickHandler {
    //var toolbar:Toolbar?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Set the ViewModel in the binding (optional, depending on if you are using data binding for ViewModel)
        binding.mainViewHolder = viewModel
        binding.lifecycleOwner = this
        // Set AuthListener for ViewModel
        viewModel.mainClickHandler = this
//        toolbar=findViewById<Toolbar>(R.id.toolbar_sel_main)
//        toolbar.title=""

    }

    override fun applyNewClick() {
//        TODO("Not yet implemented")
        val intent = Intent(this, ApplyForNewUC::class.java)
        this.startActivity(intent)
    }

}