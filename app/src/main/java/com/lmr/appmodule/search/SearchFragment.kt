package com.lmr.appmodule.search
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lmr.appmodule.search.adapter.SearchEventAdapter
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
    override fun initUi() {
        setRecylerServices()
    }
    override fun getViewBinding() = FragmentSearchBinding.inflate(layoutInflater)
    private fun setRecylerServices() {
        val manager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerEvent.layoutManager = manager
        var adapter  = SearchEventAdapter()
        binding.recyclerEvent.adapter=adapter
        adapter.notifyDataSetChanged();
    }
}