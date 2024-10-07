import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lmr.R

class LoaderDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with a ProgressBar
        return inflater.inflate(R.layout.fragment_loader_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false) // Prevent user from dismissing the dialog
    }

    companion object {
        const val TAG = "LoaderDialogFragment"

        fun newInstance(): LoaderDialogFragment {
            return LoaderDialogFragment()
        }
    }
}
