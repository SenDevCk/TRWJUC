package com.bih.nic.bsphcl.trwjuc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.adapters.JointInspectionAdapter
import com.bih.nic.bsphcl.trwjuc.adapters.MaterialAdapter
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityJointInspectionBinding
import com.bih.nic.bsphcl.trwjuc.databinding.ActivityLoginBinding
import com.bih.nic.bsphcl.trwjuc.ui.auth.AuthViewModel
import com.bih.nic.bsphcl.trwjuc.ui.joint_inspection.JointInspectionHandler
import com.bih.nic.bsphcl.trwjuc.ui.joint_inspection.JointInspectionViewModel

class JointInspectionActivity : AppCompatActivity(), JointInspectionHandler {

    var binding : ActivityJointInspectionBinding?=null
    var viewModel : JointInspectionViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val toolbar = findViewById<Toolbar>(R.id.toolbar_jilist)


        binding  = DataBindingUtil.setContentView(this,R.layout.activity_joint_inspection)
        // Get the ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this).get(JointInspectionViewModel::class.java)

        // Set the ViewModel in the binding (optional, depending on if you are using data binding for ViewModel)
        binding?.jointViewModel = viewModel
        binding?.lifecycleOwner = this
        // Set AuthListener for ViewModel
        viewModel?.jointInspectionHandler = this
        setSupportActionBar(binding?.toolbarJilist)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Joint Inspection List"
        viewModel?.jointInspectionData?.observe(this, {
            // important part: initialize the adapter only once
            val jointInsAdapter = JointInspectionAdapter(this, it) // Pass only the data
            binding?.jointinsLst?.layoutManager = LinearLayoutManager(this)
            binding?.jointinsLst?.adapter = jointInsAdapter

            // Optional: Update the result number text change
//                val productNumber = materialList.size.toString()
//                binding.textViewResultNumber.text = "$productNumber sonuç bulundu"
        })
    }

    override fun onSuccess() {
       //implimentation
    }

    override fun onFailure(message: String) {
        //implimentation
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Close the activity and navigate back
        return true
    }
}