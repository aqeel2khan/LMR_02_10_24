package com.lmr.appmodule.appcommonView

import androidx.activity.viewModels
import com.lmr.databinding.ActivityBuySubscriptionBinding
import com.lmr.appmodule.BaseActivity

import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuySubscriptionActivity : BaseActivity<ActivityBuySubscriptionBinding>() {
  private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {
        buttonClicks()
    }

    private fun buttonClicks() {

    }

    override fun getViewBinding() = ActivityBuySubscriptionBinding.inflate(layoutInflater)
}