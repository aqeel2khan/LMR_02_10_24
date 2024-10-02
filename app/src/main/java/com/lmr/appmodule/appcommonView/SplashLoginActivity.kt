package com.lmr.appmodule.appcommonView

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import com.lmr.R
import com.lmr.databinding.ActivitySplashLoginBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.EjzoViewModel
import com.lmr.appmodule.home.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashLoginActivity : BaseActivity<ActivitySplashLoginBinding>() {
    private val viewModel: EjzoViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getViewBinding() = ActivitySplashLoginBinding.inflate(layoutInflater)

    @SuppressLint("ResourceAsColor")
    override fun initUi() {
        try {
            binding.personalTextView.setOnClickListener {
                binding.personalTextView.setBackgroundResource(R.drawable.circle_shap_allo)
                binding.personalTextView.setTextColor(R.color.blue)
                binding.businessTextView.setBackgroundResource(R.drawable.circle_shap)
                binding.businessTextView.setTextColor(R.color.grey)
            }
            binding.businessTextView.setOnClickListener {
                binding.businessTextView.setBackgroundResource(R.drawable.circle_shap_allo)
                binding.businessTextView.setTextColor(R.color.blue)
                binding.personalTextView.setBackgroundResource(R.drawable.circle_shap)
                binding.personalTextView.setTextColor(R.color.grey)
            }

            binding.loginButton.setOnClickListener {
                try {
                    val intentObj = Intent(this, MainActivity::class.java)
                    val numberType = binding.numberEditText.text.toString()
                    val countryCode = binding.countryCodeText.text.toString()
                    intentObj.putExtra("number", numberType)
                    intentObj.putExtra("CountryCode", countryCode)
                    startActivity(intentObj)
                } catch (e: Exception) {
                   e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}