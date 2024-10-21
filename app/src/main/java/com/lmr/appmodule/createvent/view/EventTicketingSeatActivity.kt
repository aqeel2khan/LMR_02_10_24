package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.lmr.R
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.databinding.ActivityTicketingSeatBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.model.eventbookdateseat.EventBookingRequest
import com.lmr.appmodule.createvent.model.eventbookdateseat.TicketBookingDetailRequest
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.TicketingSeatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class EventTicketingSeatActivity : BaseActivity<ActivityTicketingSeatBinding>() {
    private var selectedItem: String ="none"
    private var onetimeEvent: Boolean = true
    private val viewModel: TicketingSeatViewModel by viewModels()

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
    @SuppressLint("SetTextI18n")
    override fun initUi() {
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Ticketing/ Seat Details"
        val spinner = findViewById<Spinner>(R.id.VisibilitySpinner)
        val array = arrayListOf("Visible", "Not Visible")

        binding.btnOneTimeEvent.setOnClickListener {
            onetimeEvent= true

            val colorSelected = ContextCompat.getColor(this, R.color.color4A4A4A)
            binding.btnOneTimeEvent.setBackgroundColor(colorSelected)

            val colorunselected = ContextCompat.getColor(this, R.color.colorEAEEFB)
            binding.btnrecurringTimeEvent.setBackgroundColor(colorunselected)

        }
        binding.btnrecurringTimeEvent.setOnClickListener {
            onetimeEvent= false

            val colorSelected = ContextCompat.getColor(this, R.color.color4A4A4A)
            val colorunselected = ContextCompat.getColor(this, R.color.colorEAEEFB)

            binding.btnOneTimeEvent.setBackgroundColor(colorunselected)
            binding.btnrecurringTimeEvent.setBackgroundColor(colorSelected)

        }

        val eventId = intent.getStringExtra("eventID")
        val eventBookingRequest = intent.getSerializableExtra("eventBookingRequest") as? EventBookingRequest
        if (eventBookingRequest != null) {
            // Use the eventBookingRequest object as needed
        }


        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                 selectedItem = parent.getItemAtPosition(position).toString()

                // Do something with the selected item
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do something when nothing is selected, if necessary
            }
        }

        binding.addbttn.setOnClickListener {

        var vipSeatName=    binding.etSeatName.text.toString()
            var metQuantity=    binding.etQuantity.text.toString()
            var mPrice=    binding.etprice.text.toString()
            var emtMinimumTicket=    binding.etMinimumTicket.text.toString()
            var metMaximumTicket= binding.etMaximumTicket.text.toString()
            var metTicketDescription =  binding.etTicketDescription.text.toString()


           val listData=  TicketBookingDetailRequest(
               ticketBookingDetailsID = 0,
               ticketName = vipSeatName,
               availableQuantity = metQuantity.toInt(),
               price = mPrice,
               minimumTicketNumber = emtMinimumTicket.toInt(),
               maximumTicketNumber = metMaximumTicket.toInt(),
               ticketDescription = metTicketDescription,
               visibility = selectedItem,
               ticketFeeAbsorbtion =onetimeEvent

           )

         var mList=   arrayListOf<TicketBookingDetailRequest>()
            mList.add(listData)

            eventBookingRequest?.lstTicketBookingDetailRequest=mList

            if (eventBookingRequest != null) {
                viewModel.   callEventbookDateSeatAPI(eventBookingRequest)
                observerPostResponseData()
            }

           // startActivity(Intent(this, EventPreviewsActivity::class.java))
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
                                    val intent = Intent(this@EventTicketingSeatActivity, EventPreviewsActivity::class.java)
                                    intent.putExtra("eventID", eventId)
                                    startActivity(intent)

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
    override fun getViewBinding() = ActivityTicketingSeatBinding.inflate(layoutInflater)
}