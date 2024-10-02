package com.lmr.appmodule.appcommonView

import androidx.activity.viewModels
import com.lmr.databinding.ActivityOnBoardingBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
       return viewModel
    }

    override fun initUi() {
        binding.loginButton.setOnClickListener {
        }
    }

    override fun getViewBinding() = ActivityOnBoardingBinding.inflate(layoutInflater)

}