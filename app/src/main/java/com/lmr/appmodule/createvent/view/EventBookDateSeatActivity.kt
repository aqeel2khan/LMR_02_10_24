package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.lmr.R
import com.lmr.app_custom.ImageResizeCallback
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.databinding.ActivityTickeetingSeatDetails1Binding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.model.eventbookdateseat.EventBookingRequest
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import com.lmr.appmodule.createvent.model.organizerdetail.TeamMemberOrganizer
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.EventDateSeatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar


@AndroidEntryPoint
class EventBookDateSeatActivity : BaseActivity<ActivityTickeetingSeatDetails1Binding>() {
    private var freeStatus: Boolean =true

    private val viewModel: EventDateSeatViewModel by viewModels()
    private lateinit var mListener: ImageResizeCallback


    private fun validation(): Boolean {
        val saleEndTime = binding.endTimeSaleTextView.text.toString()
        val vipSeat = binding.vipSeatsTextView.text.toString()
        val quantity = binding.quantityEditText.text.toString()
        val saleEndDate = binding.endTimeSaleTextView.text.toString()
        val salStartDate = binding.tvTicketSalesStartDate.text.toString()
        val salStarTime = binding.salesStarTimeTextView.text.toString()
        val endDateSale = binding.endDateSaleTextView.text.toString()
        val endTimeSale = binding.endTimeSaleTextView.text.toString()
        if (TextUtils.isEmpty(endTimeSale)) {
            Toast.makeText(this, "Set Time", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(endDateSale)) {
            Toast.makeText(this, "Set Date", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(salStarTime)) {
            Toast.makeText(this, "Set Time", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(salStartDate)) {
            Toast.makeText(this, "Set Date", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(saleEndDate)) {
            Toast.makeText(this, "Ticket Sale End Date", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(vipSeat)) {
            Toast.makeText(this, "VIP Seat is Empty", Toast.LENGTH_SHORT).show()
            return false
        } else if (TextUtils.isEmpty(quantity)) {
            Toast.makeText(this, "Quantity   is Empty", Toast.LENGTH_SHORT).show()
            return false
        } else if (TextUtils.isEmpty(saleEndTime)) {
            Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
        return false
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    @SuppressLint("SetTextI18n")
    override fun initUi() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Ticketing/Seating"
        binding.tvFreeText.setOnClickListener {
            freeStatus= true
            binding.tvFreeText.setBackgroundResource(R.drawable.circel_shep_white)
            binding.tvPaidText.setBackgroundResource(R.drawable.circle_shap_allo)
        }
        binding.tvPaidText.setOnClickListener {
            freeStatus= false
            binding.tvFreeText.setBackgroundResource(R.drawable.circle_shap_allo)
            binding.tvPaidText.setBackgroundResource(R.drawable.circel_shep_white)
        }

        binding.ticketsSalesStartDateRelative.setOnClickListener {
            openCalendar2()
        }

        binding.startTimeSeat.setOnClickListener {
            showTimeDialog3()
        }
        binding.endDateLableRelative.setOnClickListener {
            openCalendar3()
        }
        binding.endTimeLableRelative.setOnClickListener {
            showTimeDialog4()
        }
        binding.saveAndContinueButtonSeat.setOnClickListener {
          //  startActivity(Intent(this, EventTicketingSeatActivity::class.java))
            val checkStatus = validation()
            if (checkStatus) {

                postTicketimgSeat()
            }
        }
    }


    private fun postTicketimgSeat() {

    //
       val saleEndTime = binding.endTimeSaleTextView.text.toString()
        val vipSeat = binding.vipSeatsTextView.text.toString()
        val quantity = binding.quantityEditText.text.toString()
        val saleEndDate = binding.endDateSaleTextView.text.toString()
        val salStartDate = binding.ticketSalesStartDateText.text.toString()
        val salStarTime = binding.salesStarTimeTextView.text.toString()
        val endTimeSale = binding.endTimeSaleTextView.text.toString()

        var postFreestatus =0
        if(freeStatus){
            postFreestatus= 0

        }else{
            postFreestatus=1
        }

    //

        val eventBookingRequest = EventBookingRequest(
            seatDetailsID = 0,
            eventID = 264,
            eventPaidType = postFreestatus,
            bookingStartDate = salStartDate,
            bookingEndDate = saleEndDate,
            bookingStartTime = salStarTime ,
            bookingEndTime =endTimeSale,
            lstTicketBookingDetailRequest = arrayListOf()
        )

        if(freeStatus){

            val intent = Intent(this@EventBookDateSeatActivity, EventTicketingSeatActivity::class.java)
            intent.putExtra("eventID", "212")
            intent.putExtra("eventBookingRequest", eventBookingRequest)

            startActivity(intent)
            return
        }else{
            viewModel.   callEventbookDateSeatAPI(eventBookingRequest)
            observerPostResponseData()
        }



    }


    @SuppressLint("SuspiciousIndentation")
    private fun observerPostResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel._eventDescriptionResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel._eventDescriptionResponse.removeObservers(this)
                        if (viewModel._eventDescriptionResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data

                                if(response?.success == true){

                                    var eventId= response.data.eventID;
                                    val intent = Intent(this@EventBookDateSeatActivity, EventTicketingSeatActivity::class.java)
                                    intent.putExtra("eventID", eventId)
                                    startActivity(intent)


                                   // startActivity(Intent(this@EventBookDateSeatActivity, EventTicketingSeatActivity::class.java))
                                }else{


                                }

                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel._eventDescriptionResponse.removeObservers(this)
                        if ( viewModel._eventDescriptionResponse.hasObservers()) return@observe
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

    override fun getViewBinding() = ActivityTickeetingSeatDetails1Binding.inflate(layoutInflater)


    private fun openCalendar2() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/$month/$year"
                binding.ticketSalesStartDateText.text = date
            },
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    private fun openCalendar3() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val date = "$dayOfMonth/$month/$year"
                binding.endDateSaleTextView.text = date
            },
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }


    private fun showTimeDialog4() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val date = "$hourOfDay-$minute"
                binding.endTimeSaleTextView.text = date
            },
            0,
            0,
            true
        )
        timePickerDialog.show()
    }

    private fun showTimeDialog3() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                val date = "$hourOfDay-$minute"
                binding.salesStarTimeTextView.text = date
            },
            0,
            0,
            true
        )
        timePickerDialog.show()
    }


}