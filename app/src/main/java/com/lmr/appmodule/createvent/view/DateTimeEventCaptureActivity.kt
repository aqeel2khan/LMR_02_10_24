package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.lmr.R
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.databinding.ActivityDateTime3Binding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.model.adddatetime.DateTimeDetailsRequest
import com.lmr.appmodule.createvent.model.adddatetime.DateTimeEventPost

import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.DateTimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class DateTimeEventCaptureActivity() : BaseActivity<ActivityDateTime3Binding>() {
    private var numberTimesEvent: String ?= ""
    private val viewModel: DateTimeViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }
    override fun initUi() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Date & Time"

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.tvsingledayevent.setOnClickListener {

             numberTimesEvent= "1"
            binding.tvrecurringevent.setBackgroundResource(R.drawable.circle_shap_allo)
            binding.tvsingledayevent.setBackgroundResource(R.drawable.circel_shep_white)
        }
        binding.tvrecurringevent.setOnClickListener {
            numberTimesEvent= "2"
            binding.tvrecurringevent.setBackgroundResource(R.drawable.circel_shep_white)
            binding.tvsingledayevent.setBackgroundResource(R.drawable.circle_shap_allo)
        }

        binding.imenddata.setOnClickListener {
            openCalendar2()
        }
        binding.imdateicon.setOnClickListener {
            openCalendar()
        }
        binding.ivStartTime.setOnClickListener {
            showTimeDialog()

        }
        binding.imendtimeicon.setOnClickListener {
            showTimeDialog2()
        }
        binding.saveAndContinueButtonDateTime.setOnClickListener {

            val dateTimeEventPost:DateTimeEventPost = createDateTimeEventPost()

            viewModel.callPostDateTimeEvent(dateTimeEventPost);

            observeResponseData()

            println(dateTimeEventPost)
         //   startActivity(Intent(this, EventLocationActivity::class.java))
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun observeResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel._addDateTimeResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel._addDateTimeResponse.removeObservers(this)
                        if (viewModel._addDateTimeResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data

                                if(response?.success == true){

                                    var eventId= response.data.eventID;

                                    startActivity(Intent(this@DateTimeEventCaptureActivity, EventLocationActivity::class.java))
                                }else{


                                }

                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel._addDateTimeResponse.removeObservers(this)
                        if ( viewModel._addDateTimeResponse.hasObservers()) return@observe
                        //   hideLoader()
                        //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                    }
                    is NetworkErrorResult.Loading->{
                        //  hideLoader()
                    }

                    else -> {
                        LoaderUtil.hideLoader(this)  // To

                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            LoaderUtil.hideLoader(this)  // To

        }
    }

    override fun getViewBinding()= ActivityDateTime3Binding.inflate(layoutInflater)

    private fun showTimeDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val date = "$hourOfDay/$minute"
                binding.tvStartTime.text = date
            },
            0,
            0,
            true
        )
        timePickerDialog.show()
    }

    private fun showTimeDialog2() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val date = "$hourOfDay/$minute"
                binding.tvendtime.text = date
            },
            0,
            0,
            true
        )
        timePickerDialog.show()
    }


    private fun openCalendar() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/$month/$year"
                binding.startDateText.text = date

            },
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    private fun openCalendar2() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/$month/$year"
                binding.endDate.text = date
            },
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    fun createDateTimeEventPost(): DateTimeEventPost {

        var diplayStartTime=  binding.ch1.isChecked
        var diplayEndTime=  binding.ch2.isChecked


        val dateTimeDetailsList = listOf(
            DateTimeDetailsRequest(
                eventDateAndTimeDetailsID = 0,
                eventStartDate =  binding.startDateText.text.toString()?:"",
                startTime =  binding.tvStartTime.text.toString(),
                eventEndDate =  binding.endDate.text.toString()?:"",
                endTime =     binding.tvendtime.text.toString()
            )

        )

        return DateTimeEventPost(
            eventDateTimeID = 0,
            eventID = 211,
            eventOccurType = numberTimesEvent?:"1",
            displayStartTime = diplayStartTime,
            displayEndTime = diplayEndTime,
            lstDateTimeDetailsRequest = dateTimeDetailsList
        )
    }

}