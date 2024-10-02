package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.TextView
import androidx.activity.viewModels
import com.lmr.R
import com.lmr.databinding.ActivityPreviewsBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.PreviewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class EventPreviewsActivity : BaseActivity<ActivityPreviewsBinding>() {
    private val viewModel: PreviewsViewModel by viewModels()

    override fun getViewModel(): BaseViewModel {
        return  viewModel
    }

    @SuppressLint("SetTextI18n")
    override fun initUi() {
        binding.savePostBtn.setOnClickListener {

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

    override fun getViewBinding()= ActivityPreviewsBinding.inflate(layoutInflater)
}

