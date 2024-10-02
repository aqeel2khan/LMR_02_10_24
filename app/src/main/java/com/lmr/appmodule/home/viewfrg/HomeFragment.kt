package com.lmr.appmodule.home.viewfrg

import android.content.Intent
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.lmr.R
import com.lmr.appmodule.BaseFragment
import com.lmr.appmodule.createvent.viewmodel.AllViewModel
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.eventorganizorlist.view.EventOrganizerListActivity
import com.lmr.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: AllViewModel by viewModels()
    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {

        showImage()

    }

    private fun showImage() {


        binding.ivNextOrganizeList.setOnClickListener {

         try {
             requireActivity(). startActivity(Intent(requireActivity(),EventOrganizerListActivity::class.java))
         } catch (e: Exception) {
            e.printStackTrace()
         }
        }


        Glide.with(this)
            .load(R.drawable.img_9)
            .into(binding. ivToday1);

        Glide.with(this)
            .load(R.drawable.girls_img)
            .into(binding. girlImg);

        Glide.with(this)
            .load(R.drawable.nbk_img)
            .into(binding. nbkImg);

        Glide.with(this)
            .load(R.drawable.galf_imag)
            .into(binding. galfImg);

        Glide.with(this)
            .load(R.drawable.img_10)
            .into(binding. img10);

        Glide.with(this)
            .load(R.drawable.person_icon)
            .into(binding. ivPerson);

        Glide.with(this)
            .load(R.drawable.round_img)
            .into(binding. ivRound);

        Glide.with(this)
            .load(R.drawable.company_img)
            .into(binding. ivCompany);

        Glide.with(this)
            .load(R.drawable.img_11)
            .placeholder(R.drawable.image_icon)
            .into(binding. iv11);

        Glide.with(this)
            .load(R.drawable.img_12)
            .into(binding. iv12);

        Glide.with(this)
            .load(R.drawable.img_13)
            .into(binding. iv13);

        Glide.with(this)
            .load(R.drawable.img_14)
            .into(binding. iv16);

        Glide.with(this)
            .load(R.drawable.img_15)
            .into(binding. iv15);



    }

    override fun onResume() {
        super.onResume()
        handleHeader(true)
//        handleTitle("Find events in")
    }

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

}