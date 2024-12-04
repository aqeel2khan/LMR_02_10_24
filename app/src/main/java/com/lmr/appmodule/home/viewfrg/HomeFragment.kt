package com.lmr.appmodule.home.viewfrg

import LoaderUtil
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dev.batchfinal.adapter.EventListAdapter
import com.dev.batchfinal.adapter.OrganizerListAdapter
import com.dev.batchfinal.adapter.UpcomingEventListAdapter
import com.lmr.R
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.eventorganizorlist.view.EventOrganizerListActivity
import com.lmr.appmodule.home.model.EventDashboardCategory
import com.lmr.appmodule.home.model.EventData
import com.lmr.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {

        showImage()
        observerLocationResponseData()
        viewModel.locationApiCall()
        dashboardEventResponseData()
        clicks()


    }

    private fun showImage() {


        binding.seeAll.setOnClickListener {

         try {
             requireActivity(). startActivity(Intent(requireActivity(),EventOrganizerListActivity::class.java))
         } catch (e: Exception) {
            e.printStackTrace()
         }
        }









    }

    fun clicks(){
       binding.city.setOnClickListener {
           binding.spinnerCity.visibility=View.VISIBLE

       }






    }
    fun observerLocationResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel.locationResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.locationResponse.removeObservers(this)
                        if (viewModel.locationResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data
                                if(response?.success == true){
                                    val locationName =  response.locationDetails!!.map { it.locationName }


                                    val adapter = ArrayAdapter(requireActivity(), com.lmr.R.layout.spinner_item, locationName!!)
                                    adapter.setDropDownViewResource(R.layout.spinner_item)
                                    binding.spinnerCity.adapter = adapter
                                    binding.spinnerCity.setDropDownVerticalOffset(0) // Optional: Adjust vertical offset if needed
                                    binding.spinnerCity.gravity = Gravity.BOTTOM
                                    binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {


                                            val location_city = response.locationDetails!![position]
                                            var location_name=location_city.locationName.toString()
                                            val locationIdId = location_city.locationID
                                            viewModel.dashboardEventApiCall(locationIdId.toString())

                                        }

                                        override fun onNothingSelected(parent: AdapterView<*>) {
                                            // Handle case where nothing is selected, if needed
                                        }
                                    }


                                }else{
                                }
                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.locationResponse.removeObservers(this)
                        if ( viewModel.locationResponse.hasObservers()) return@observe
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

    fun dashboardEventResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel.dashboardEventResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To
                      
                        //     hideLoader()
                        val response = it.data
                        if(response?.success == true){
                            for (i in response.data.eventDashboardData){
                                if (i.dataCategory.equals("Event")&&i.dataCategoryTitle.equals("Today")){
                                    if (!i.listdata.isNullOrEmpty()){
                                        binding.rvEvents.visibility=View.VISIBLE
                                        eventListAdapter(i.listdata!!)

                                    }else{
                                        binding.rvEvents.visibility=View.GONE
                                    }
                                } else if (i.dataCategory.equals("Organizers")&&i.dataCategoryTitle.equals("Organizers")){
                                    if (!i.listdata.isNullOrEmpty()){
                                        binding.rvOrganizerList.visibility=View.VISIBLE
                                        eventOrganizerListAdapter(i.listdata!!)
                                    }else{
                                        binding.rvOrganizerList.visibility=View.GONE
                                    }
                                }else if (i.dataCategory.equals("Event")&&i.dataCategoryTitle.equals("Upcoming")){
                                    if (!i.listdata.isNullOrEmpty()){
                                        binding.rvUpcomingEvent.visibility=View.VISIBLE
                                        upcomingEventListAdapter(i.listdata!!)
                                    }else{
                                        binding.rvUpcomingEvent.visibility=View.GONE
                                    }
                                }else if (i.dataCategory.equals("Advertisement")&&i.dataCategoryTitle.equals("Advertisement")){
                                    if (!i.listdata.isNullOrEmpty()){
                                        binding.rvAllEventMusic.visibility=View.VISIBLE
                                        allAboutMusicListAdapter(i.listdata!!)
                                    }else{
                                        binding.rvAllEventMusic.visibility=View.GONE
                                    }
                                }
                            }




                        }else{
                        }

                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To
                        viewModel.dashboardEventResponse.removeObservers(this)
                        if ( viewModel.dashboardEventResponse.hasObservers()) return@observe
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

    fun eventListAdapter(allUserAlerts:List<EventData>) {
        binding.rvEvents.setLayoutManager(
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false))
        val adapter = EventListAdapter(requireActivity(),allUserAlerts)
        binding.rvEvents.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    fun eventOrganizerListAdapter(allUserAlerts:List<EventData>) {
        binding.rvOrganizerList.setLayoutManager(
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false))
        val adapter = OrganizerListAdapter(requireActivity(),allUserAlerts)
        binding.rvOrganizerList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    fun upcomingEventListAdapter(allUserAlerts:List<EventData>) {
        binding.rvUpcomingEvent.setLayoutManager(
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false))
        val adapter = UpcomingEventListAdapter(requireActivity(),allUserAlerts)
        binding.rvUpcomingEvent.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun allAboutMusicListAdapter(allUserAlerts:List<EventData>) {
        binding.rvAllEventMusic.setLayoutManager(
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false))
        val adapter = EventListAdapter(requireActivity(),allUserAlerts)
        binding.rvAllEventMusic.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        handleHeader(false)
//        handleTitle("Find events in")
    }




    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}