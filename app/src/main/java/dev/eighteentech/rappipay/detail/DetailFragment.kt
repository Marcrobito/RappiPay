package dev.eighteentech.rappipay.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dev.eighteentech.rappipay.R
import dev.eighteentech.rappipay.common.BaseFragment
import dev.eighteentech.rappipay.common.setFromUrl
import dev.eighteentech.rappipay.common.setTMDBUrl
import dev.eighteentech.rappipay.databinding.FragmentDetailBinding
import dev.eighteentech.rappipay.entities.Detail.Movie
import dev.eighteentech.rappipay.entities.Detail.TV
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private var movieId: String = "0"
    private lateinit var type: Type
    private val viewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            webView.settings.javaScriptEnabled = true
        }

        viewModel.loadDetail(movieId, type)
        viewModel.video.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.progressBar.visibility = View.GONE

                    //binding.movieName.text = it.data.name
                    binding.webView.visibility = View.VISIBLE
                    binding.webView.loadUrl("https://www.youtube.com/embed/${it.data.key}")
                }
                is Response.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.poster.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        "We couldn't load the video",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.detail.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.progressBar.visibility = View.GONE
                    it.data.apply {
                        when (this) {
                            is Movie -> setMovie(this)
                            is TV -> setTV(this)
                        }
                    }
                }
                is Response.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

    private fun setMovie(movie: Movie) = with(binding) {
        movieName.text = movie.title
        tagline.text = movie.tagline
        date.text = movie.releaseDate
        poster.setTMDBUrl(movie.posterPath)
        release.text = getString(R.string.release_date)
        rate.text = movie.voteAverage.toString()
        overview.text = movie.overview
    }

    private fun setTV(tv: TV) = with(binding) {
        movieName.text = tv.title
        tagline.text = tv.tagline
        date.text = tv.firstAirDate
        poster.setFromUrl(tv.posterPath)
        release.text = getString(R.string.first_aired)
        rate.text = tv.voteAverage.toString()
        overview.text = tv.overview
    }

    companion object {
        fun newInstance(movieId: String, type: Type) = DetailFragment().apply {
            this.movieId = movieId
            this.type = type
        }
    }

}