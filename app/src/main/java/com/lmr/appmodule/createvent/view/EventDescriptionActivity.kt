package com.lmr.appmodule.createvent.view
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.lmr.R
import com.lmr.databinding.ActivityEventDescriptionBinding
import com.lmr.app_utils.MyConstant
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
//import com.github.dhaval2404.imagepicker.ImagePicker
import com.lmr.app_utils.PermissionKeys
import com.lmr.appmodule.createvent.model.description.EventDescriptionPost
import com.lmr.appmodule.createvent.viewmodel.EventDescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class EventDescriptionActivity : BaseActivity<ActivityEventDescriptionBinding>() {
    var imageFront: File? =null
    private val viewModel: EventDescriptionViewModel by viewModels()

    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }

    @SuppressLint("SetTextI18n")
    override fun initUi() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.saveAndContinueButton.setOnClickListener {
            postDescriptionData()
            //startActivity(Intent(this, DateTimeEventCaptureActivity::class.java))
        }
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "More Details"

        requestPermission()



        addImage()
    }

    private fun postDescriptionData() {

      var decriptionData=  binding.etsyedali.text.toString()

        val eventDescriptionPost = EventDescriptionPost(
            eventDescriptionID = 0,
            eventID = 211,
            eventDescriptions = decriptionData
        )
        viewModel.   callPostDescription(eventDescriptionPost)

        postDescriptionResponseData()


    }

    private fun postDescriptionResponseData() {
        try {
            //   showLoader()
            viewModel.postDescriptionResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        viewModel.postDescriptionResponse.removeObservers(this)
                        if (viewModel.postDescriptionResponse.hasObservers()) return@observe
                        //     hideLoader()
                        lifecycleScope.launch {
                            it.let {
                                val response = it.data

                                if(response?.success == true){

                                  var eventId= response.data.eventID;

                                    startActivity(Intent(this@EventDescriptionActivity, DateTimeEventCaptureActivity::class.java))
                                }else{


                                }

                            }
                        }
                    }
                    is NetworkErrorResult.Error->{
                        viewModel.postDescriptionResponse.removeObservers(this)
                        if ( viewModel.postDescriptionResponse.hasObservers()) return@observe
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


    private fun addImage() {

        binding.addImage.setOnClickListener {
            if(PermissionKeys.checkPermission1(this)){
//                ImagePicker.with(this)
//                    .crop()	    			//Crop image(Optional), Check Customization for more option
//                    .start()
            }else
                requestPermission()  }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            MyConstant.PERMISSION_REQUEST_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val uri: Uri = data?.data!!
                val imageFile = File(getPath(uri)!!)
                // Use Uri object instead of File to avoid storage permissions

                    imageFront=imageFile
                //    mpref!!.setImageFront(imageFront.toString())
                    Glide.with(this)
                        .load(uri)
                        .placeholder(R.drawable.profile_image)
                        .into(binding.imageShow)


                //  mBinding.profileImage.setImageURI(uri)
                //  uploadImageApi(imageFile,mpref!!.getUserID("").toString())
            }
//            ImagePicker.RESULT_ERROR -> {
//                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getPath(uri: Uri?): String? {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = this.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            val column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val path = cursor.getString(column_index)
            cursor.close()
            return path
        }
        // this is our fallback here
        return uri.path
    }

    override fun getViewBinding() = ActivityEventDescriptionBinding.inflate(layoutInflater)
}