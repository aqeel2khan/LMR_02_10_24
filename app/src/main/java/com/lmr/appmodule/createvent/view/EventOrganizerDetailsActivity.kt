package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.lmr.R
import com.lmr.adapter.OrganizerTypeSpinner
import com.lmr.app_utils.CheckNetworkConnection
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.databinding.ActivityOrganigerDetailsBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.appcommonView.BottomSheetFragment
import com.lmr.appmodule.createvent.model.EventCategory
import com.lmr.appmodule.createvent.model.organizerdetail.EventOrganizerTypeData

import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.OrganizerDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventOrganizerDetailsActivity : BaseActivity<ActivityOrganigerDetailsBinding>() {
    private var eventOrganizerListData: MutableList<EventOrganizerTypeData?> = mutableListOf()
    private var eventOrganizerTypeDataSelected :EventOrganizerTypeData? =null
    private val viewModel: OrganizerDetailsViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    @SuppressLint("SetTextI18n")
    override fun initUi() {

        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Organizer Details"
        binding.profileImageTextView.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        val items =
            arrayOf("Marriage 1", "Party 2", "Birthday  3", "Anniversary 4", "Rewards Party 5")
        val adapter = ArrayAdapter(this, R.layout.custom_dropdown_item, items)
        binding.orgranizerSpinner.adapter = adapter
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.saveAndContinueButtonOrganiger.setOnClickListener {
            startActivity(Intent(this, EventBookDateSeatActivity::class.java))
        }
        binding.profileImageRelativeLayout.setOnClickListener {
            takePhoto()
        }

        getOrganizerTypeData();
    }

    private fun getOrganizerTypeData() {
        try {

            if (CheckNetworkConnection.isConnection(binding.root.context, binding.root, true)) {

                viewModel.fetchOrganizerType()
                observeResponseData()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun observeResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel._organizerTypeResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel._organizerTypeResponse.removeObservers(this)
                        if (viewModel._organizerTypeResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data

                                if(response?.success == true){

                                     eventOrganizerListData = response.data;
                                    setDataOrganizerTypeSpinner(eventOrganizerListData)

//                                    startActivity(Intent(this@EventOrganizerDetailsActivity, EventBookDateSeatActivity::class.java))
                                }else{


                                }

                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel._organizerTypeResponse.removeObservers(this)
                        if ( viewModel._organizerTypeResponse.hasObservers()) return@observe
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

    private fun setDataOrganizerTypeSpinner(listEventOrganizerType: MutableList<EventOrganizerTypeData?>) {

        try {
            val addHint = EventOrganizerTypeData(-1,"Choose Organizer Type","","EN")
            listEventOrganizerType!!.add(0,addHint)
          var  organizerTypeSpinner =  OrganizerTypeSpinner(this, listEventOrganizerType)
            binding.orgranizerSpinner.adapter=organizerTypeSpinner
            binding.orgranizerSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                    eventOrganizerTypeDataSelected = listEventOrganizerType[position]

                }
                // to close the onItemSelected

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getViewBinding() = ActivityOrganigerDetailsBinding.inflate(layoutInflater)

    private fun takePhoto() {

//        pickCameraImage()
    }


    fun showImageCode(view: View) {
//        var resource = 0
//        if (view === imgProfileCode) {
//            resource = R.drawable.img_profile_code
//        } else if (view === imgCameraCode) {
//            resource = R.drawable.img_camera_code
//        } else if (view === imgGalleryCode) {
//            resource = R.drawable.img_gallery_code
//        }
//        ImageViewerDialog
//            .newInstance(resource)
//            .show(supportFragmentManager, "")
    }

    fun showImage(view: View) {
//        val uri: Uri?
//        uri = if (view === imgProfile) {
//            mProfileUri
//        } else if (view === imgCamera) {
//            mCameraUri
//        } else if (view === imgGallery) {
//            mGalleryUri
//        } else {
//            null
//        }
//        if (uri != null) {
//            startActivity(IntentUtils.getUriViewIntent(this, uri))
//        }
    }

    fun showImageInfo(view: View) {
//        val uri: Uri?
//        uri = if (view === imgProfileInfo) {
//            mProfileUri
//        } else if (view === imgCameraInfo) {
//            mCameraUri
//        } else if (view === imgGalleryInfo) {
//            mGalleryUri
//        } else {
//            null
//        }
//        AlertDialog.Builder(this)
//            .setTitle("Image Info")
//            .setMessage(FileUtil.getFileInfo(this, uri))
//            .setPositiveButton("Ok", null)
//            .show()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            // Uri object will not be null for RESULT_OK
//            val uri = data!!.data
//            when (requestCode) {
//               PROFILE_IMAGE_REQ_CODE -> {
//                    mProfileUri = uri
//                }
//
//              GALLERY_IMAGE_REQ_CODE -> {
//                    mGalleryUri = uri
//                }
//
//               CAMERA_IMAGE_REQ_CODE -> {
//                    mCameraUri = uri
//                }
//            }
//        } else if (resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
//        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}





