package dev.eighteentech.rappipay.common

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import dev.eighteentech.rappipay.ui.FragmentListener

abstract class BaseFragment: Fragment() {

    private var listener: FragmentListener? = null

    override fun onAttach(context: Context) {
        if(context is FragmentListener)
            listener = context
        super.onAttach(context)
        requireActivity().currentFocus?.let { view ->
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun removeFragment(){
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        listener?.onFragmentDetached()
    }
}