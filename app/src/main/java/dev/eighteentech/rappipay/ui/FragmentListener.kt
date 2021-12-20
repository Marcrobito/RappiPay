package dev.eighteentech.rappipay.ui

import dev.eighteentech.rappipay.entities.Type

interface FragmentListener {
    fun onSearchFragmentDetached()
    fun onItemSelected(id: String, type: Type)
    fun onDetailFragmentDetached()

}