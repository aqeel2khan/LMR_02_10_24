package com.lmr.appmodule.home.view

import android.os.Bundle
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.lmr.R
import com.lmr.app_utils.makeGone
import com.lmr.app_utils.makeVisible
import com.lmr.appmodule.BaseActivity
import com.lmr.appmodule.createvent.viewmodel.AllViewModel

import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: AllViewModel by viewModels()
    private lateinit var navController: NavController

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initUi() {
        try {
            navController = findNavController(R.id.main_fragment)

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
            navController = navHostFragment.navController

            binding.tvHeading.text = "Home"
            navigateToRootNode(R.id.home_navigation)
            setUpBottomBar()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setUpBottomBar() {
        try {
            binding.bottomNavBar.setItemSelected(R.id.home_fragment)
            binding.bottomNavBar.setOnItemSelectedListener {
                when (it) {
                    R.id.home_fragment -> {
                        binding.tvHeading.text = "Home"
                        navigateToRootNode(R.id.home_navigation)
                    }
                    R.id.workout_fragment -> {
                        binding.tvHeading.text = "Search"
                        navigateToRootNode(R.id.search_navigation)

                    }
                    R.id.meal_fragment -> {
                        binding.tvHeading.text = "Events "
                        navigateToRootNode(R.id.event_navigation)
                    }
                    /*R.id.shopping_fragment -> {
                        binding.tvHeading.text = "Shopping "
                        navigateToRootNode(R.id.shopping_navigation)
                    }*/
                    R.id.vendor_fragment -> {
                        binding.tvHeading.text = "Shopping "
                        navigateToRootNode(R.id.vendor_navigation)
                    }
                    R.id.scanning_fragment -> {
                        binding.tvHeading.text = "Scanning"
                        navigateToRootNode(R.id.scanning_navigation)
                    }
                }
            }
        } catch (e: Exception) {
           e.printStackTrace()
        }
    }

    private fun navigateToRootNode(startDestinationId: Int, bundle: Bundle? = null) {
        try {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(startDestinationId, false)
                .build()

            navController.navigate(startDestinationId, bundle, navOptions)
        } catch (e: Exception) {
           e.printStackTrace()
        }
    }

    fun handleHeader(isVisible: Boolean = true, perform: () -> Unit = {}) {
        try {
            if (isVisible) {
                binding.headerTitle.makeVisible()
            } else {
                binding.headerTitle.makeGone()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    fun handleBottomBar(isVisible: Boolean = true, perform: () -> Unit = {}) {
        try {
            if (isVisible) {
                binding.rlBottomLayout.makeVisible()
            } else {
                binding.rlBottomLayout.makeGone()
            }
        } catch (e: Exception) {
          e.printStackTrace()
        }
    }

    fun handleTitle(headerTitle: String, perform: () -> Unit = {}){
        try {
            binding.tvHeading.text = headerTitle
            when (headerTitle) {
                resources.getString(R.string.home) -> {
    //                binding.ivHome.setImageResource(R.drawable.sel_home)
    //                binding.ivWorkout.setImageResource(R.drawable.iv_workout)
    //                binding.ivMeal.setImageResource(R.drawable.iv_meal)
    //                binding.ivShop.setImageResource(R.drawable.iv_shoping)
    //                binding.ivScan.setImageResource(R.drawable.iv_scaning)
                }
                resources.getString(R.string.searches) -> {

    //                binding.ivHome.setImageResource(R.drawable.iv_home)
    //                binding.ivWorkout.setImageResource(R.drawable.sel_training)
    //                binding.ivMeal.setImageResource(R.drawable.iv_meal)
    //                binding.ivShop.setImageResource(R.drawable.iv_shoping)
    //                binding.ivScan.setImageResource(R.drawable.iv_scaning)
                }
                resources.getString(R.string.create_events) -> {
    //                binding.ivHome.setImageResource(R.drawable.iv_home)
    //                binding.ivWorkout.setImageResource(R.drawable.iv_workout)
    //                binding.ivMeal.setImageResource(R.drawable.sel_meal)
    //                binding.ivShop.setImageResource(R.drawable.iv_shoping)
    //                binding.ivScan.setImageResource(R.drawable.iv_scaning)
                }

                resources.getString(R.string.shopping) -> {
    //                binding.ivHome.setImageResource(R.drawable.iv_home)
    //                binding.ivWorkout.setImageResource(R.drawable.iv_workout)
    //                binding.ivMeal.setImageResource(R.drawable.iv_meal)
    //                binding.ivShop.setImageResource(R.drawable.sel_shopping)
    //                binding.ivScan.setImageResource(R.drawable.iv_scaning)
                }
                resources.getString(R.string.my_account) -> {
    //                binding.ivHome.setImageResource(R.drawable.iv_home)
    //                binding.ivWorkout.setImageResource(R.drawable.iv_workout)
    //                binding.ivMeal.setImageResource(R.drawable.iv_meal)
    //                binding.ivShop.setImageResource(R.drawable.iv_shoping)
    //                binding.ivScan.setImageResource(R.drawable.sel_scanning)
                }
    //            sessionManager.getName() -> {
    //                binding.ivHome.setImageResource(R.drawable.iv_home)
    //                binding.ivWorkout.setImageResource(R.drawable.iv_workout)
    //                binding.ivMeal.setImageResource(R.drawable.iv_meal)
    //                binding.ivShop.setImageResource(R.drawable.iv_shoping)
    //                binding.ivScan.setImageResource(R.drawable.sel_scanning)
    //            }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.menu_bottom)
        val menu = popupMenu.menu
      //  binding.bottomBar.setupWithNavController(menu, navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
//
//    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

}