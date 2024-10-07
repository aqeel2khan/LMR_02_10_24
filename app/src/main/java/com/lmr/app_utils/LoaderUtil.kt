import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object LoaderUtil {

    // Show Loader
    fun showLoader(activity: AppCompatActivity) {
        val fragmentManager = activity.supportFragmentManager

        // Check if the LoaderDialogFragment is already shown
        if (fragmentManager.findFragmentByTag(LoaderDialogFragment.TAG) == null) {
            val loaderDialog = LoaderDialogFragment.newInstance()
            loaderDialog.isCancelable = false // Optional, prevent dismissal by user
            loaderDialog.show(fragmentManager, LoaderDialogFragment.TAG)
        }
    }

    // Hide Loader
    fun hideLoader(activity: AppCompatActivity) {
        val fragmentManager = activity.supportFragmentManager
        val loaderDialog = fragmentManager.findFragmentByTag(LoaderDialogFragment.TAG) as? LoaderDialogFragment
        loaderDialog?.dismissAllowingStateLoss()
    }

    // Show Loader in Fragment
    fun showLoader(fragment: Fragment) {
        val fragmentManager = fragment.parentFragmentManager
        if (fragmentManager.findFragmentByTag(LoaderDialogFragment.TAG) == null) {
            val loaderDialog = LoaderDialogFragment.newInstance()
            loaderDialog.isCancelable = false
            loaderDialog.show(fragmentManager, LoaderDialogFragment.TAG)
        }
    }

    // Hide Loader in Fragment
    fun hideLoader(fragment: Fragment) {
        val fragmentManager = fragment.parentFragmentManager
        val loaderDialog = fragmentManager.findFragmentByTag(LoaderDialogFragment.TAG) as? LoaderDialogFragment
        loaderDialog?.dismissAllowingStateLoss()
    }
}
