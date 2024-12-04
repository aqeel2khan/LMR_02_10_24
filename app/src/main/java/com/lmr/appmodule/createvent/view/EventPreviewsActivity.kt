package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import com.lmr.R
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.databinding.ActivityPreviewsBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.PreviewsViewModel
import com.lmr.appmodule.home.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class EventPreviewsActivity : BaseActivity<ActivityPreviewsBinding>() {
    private val viewModel: AllViewModel by viewModels()
    var eventId=""
    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }

    @SuppressLint("SetTextI18n")
    override fun initUi() {
         eventId = intent.getStringExtra("eventID").toString()
        observerPostEventResponseData()
        binding.postEventBtn.setOnClickListener {
            val jsonObject = JsonObject()
            jsonObject.addProperty("eventID", eventId)
            viewModel.postEventApiCall(jsonObject)
        }



        binding.buttonReadMore.setOnClickListener {
            if (binding.textViewContent.maxLines === Int.MAX_VALUE) {
                binding.textViewContent.maxLines = 3
                binding.buttonReadMore.text = "Read More"
            } else {
                binding.textViewContent.maxLines = Int.MAX_VALUE
                binding.buttonReadMore.text = "Read Less"
            }
        }

        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Previews"
    }


    private fun observerPostEventResponseData() {
        try {
            LoaderUtil.showLoader(this)  // To show loader
            viewModel.postEventResponse.observe(this){
                when(it){
                    is NetworkErrorResult.Success->{
                        LoaderUtil.hideLoader(this)  // To
                        //     hideLoader()
                        val response = it.data
                        if(response?.success == true){
                            val intent = Intent(this@EventPreviewsActivity, MainActivity::class.java)
                            intent.putExtra("eventID", eventId.toString())
                            startActivity(intent)

                        }else{


                        }
                    }
                    is NetworkErrorResult.Error->{
                        LoaderUtil.hideLoader(this)  // To

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


    override fun getViewBinding()= ActivityPreviewsBinding.inflate(layoutInflater)
}

