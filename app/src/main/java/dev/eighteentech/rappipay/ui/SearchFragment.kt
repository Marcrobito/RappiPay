package dev.eighteentech.rappipay.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.eighteentech.rappipay.R
import dev.eighteentech.rappipay.common.ItemAdapter
import dev.eighteentech.rappipay.common.ItemSelected
import dev.eighteentech.rappipay.databinding.ActivityMainBinding
import dev.eighteentech.rappipay.databinding.FragmentSearchBinding
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), ItemSelected {

    lateinit var query: String

    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: ItemAdapter

    private var listener: FragmentListener? = null

    override fun onAttach(context: Context) {
        if(context is FragmentListener)
            listener = context
        super.onAttach(context)

    }

    private var currentSearch = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        adapter = ItemAdapter(this)
        currentSearch = ""

        binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            search.setText(query)
            if (query != currentSearch)
                viewModel.search(query)


            recycler.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            recycler.adapter = adapter

            search.doOnTextChanged { text, _, _, _ ->
                Log.d("TAG",text.toString())
                text?.let {
                    if (currentSearch != it.toString()) {
                        viewModel.search(it.toString())
                        currentSearch = it.toString()
                    }
                }

            }

        }

        viewModel.searchResults.observe(viewLifecycleOwner){
            when(it){
                is Response.Success -> {
                    adapter.update(it.data)
                }
            }
        }




        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(query: String) =
            SearchFragment().apply {
                this.query = query
            }
    }

    override fun onDetach() {
        listener?.onSearchFragmentDetached()
        super.onDetach()
    }

    override fun onItemSelected(id: String, type: Type) {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        listener?.onItemSelected(id,type)
    }
}