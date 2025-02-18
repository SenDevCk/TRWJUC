package com.bih.nic.bsphcl.trwjuc.ui

import CommanPref
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bih.nic.bsphcl.trwjuc.R
import com.bih.nic.bsphcl.trwjuc.databases.AppDatabase
import com.bih.nic.bsphcl.trwjuc.retrofit.DataApi
import com.bih.nic.bsphcl.trwjuc.retrofit.RetrofitHelper
import com.bih.nic.bsphcl.trwjuc.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {

    var progressBar: ProgressBar?=null
    private val appDataBase = AppDatabase.getDatabase(application)
    var progressMessage : TextView?=null
    var sessionData: CommanPref?=null
    val handler = Handler(Looper.getMainLooper())
    override fun onStart() {
        super.onStart()
        sessionData = CommanPref.getInstance(applicationContext)
        sessionData?.saveData("appdata", "N")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.progressBar =findViewById<ProgressBar>(R.id.progress_bar)
        this.progressMessage = findViewById<TextView>(R.id.progress_message)
        val appData = sessionData?.getData("appdata")?.trim()
        if (appData.equals("N", ignoreCase = true)) {
            loadData()
        } else {
            start()
        }

    }

    private fun loadData() {
        getCircle()
        getDivision()
        getSubDivision()
        getSection()
        getJobWiseMatUtilizationSeg()
    }

    private fun getCircle() {
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) { showProgressBar("Loading Circle....") }
                val result = dataApi.getCircle()

                if (result.isSuccessful) {
                    result.body()?.let { circleList ->
                        val countCir = appDataBase?.circleDao()?.countCircle() ?: 0
                        if (countCir <= 0) {
                            appDataBase?.circleDao()?.insertAll(*circleList.toTypedArray())
                        }
                    }
                } else {
                    Log.d("chandan:", "Error: ${result.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("chandan:", "Error: ${e.message}")
            } finally {
                withContext(Dispatchers.Main) { hideProgressBar() }
            }
        }
    }


    fun getDivision() {
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // ✅ UI updates on the main thread
                withContext(Dispatchers.Main) {
                    progressBar?.visibility = View.VISIBLE
                    progressMessage?.text = "Loading Division...."
                }

                // ✅ Run API call on the background thread
                val result = withContext(Dispatchers.IO) { dataApi.getDivision() }

                if (result.isSuccessful) {
                    result.body()?.let { divList ->
                        // ✅ Insert into database on IO thread
                        withContext(Dispatchers.IO) {
                            appDataBase?.divisionDao()?.insertAll(*divList.toTypedArray())
                        }



                    } ?: run {
                        Log.d("chandan:", "No data available")
                    }
                } else {
                    Log.d("chandan:", "Error: ${result.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("chandan:", "Error during API call: ${e.message}")
            } finally {
                // ✅ Ensure UI updates run on the main thread
                withContext(Dispatchers.Main) {
                    progressBar?.visibility = View.GONE
                }
            }
        }
    }


    fun getSubDivision(){
        // Show progress bar while the network call is happening
        progressBar?.visibility = View.VISIBLE
        progressMessage?.text="Loading Subdivision...."
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // Launching the coroutine using ViewModelScope (instead of GlobalScope)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                progressBar?.visibility = View.GONE
                val result = dataApi.getSubdivision()

                // Check if result is not null
                if (result.isSuccessful) {
                    result.body()?.let { subdivList ->
                        // Log the result
                        Log.d("chandan:", subdivList.toString())
                        // Insert data into the database
                        val divisionDao = appDataBase?.subDivisionDao()
                        divisionDao?.insertAll(*subdivList.toTypedArray())
                        // Call getDivision after successful insert
                    } ?: run {
                        // Handle case when the body is null
                        Log.d("chandan:", "No data available")
                    }
                } else {
                    // Log the error or handle the failure case
                    Log.d("chandan:", "Error: ${result.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Handle network or other errors
                Log.e("chandan:", "Error during API call: ${e.message}")
            } finally {
                // Hide the progress bar regardless of success or failure
                progressBar?.visibility = View.GONE
            }
        }
    }

    fun getSection() {
        // Show progress bar while the network call is happening
        progressBar?.visibility = View.VISIBLE
        progressMessage?.text="Loading Section...."
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // Launching the coroutine using ViewModelScope (instead of GlobalScope)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                progressBar?.visibility = View.GONE
                val result = dataApi.getSection()

                // Check if result is not null
                if (result.isSuccessful) {
                    result.body()?.let { secListList ->
                        // Log the result
                        Log.d("chandan:", secListList.toString())
                        // Insert data into the database
                        val sectionDao = appDataBase?.sectionDao()
                        sectionDao?.insertAll(*secListList.toTypedArray())
                    } ?: run {
                        // Handle case when the body is null
                        Log.d("chandan:", "No data available")
                    }
                } else {
                    // Log the error or handle the failure case
                    Log.d("chandan:", "Error: ${result.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Handle network or other errors
                Log.e("chandan:", "Error during API call: ${e.message}")
            } finally {
                // Hide the progress bar regardless of success or failure
                progressBar?.visibility = View.GONE
            }
        }
    }

    fun getJobWiseMatUtilizationSeg() {
        // Show progress bar while the network call is happening
        progressBar?.visibility = View.VISIBLE
        progressMessage?.text="Loading JObs...."
        val dataApi = RetrofitHelper.getInstance().create(DataApi::class.java)
        // Launching the coroutine using ViewModelScope (instead of GlobalScope)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                progressBar?.visibility = View.GONE
                val result = dataApi.getJobwiseMatUtilizationSeg()

                // Check if result is not null
                if (result.isSuccessful) {
                    result.body()?.let { jobUtilList ->
                        // Insert data into the database
                        Log.d("chandan:",jobUtilList.toString())
                        val jwmuDao = appDataBase?.jobwiseMatUtilDao()
                        jwmuDao?.insertAll(*jobUtilList.toTypedArray())
                        // Log the result
                        progressMessage?.text="Done.."
                        sessionData?.saveData("appdata", "Y")
                        start()
                    } ?: run {
                        // Handle case when the body is null
                        Log.d("chandan:", "No data available")
                    }
                } else {
                    // Log the error or handle the failure case
                    Log.d("chandan:", "Error: ${result.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Handle network or other errors
                Log.e("chandan:", "Error during API call: ${e.message}")
            } finally {
                // Hide the progress bar regardless of success or failure
                progressBar?.visibility = View.GONE
            }
        }
    }
    private fun showProgressBar(message: String) {
        progressBar?.visibility = View.VISIBLE
        progressMessage?.text = message
    }

    private fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }
    fun start() {
        progressMessage?.text = "Wait.."
        handler.postDelayed({
            val appData = sessionData?.getData("appdata")?.trim()
            if (appData.equals("Y", ignoreCase = true)) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
            } else {
                toast("Data Not Found!")
                finish()
            }
        }, 5000)
    }
}