package com.lmr.appmodule.scanning

import androidx.fragment.app.viewModels
import com.lmr.databinding.FragmentScanningBinding
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScanningFragment : BaseFragment<FragmentScanningBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {

    }
    override fun onResume() {
        super.onResume()
        handleHeader(true)
//        handleTitle("Dashboard")
    }
    override fun getViewBinding() = FragmentScanningBinding.inflate(layoutInflater)

}