package com.lmr.appmodule.createvent.viewfrag

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.lmr.R
import com.lmr.adapter.AgeGroupSpinnerAdapter
import com.lmr.adapter.CustomSpinerEventCategoryAdapter
import com.lmr.adapter.EventTypeSpinnerAdapter
import com.lmr.adapter.MaxCapacitySpinnerAdapter
import com.lmr.databinding.ActivityBasicDetailsBinding
import com.lmr.appmodule.model.request.PostBasicDetailEvent
import com.lmr.appmodule.model.response.AgeGroupData
import com.lmr.appmodule.model.response.CapacityData
import com.lmr.appmodule.model.response.Event
import com.lmr.appmodule.createvent.model.EventCategory
import com.lmr.app_utils.CheckNetworkConnection
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.view.EventDescriptionActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.BasicDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasicDetailsFragment : BaseFragment<ActivityBasicDetailsBinding>() {
    private lateinit var customEventCategorySpinnerAdapter: CustomSpinerEventCategoryAdapter
    private lateinit var customEventTypeSpinnerAdapter: EventTypeSpinnerAdapter
    private lateinit var customMaxCapacitySpinnerAdapter: MaxCapacitySpinnerAdapter
    private lateinit var ageGroupSpinnerAdapter : AgeGroupSpinnerAdapter


    private  var eventcategorySelected : EventCategory? = null
    private  var eventTypeSelected : Event? = null
    private var  eventCapacityDataSelected : CapacityData? = null
    private var eventAgeGroupData : AgeGroupData? = null

    private val viewModel: BasicDetailViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
    override fun getViewBinding() = ActivityBasicDetailsBinding.inflate(layoutInflater)
    private var isPrivateEvent = true
    private var isPublicEvent = true
    private var isFreeEvent = true
    private var isPaidEvent = true
    override fun initUi() {
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.publicEvent.setOnClickListener {
            isPublicEvent = true
            binding.publicEvent.isChecked = true
            binding.privateEvent.isChecked = false
        }
        binding.privateEvent.setOnClickListener {
            isPublicEvent = false
            binding.privateEvent.isChecked = true
            binding.publicEvent.isChecked = false
        }
        binding.freeEvent.setOnClickListener {
            isFreeEvent = true
            binding.freeEvent.isChecked = true
            binding.paidEvent.isChecked = false
        }
        binding.paidEvent.setOnClickListener {
            isFreeEvent = false
            binding.paidEvent.isChecked = true
            binding.freeEvent.isChecked = false
        }
        customSpinnerAgeGroup();
        binding.saveAndContinueButtonBasic.setOnClickListener {
            // post data to api
            startActivity(Intent(requireActivity(), EventDescriptionActivity::class.java))

           postData()
            // Initiate the API calls

        }

        initiateApiCalls()

        // Observe the responses
        observeCombinedResponse()
       // observePostEventResponse()



        //Todo Call API Get Category Event from Server via API
      /*  categoryEventApi()
      //  eventTypeApi()
     //   maximumCapacityApi()
     //   ageApi()*/


    }

    private fun initiateApiCalls() {
        try {

                if (CheckNetworkConnection.isConnection(binding.root.context, binding.root, true)) {
                    LoaderUtil.showLoader(this)

                    viewModel.fetchAllDetails()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

/*
    private fun observePostEventResponse(){

        // Observe postEventResponse
        viewModel.postEventResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkErrorResult.Loading -> {
                    // Show loading indicator
                }
                is NetworkErrorResult.Success -> {
                    // Handle successful post event response
                    val postEventResponse = response.data
                    // Use postEventResponse to update your UI components
                }
                is NetworkErrorResult.Error -> {
                    // Show error message
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
*/

    private fun observeCombinedResponse() {
        // Observe allDetailsResponse
        viewModel.allDetailsResponse.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is NetworkErrorResult.Loading -> {
                    // Show loading indicator
                }
                is NetworkErrorResult.Success -> {
                    LoaderUtil.hideLoader(this)
                    // Update UI with fetched details
                    val allDetails = response.data

                    setDataForAgeSelection( allDetails?.ageGroup?.data)
                    setDataforCategorySpinner(allDetails?.categoryEvent?.data)
                    setDataforEventTypeSpinner(allDetails?.eventType?.data)
                    setDataforMaxCapacitySpinner(allDetails?.maximumCapacity?.data)
                    // Use allDetails to update your UI components
                }
                is NetworkErrorResult.Error -> {
                    LoaderUtil.hideLoader(this)
                    // Show error message
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })    }


/*
    private fun ageApi() {

            try {
                if (CheckNetworkConnection.isConnection(binding.root.context, binding.root, true)) {
                    //  showLoader()
                    viewModel.getAgeApi()

                    viewModel.ageGroupResponse.observe(this){
                        when(it){
                            is NetworkErrorResult.Success->{
                                viewModel.ageGroupResponse.removeObservers(this)
                                if (viewModel.ageGroupResponse.hasObservers()) return@observe
                                //   hideLoader()
                                lifecycleScope.launch {
                                    it.let {
                                        val response = it.data
                                        //    Log.e("response",response.toString())
                                        if(response!!.success){

                                            if(!response?.data.isNullOrEmpty()){
                                                // setRecylerServices(response.data.reviews)

                                                setDataForAgeSelection(response?.data)

                                            }else{

                                            }

                                            //    response.data.

                                        }else{

                                        }

                                    }
                                }
                            }
                            is NetworkErrorResult.Error->{
                                viewModel.ageGroupResponse.removeObservers(this)
                                if ( viewModel.ageGroupResponse.hasObservers()) return@observe
                                //    hideLoader()
                                //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                            }
                            is NetworkErrorResult.Loading->{
                                //    hideLoader()
                            }

                            else -> {

                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }
*/

    private fun setDataForAgeSelection(data: MutableList<AgeGroupData>?) {
        try {

            val addHint = AgeGroupData(-1,"AGE GROUP")
            data!!.add(0,addHint)

            ageGroupSpinnerAdapter =  AgeGroupSpinnerAdapter(requireContext(), data)
            binding.customSpinner4.adapter=ageGroupSpinnerAdapter
            binding.customSpinner4?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                    eventAgeGroupData=     data?.get(position)
                } // to close the onItemSelected

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun postData() {

       val mPostBasicDetailEvent =  PostBasicDetailEvent()
        mPostBasicDetailEvent.eventName = binding.eventEditText.toString()
        mPostBasicDetailEvent.eventTypeID = eventTypeSelected?.eventTypeID?:0
        mPostBasicDetailEvent.eventCategoryID = eventcategorySelected?.eventTypeID?:0
        mPostBasicDetailEvent.maximumCapacity =  eventCapacityDataSelected?.totalMaximumCapacityId?:0
        mPostBasicDetailEvent.ageGroupID =  eventAgeGroupData?.ageGroupID?:0
        if(isFreeEvent){
            mPostBasicDetailEvent.ticketType =  0
        }else{
            mPostBasicDetailEvent.ticketType =  1
        }
        if(isPublicEvent){
            mPostBasicDetailEvent.publishingMethod =  0
        }else{
            mPostBasicDetailEvent.publishingMethod =  1
        }

        viewModel.postEventData(mPostBasicDetailEvent)
        PostEventResponseData()

    }

    private fun PostEventResponseData() {
            try {
                      // showLoader()
                    viewModel.postEventResponse.observe(this){
                        when(it){
                            is NetworkErrorResult.Success->{
                                viewModel.postEventResponse.removeObservers(this)
                                if (viewModel.postEventResponse.hasObservers()) return@observe
                                //     hideLoader()
                                lifecycleScope.launch {
                                    it.let {
                                        val response = it.data

                                        if(response!!.success){

                                       startActivity(Intent(requireActivity(), EventDescriptionActivity::class.java))
                                        }else{


                                        }

                                    }
                                }
                            }
                            is NetworkErrorResult.Error->{
                                viewModel.postEventResponse.removeObservers(this)
                                if ( viewModel.postEventResponse.hasObservers()) return@observe
                                //   hideLoader()
                                //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                            }
                            is NetworkErrorResult.Loading->{
                                //  hideLoader()
                            }

                            else -> {

                            }
                        }
                    }

            } catch (e: Exception) {
                e.printStackTrace()
            }
    }

/*    @SuppressLint("LogNotTimber")
    private fun categoryEventApi() {
        try {
            if (CheckNetworkConnection.isConnection(binding.root.context, binding.root, true)) {
              //  showLoader()
                viewModel.getCategoryEvents()

                viewModel.eventcategoryResponse.observe(this){
                    when(it){
                        is NetworkErrorResult.Success->{
                            viewModel.eventcategoryResponse.removeObservers(this)
                            if (viewModel.eventcategoryResponse.hasObservers()) return@observe
                         //   hideLoader()
                            lifecycleScope.launch {
                                it.let {
                                    val response = it.data
                                //    Log.e("response",response.toString())
                                    if(response!!.success){

                                        if(!response?.data.isNullOrEmpty()){
                                           // setRecylerServices(response.data.reviews)

                                            setDataforCategorySpinner(response?.data)

                                        }else{

                                        }

                                    //    response.data.

                                    }else{

                                    }

                                }
                            }
                        }
                        is NetworkErrorResult.Error->{
                            viewModel.eventcategoryResponse.removeObservers(this)
                            if ( viewModel.eventcategoryResponse.hasObservers()) return@observe
                        //    hideLoader()
                         //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                        }
                        is NetworkErrorResult.Loading->{
                        //    hideLoader()
                        }

                        else -> {

                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

/*
    private fun maximumCapacityApi() {
        try {
            if (CheckNetworkConnection.isConnection(binding.root.context, binding.root, true)) {
                //   showLoader()
                viewModel.getMaximumCapacity()

                viewModel.maximumCapacityResponse.observe(this){
                    when(it){
                        is NetworkErrorResult.Success->{
                            viewModel.maximumCapacityResponse.removeObservers(this)
                            if (viewModel.maximumCapacityResponse.hasObservers()) return@observe
                            //     hideLoader()
                            lifecycleScope.launch {
                                it.let {
                                    val response = it.data
                                    //    Log.e("response",response.toString())
                                    if(response!!.success){

                                        if(!response?.data.isNullOrEmpty()){
                                            // setRecylerServices(response.data.reviews)

                                            setDataforMaxCapacitySpinner(response?.data)

                                        }else{

                                        }
                                    }else{

                                    }

                                }
                            }
                        }
                        is NetworkErrorResult.Error->{
                            viewModel.maximumCapacityResponse.removeObservers(this)
                            if ( viewModel.maximumCapacityResponse.hasObservers()) return@observe
                            //   hideLoader()
                            //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                        }
                        is NetworkErrorResult.Loading->{
                            //  hideLoader()
                        }

                        else -> {

                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
*/

    private fun setDataforMaxCapacitySpinner(data: MutableList<CapacityData>?) {
        try {

            val addHint = CapacityData(-1,"MAXIMUM")
            data!!.add(0,addHint)
            customMaxCapacitySpinnerAdapter =  MaxCapacitySpinnerAdapter(requireContext(), data)
            binding.customSpinner3.adapter=customMaxCapacitySpinnerAdapter
            binding.customSpinner3?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                    eventCapacityDataSelected=     data?.get(position)
                } // to close the onItemSelected

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
/*
    private fun eventTypeApi() {
        try {
            if (CheckNetworkConnection.isConnection(binding.root.context, binding.root, true)) {
             //   showLoader()
                viewModel.getEventsType()
                viewModel.eventTypeResponse.observe(this){
                    when(it){
                        is NetworkErrorResult.Success->{
                            viewModel.eventTypeResponse.removeObservers(this)
                            if (viewModel.eventTypeResponse.hasObservers()) return@observe
                       //     hideLoader()
                            lifecycleScope.launch {
                                it.let {
                                    val response = it.data
                                    //    Log.e("response",response.toString())
                                    if(response!!.success){

                                        if(!response?.data.isNullOrEmpty()){
                                            // setRecylerServices(response.data.reviews)

                                            setDataforEventTypeSpinner(response?.data)

                                        }else{

                                        }

                                        //    response.data.

                                    }else{

                                    }

                                }
                            }
                        }
                        is NetworkErrorResult.Error->{
                            viewModel.eventTypeResponse.removeObservers(this)
                            if ( viewModel.eventTypeResponse.hasObservers()) return@observe
                         //   hideLoader()
                            //   snackBarWithRedBackground(binding.root, MyUtils.errorBody(it.message,binding.root.context))
                        }
                        is NetworkErrorResult.Loading->{
                          //  hideLoader()
                        }

                        else -> {

                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
*/
    private fun setDataforEventTypeSpinner(data: MutableList<Event>?) {
        try {
            val addHint = Event(-1,"TYPE OF EVENT","","EN")
            data!!.add(0,addHint)
            customEventTypeSpinnerAdapter =  EventTypeSpinnerAdapter(requireContext(), data)
            binding.customSpinner1.adapter=customEventTypeSpinnerAdapter
            binding.customSpinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                    eventTypeSelected=     data?.get(position)
                } // to close the onItemSelected

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun setDataforCategorySpinner(listEventCategory: MutableList<EventCategory>?) {

        try {
            val addHint = EventCategory(-1,"CHOOSE CATEGORY","","EN")
            listEventCategory!!.add(0,addHint)
            customEventCategorySpinnerAdapter =  CustomSpinerEventCategoryAdapter(requireContext(), listEventCategory)
            binding.customSpinner2.adapter=customEventCategorySpinnerAdapter
            binding.customSpinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                 eventcategorySelected=     listEventCategory?.get(position)
                } // to close the onItemSelected

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        } catch (e: Exception) {
           e.printStackTrace()
        }
    }
    private fun customSpinnerAgeGroup() {
        val items = arrayOf("18", "19 ", "20", "21")
        val adapter = ArrayAdapter(requireContext(), R.layout.custom_dropdown_item, items)
        binding.customSpinner4.adapter = adapter
    }
}