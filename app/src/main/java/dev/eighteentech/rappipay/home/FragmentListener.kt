package dev.eighteentech.rappipay.home

import dev.eighteentech.rappipay.entities.Type

interface FragmentListener {
    fun onFragmentDetached()
    fun onItemSelected(id: String, type: Type)

}