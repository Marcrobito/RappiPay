package dev.eighteentech.rappipay.ui

import dev.eighteentech.rappipay.entities.Type

interface FragmentListener {
    fun onFragmentDetached()
    fun onItemSelected(id: String, type: Type)

}