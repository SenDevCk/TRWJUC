package com.bih.nic.bsphcl.trwjuc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isGone
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.retrofit.DataApi
import com.bih.nic.bsphcl.trwjuc.retrofit.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    var progressBar: ProgressBar?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressBar =findViewById<ProgressBar>(R.id.progress_bar)

    }

    fun getCircle(){
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = dataApi.getCircle()
            if (result != null)
            // Checking the results
                progressBar?.isGone
            Log.d("chandan: ", result.body().toString())
        }
    }

    fun getDivision(){
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = dataApi.getDivision()
            if (result != null)
            // Checking the results
                progressBar?.isGone
            Log.d("chandan: ", result.body().toString())
        }
    }

    fun getSubDivision(){
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = dataApi.getSubdivision()
            if (result != null)
            // Checking the results
                progressBar?.isGone
            Log.d("chandan: ", result.body().toString())
        }
    }

    fun getSection(){
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = dataApi.getSection()
            if (result != null)
            // Checking the results
                progressBar?.isGone
            Log.d("chandan: ", result.body().toString())
        }
    }
}