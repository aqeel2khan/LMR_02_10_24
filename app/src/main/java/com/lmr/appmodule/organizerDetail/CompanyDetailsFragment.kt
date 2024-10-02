package com.lmr.appmodule.organizerDetail

import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.organizerDetail.adapter.EventAdapter
import com.lmr.databinding.FragmentCompanyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyDetailsFragment : BaseActivity<FragmentCompanyDetailsBinding>() {
    private val viewModel: AllViewModel by viewModels()

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {
        eventAdapter()
    }

    private fun eventAdapter() {
        binding.recylerEvent.setLayoutManager(
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false)
        )
        val adapter = EventAdapter(this)
        binding.recylerEvent.adapter = adapter
    }

    override fun getViewBinding() = FragmentCompanyDetailsBinding.inflate(layoutInflater)


}