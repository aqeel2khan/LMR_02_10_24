package com.lmr.appmodule.eventorganizorlist.view

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lmr.R
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.eventorganizorlist.adapter.EventOrganizerListAdapter
import com.lmr.appmodule.eventorganizorlist.model.OrganizerDataItem
import com.lmr.appmodule.home.model.Organizer
import com.lmr.appmodule.organizerDetail.CompanyDetailsFragment
import com.lmr.appmodule.vendor.utils.OnClickListener
import com.lmr.databinding.EventOrganizerListActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.ArrayList
@AndroidEntryPoint
class EventOrganizerListActivity : BaseActivity<EventOrganizerListActivityBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }
    override fun initUi() {
        try {
            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
            detailsTextView.text = "LAMAR Events"

            supportActionBar!!.setDisplayShowTitleEnabled(false)
//        binding.tvsingledayevent.setOnClickListener {
//            binding.tvrecurringevent.setBackgroundResource(R.drawable.circle_shap_allo)
//            binding.tvsingledayevent.setBackgroundResource(R.drawable.circel_shep_white)
//        }
//        binding.tvrecurringevent.setOnClickListener {
//            binding.tvrecurringevent.setBackgroundResource(R.drawable.circel_shep_white)
//            binding.tvsingledayevent.setBackgroundResource(R.drawable.circle_shap_allo)
//        }

           // showData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        eventOrganizerResponseData()
        viewModel.eventOrganizerApiCall()

    }

    private fun showData() {
        try {
            val mOnClickListener = object : OnClickListener {
                override fun <T> onClick(view: View, item: T) {
                  //  if (item is OrganizerDataItem) {
                        if (view.id == R.id.outer_layout) {

                            startActivity(Intent(this@EventOrganizerListActivity,
                                CompanyDetailsFragment::class.java))
//                            when(item.vendorType) {
//                                "Vendor" -> {
//
//                                }
//
//                        }
                    }

                   // }
                }
            }

            val vendorEventsList = ArrayList<OrganizerDataItem>()

            val organizerDataItem = OrganizerDataItem(
                bankName = "KOC",
                bankAddress = "Ahmadi",
                bankUpcomingEventCount = "5 Upcoming Events",
                vendorCount = 10,
                isBackGround = true,
                backGroundColor = "#FAFAFA",
                backGroundImage = R.drawable.koc_bank
            )
            val organizerDataItem2 = OrganizerDataItem(
                "Gulf Bank",
                "Kuwait City",
                "6 Upcoming Events",
                6,
                true,
                backGroundColor = "#FAFAFA",
                backGroundImage = R.drawable.gulf_bank
            )

            val organizerDataItem3 = OrganizerDataItem(
                "NBK ",
                "Kuwait City",
                "4 Upcoming Events",
                6,
                true,
                backGroundColor = "#FAFAFA",
                backGroundImage = R.drawable.nbk_bank
            )

            val organizerDataItem4 = OrganizerDataItem(
                "JACC ",
                "Kuwait City",
                "2 Upcoming Events",
                6,
                true,
                backGroundColor = "#FAFAFA",
                backGroundImage = R.drawable.jacc_bank
            )
            vendorEventsList.add(organizerDataItem)
            vendorEventsList.add(organizerDataItem2)
            vendorEventsList.add(organizerDataItem3)
            vendorEventsList.add(organizerDataItem4)
//        vendorEventsList.add(organizerDataItem4)
//        vendorEventsList.add(organizerDataItem4)
//        vendorEventsList.add(organizerDataItem4)
//        vendorEventsList.add(organizerDataItem4)Exce

           /* binding.recyclerList.apply {
                layoutManager =  GridLayoutManager(this@EventOrganizerListActivity,2)
                adapter = EventOrganizerListAdapter(vendorEventsList,mOnClickListener,this@EventOrganizerListActivity)

               binding.recyclerList.addItemDecoration(AdaptiveSpacingItemDecoration(binding.recyclerList.context.resources.getDimensionPixelSize(R.dimen.spacing), edgeEnabled = true))

            }*/
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    fun eventOrganizerResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel.eventOrganizerResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.eventOrganizerResponse.removeObservers(this)
                        if (viewModel.eventOrganizerResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data
                                if(response?.success == true){
                                    eventOrganizerListAdapter(response.data)

                                }else{
                                }
                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.eventOrganizerResponse.removeObservers(this)
                        if ( viewModel.eventOrganizerResponse.hasObservers()) return@observe
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

    fun eventOrganizerListAdapter(allUserAlerts:List<Organizer>) {
        binding.recyclerList.setLayoutManager(
            GridLayoutManager(this@EventOrganizerListActivity,2)
        )
        val adapter = EventOrganizerListAdapter(allUserAlerts,this)
        binding.recyclerList.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    override fun getViewBinding()= EventOrganizerListActivityBinding.inflate(layoutInflater)


}