package dev.eighteentech.rappipay.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dev.eighteentech.rappipay.common.BaseFragment
import dev.eighteentech.rappipay.databinding.FragmentDetailBinding
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private var movieId:String = "0"
    private lateinit var type:Type
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false).apply {
            webView.settings.javaScriptEnabled = true
        }

        viewModel.loadDetail(movieId, type.getTypeName())
        viewModel.detail.observe(viewLifecycleOwner){
            when (it) {
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.movieName.text = it.data.name
                    binding.webView.visibility = View.VISIBLE
                    binding.webView.loadUrl("https://www.youtube.com/embed/${it.data.key}")
                }
                is Response.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "We couldn't load the data", Toast.LENGTH_LONG).show()
                    viewLifecycleOwner.lifecycle
                    lifecycleScope.launch {
                        delay(3000)
                        removeFragment()
                    }
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

    companion object{
        fun newInstance(movieId:String, type:Type) = DetailFragment().apply {
            this.movieId = movieId
            this.type = type
        }
    }

}