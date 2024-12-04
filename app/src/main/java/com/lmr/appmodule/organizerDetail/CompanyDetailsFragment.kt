package com.lmr.appmodule.organizerDetail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.batchfinal.adapter.UpcomingEventListAdapter
import com.dev.batchfinal.adapter.UpcomingOrganizerEventListAdapter
import com.google.gson.JsonObject
import com.lmr.R
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.home.model.EventByOrganizerResponse.EventByOrganizer
import com.lmr.appmodule.home.model.EventData
import com.lmr.appmodule.organizerDetail.adapter.EventAdapter
import com.lmr.databinding.FragmentCompanyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CompanyDetailsFragment : BaseActivity<FragmentCompanyDetailsBinding>() {
    private val viewModel: AllViewModel by viewModels()
    var organizerId =""
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }



    override fun initUi() {
         organizerId = intent.getStringExtra("EXTRA_ID") ?: "No ID"  // Default value if null
        val companyName = intent.getStringExtra("EXTRA_NAME") ?: "No Company Name"  // Default value if null
        // binding.txtCompany.text=organizerId

        val jsonObject = JsonObject()
        jsonObject.addProperty("OrganizerID", organizerId)
        jsonObject.addProperty("Type", "0")
        viewModel.eventByOrganizerApiCall(jsonObject)
        viewModel.eventOrganizerProfileApiCall(organizerId)
        getEventData()
        getProfileData()
        eventAdapter()
        set()
    }

    fun set(){
        // Retrieve the data passed via Intent

       binding.llUpcomingEvents.setOnClickListener {
           binding.rvEvents.visibility=View.VISIBLE
           binding.txtProfileDescription.visibility=View.GONE
           val jsonObject = JsonObject()
           jsonObject.addProperty("OrganizerID", organizerId)
           jsonObject.addProperty("Type", "0")
           viewModel.eventByOrganizerApiCall(jsonObject)

           binding.txtUpcomingEvent.setTextColor(ContextCompat.getColor(this, R.color.txt_blue))
           binding.viewUpcoming.visibility=View.VISIBLE
           binding.viewUpcoming.setBackgroundColor(ContextCompat.getColor(this, R.color.txt_blue))

           binding.txtPreviousEvents.setTextColor(ContextCompat.getColor(this, R.color.grey3))
           binding.viewPreviousEvents.visibility=View.GONE

           binding.txtProfile.setTextColor(ContextCompat.getColor(this, R.color.grey3))
           binding.viewProfile.visibility=View.GONE

       }
       binding.llPreviousEvents.setOnClickListener {
           binding.rvEvents.visibility=View.VISIBLE
           binding.txtProfileDescription.visibility=View.GONE
           val jsonObject = JsonObject()
           jsonObject.addProperty("OrganizerID", organizerId)
           jsonObject.addProperty("Type", "1")
           viewModel.eventByOrganizerApiCall(jsonObject)
           binding.txtUpcomingEvent.setTextColor(ContextCompat.getColor(this, R.color.grey3))
           binding.viewUpcoming.visibility=View.GONE

           binding.txtPreviousEvents.setTextColor(ContextCompat.getColor(this, R.color.txt_blue))
           binding.viewPreviousEvents.visibility=View.VISIBLE
           binding.viewPreviousEvents.setBackgroundColor(ContextCompat.getColor(this, R.color.txt_blue))

           binding.txtProfile.setTextColor(ContextCompat.getColor(this, R.color.grey3))
           binding.viewProfile.visibility=View.GONE

       }
       binding.llProfile.setOnClickListener {
           binding.rvEvents.visibility=View.GONE
           binding.txtProfileDescription.visibility=View.VISIBLE
           viewModel.eventOrganizerProfileApiCall(organizerId)

           binding.txtUpcomingEvent.setTextColor(ContextCompat.getColor(this, R.color.grey3))
           binding.viewUpcoming.visibility=View.GONE


           binding.txtPreviousEvents.setTextColor(ContextCompat.getColor(this, R.color.grey3))
           binding.viewPreviousEvents.visibility=View.GONE


           binding.txtProfile.setTextColor(ContextCompat.getColor(this, R.color.txt_blue))
           binding.viewProfile.visibility=View.VISIBLE
           binding.viewProfile.setBackgroundColor(ContextCompat.getColor(this, R.color.txt_blue))
       }
    }

    private fun eventAdapter() {
        binding.recylerEvent.setLayoutManager(
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
        )
        val adapter = EventAdapter(this)
        binding.recylerEvent.adapter = adapter
    }

    fun getEventData() {
        try {
           // LoaderUtil.showLoader(this)  // To show loader
            viewModel.eventByOrganizerResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To
                        val response = it.data
                        if(response?.success == true){
                            upcomingEventListAdapter(response.data)
                        }else{
                        }

                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.eventByOrganizerResponse.removeObservers(this)
                        if ( viewModel.eventByOrganizerResponse.hasObservers()) return@observe
                        //   hideLoader()
                        //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                    }
                    is NetworkErrorResult.Loading->{
                        LoaderUtil.hideLoader(this)
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

    fun getProfileData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel.eventOrganizerProfilerResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To
                        val response = it.data
                        if(response?.success == true){
                            binding.txtProfileDescription.text= it.data!!.data.aboutOrganizer
                        }else{
                        }

                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.eventOrganizerProfilerResponse.removeObservers(this)
                        if ( viewModel.eventOrganizerProfilerResponse.hasObservers()) return@observe
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


    fun upcomingEventListAdapter(allUserAlerts:List<EventByOrganizer>) {
        binding.rvEvents.setLayoutManager(
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        val adapter = UpcomingOrganizerEventListAdapter(this,allUserAlerts)
        binding.rvEvents.adapter = adapter
    }





    override fun getViewBinding() = FragmentCompanyDetailsBinding.inflate(layoutInflater)


}