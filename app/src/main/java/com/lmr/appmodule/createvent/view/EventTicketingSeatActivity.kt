package com.lmr.appmodule.createvent.view

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import com.lmr.R
import com.lmr.databinding.ActivityTicketingSeatBinding
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.createvent.viewmodel.TicketingSeatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class EventTicketingSeatActivity : BaseActivity<ActivityTicketingSeatBinding>() {
    private val viewModel: TicketingSeatViewModel by viewModels()

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
    @SuppressLint("SetTextI18n")
    override fun initUi() {
        val detailsTextView = findViewById<TextView>(R.id.detailsTextView)
        detailsTextView.text = "Ticketing/ Seat Details"
        val spinner = findViewById<Spinner>(R.id.VisibilitySpinner)
        val array = arrayListOf("Visible", "Not Visible")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        binding.addbttn.setOnClickListener {
            startActivity(Intent(this, EventPreviewsActivity::class.java))
        }
    }
    override fun getViewBinding() = ActivityTicketingSeatBinding.inflate(layoutInflater)
}