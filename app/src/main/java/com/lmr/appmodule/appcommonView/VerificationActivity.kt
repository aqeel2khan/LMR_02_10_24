package com.lmr.appmodule.appcommonView

import androidx.activity.viewModels
import com.lmr.databinding.ActivityVerificationBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationActivity : BaseActivity<ActivityVerificationBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {
        binding.verificationButton.setOnClickListener {
        }

    }

    override fun getViewBinding() = ActivityVerificationBinding.inflate(layoutInflater)
}