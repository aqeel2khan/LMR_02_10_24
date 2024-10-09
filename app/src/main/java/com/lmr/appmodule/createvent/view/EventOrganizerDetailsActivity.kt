package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lmr.R
import com.lmr.adapter.OrganizerTypeSpinner
import com.lmr.app_custom.ImageResizeCallback
import com.lmr.app_utils.CheckNetworkConnection
import com.lmr.app_utils.MyUtils
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.app_utils.camera.ImagePickerActivity
import com.lmr.databinding.ActivityOrganigerDetailsBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.appcommonView.BottomSheetFragment
import com.lmr.appmodule.createvent.model.EventCategory
import com.lmr.appmodule.createvent.model.description.EventDescriptionPost
import com.lmr.appmodule.createvent.model.organizerdetail.EventOrganizerTypeData
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import com.lmr.appmodule.createvent.model.organizerdetail.TeamMemberOrganizer

import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.OrganizerDetailsViewModel
import com.lmr.databinding.ActivityTickeetingSeatDetails1Binding
import com.yalantis.ucrop.UCrop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class EventOrganizerDetailsActivity : BaseActivity<ActivityOrganigerDetailsBinding>(),
    ImageResizeCallback {

    private val REQUEST_IMAGE = 100
    private val SAMPLE_CROPPED_IMAGE_NAME = "BatchImage"
    private val requestMode = 1
    private val resumeMode = 2
    private var userChoosenTask = ""
    private lateinit var mListener: ImageResizeCallback


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

        mListener = this
        binding.saveAndContinueButtonOrganiger.setOnClickListener {
            postOrganizerData()
         //   startActivity(Intent(this, EventBookDateSeatActivity::class.java))
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

    private fun postOrganizerData() {
        var organigerName=  binding.etOrganigerName.text.toString()
        var OrganizerDetail=  binding.etOrganizerDetail.text.toString()
        var etOrganizerAddress =  binding.etOrganizerAddress.text.toString()
        var mTeamName = binding.etTeamName.text.toString()
        var mTeamLastName = binding.etTeamLastName.text.toString()
        var mTeamNumber = binding.etTeamNumber.text.toString()

        val teamMembers = listOf(
            TeamMemberOrganizer(teamMemberID = 0, firstName = mTeamName, lastName = mTeamLastName, mobileNumber = mTeamNumber)
        )

        val postEventOrganizerData = PostEventOrganizerData(
            eventOrganizerID = 0,
            eventID = 231,
            eventOrganizerName = organigerName,
            eventOrganizerTypeID = 1,
            aboutOrganizer = OrganizerDetail,
            organizerAddress = etOrganizerAddress,
            profileImage = "",
            lstteammember = teamMembers
        )
        viewModel.   callPostOrganizerAPI(postEventOrganizerData)
        observerPostResponseData()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun observerPostResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel.organizerpostResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel.organizerpostResponse.removeObservers(this)
                        if (viewModel.organizerpostResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data

                                if(response?.success == true){

                                    var eventId= response.data.eventID;

                                    startActivity(Intent(this@EventOrganizerDetailsActivity, EventBookDateSeatActivity::class.java))
                                }else{


                                }

                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To

                        viewModel.organizerpostResponse.removeObservers(this)
                        if ( viewModel.organizerpostResponse.hasObservers()) return@observe
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

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getViewBinding() = ActivityOrganigerDetailsBinding.inflate(layoutInflater)

    private fun takePhoto() {

      openOtionDialog()
    }


    private fun openOtionDialog() {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
        bottomSheetDialog.setContentView(R.layout.row_select_camera_gallery)
        val btnCamera = bottomSheetDialog.findViewById<Button>(R.id.btn_camera)
        val btnGallery = bottomSheetDialog.findViewById<Button>(R.id.btn_gallery)

        btnCamera!!.setOnClickListener {
            userChoosenTask = "CAMERA"
            val result: Boolean = MyUtils.checkPermissions(this)
            if (result) {
                launchCameraIntent()
                //dispatchTakePictureIntent();
                bottomSheetDialog.dismiss()
            } else {
                bottomSheetDialog.dismiss()
            }
        }
        btnGallery!!.setOnClickListener {
            userChoosenTask = "GALLERY"
            val result: Boolean = MyUtils. checkPermissions(this)
            if (result) {
                //galleryIntent();
                //launchGalleryIntent();
                pickFromGallery()
                bottomSheetDialog.dismiss()
            } else {
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.show()


    }

    private fun launchCameraIntent() {
        val intent = Intent(
            this,
            ImagePickerActivity::class.java
        )
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE)
        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000)
        startActivityForResult(intent, REQUEST_IMAGE)
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
            .setType("image/*")
            .addCategory(Intent.CATEGORY_OPENABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestMode)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == requestMode) {
                val selectedUri = data!!.data
                if (selectedUri != null) {
                    startCrop(selectedUri)
                } else {
                    Toast.makeText(
                        this,
                        "Cannot retrieve selected image",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
                if (resultUri != null) {
                    onCaptureImageResult(resultUri)
                } else {
                    Toast.makeText(
                        this,
                        "Cannot retrieve selected image",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (requestCode == REQUEST_IMAGE) {
                val uri = data!!.getParcelableExtra<Uri>("path")
                uri?.let { onCaptureImageResult(it) }
            }

        }

    }

    private fun startCrop(uri: Uri) {
        var destinationFileName: String = SAMPLE_CROPPED_IMAGE_NAME
        destinationFileName += ".png"
        var uCrop = UCrop.of(uri, Uri.fromFile(File(cacheDir, destinationFileName)))
        uCrop = basisConfig(uCrop)
        uCrop = advancedConfig(uCrop)
        // else start uCrop Activity
        uCrop.start(this)
    }

    private fun basisConfig(uCrop: UCrop): UCrop? {
        return uCrop
    }

    private fun advancedConfig(uCrop: UCrop): UCrop? {
        val options = UCrop.Options()
        options.setCompressionFormat(Bitmap.CompressFormat.PNG)
        options.setCompressionQuality(100)
        options.setToolbarColor(ContextCompat.getColor(this, R.color.header_color))
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.header_color))
        options.setActiveControlsWidgetColor(ContextCompat.getColor(this, R.color.white))
        options.setToolbarWidgetColor(ContextCompat.getColor(this, R.color.white))
        options.setRootViewBackgroundColor(ContextCompat.getColor(this, R.color.white))
        options.setHideBottomControls(true)
        options.setFreeStyleCropEnabled(false)
        //options.withAspectRatio(520f, 130f) //Mobile

        //options.withAspectRatio(425f,106f);//Mobile
        options.withAspectRatio(1f, 1f);//Mobile

        return uCrop.withOptions(options)
    }

    private fun onCaptureImageResult(uri: Uri) {
        try {
            MyUtils. getResizedImageBase64String(
                this,
                File(uri.path),
                mListener,
                this
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onSuccess(imagePath: String?, bitmap: Bitmap?, file: File?) {
        //requestUpdateProfileImg
        // mViewModel.requestProfileImgUpdate(imagePath.toString(), sessionManager.getUserToken())
        if(imagePath!=null){

            Glide.with(this)
                .load(imagePath)
                .into(binding.ivPreview)
           // binding.ivPreview.setImageURI()

        }
    }

    override fun onFailure(msg: String?) {
        Log.e("FAILURE", msg.toString())

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}





