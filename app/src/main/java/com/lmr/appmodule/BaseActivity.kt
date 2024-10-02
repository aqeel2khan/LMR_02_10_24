package com.lmr.appmodule

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.lmr.app_utils.AppSharedPreferences
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

abstract  class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    @Inject
    protected lateinit var sharedPreferences: AppSharedPreferences
    private var spinningDialog: Dialog? = null
    protected lateinit var binding: B

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initializeCommonObservers(getViewModel())
        initUi()
    }

    private fun initializeCommonObservers(viewModel: BaseViewModel) {
        if (setErrorAndSpinnerObserver()) {
            viewModel.error.observe(this) { message ->
                message?.let(::showError)
            }
            viewModel.spinner.observe(this) { loading ->
                if (loading) {
//                    showLoader()
                } else {
//                    hideLoader()
                }
            }
        }
    }
    private fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    protected open fun setErrorAndSpinnerObserver(): Boolean {
        return true
    }
    abstract fun getViewModel(): BaseViewModel
    abstract fun initUi()
    abstract fun getViewBinding(): B
}