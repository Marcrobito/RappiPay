package dev.eighteentech.rappipay.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.eighteentech.rappipay.R
import dev.eighteentech.rappipay.common.ItemAdapter
import dev.eighteentech.rappipay.common.ItemSelected
import dev.eighteentech.rappipay.databinding.ActivityMainBinding
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import dev.eighteentech.rappipay.detail.DetailFragment
import dev.eighteentech.rappipay.search.SearchFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemSelected, FragmentListener {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private var isFirstTry = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemAdapter(this)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recycler.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            recycler.adapter = adapter

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.popular -> viewModel.getPopular()
                    R.id.top -> viewModel.getTopRated()
                }
                binding.recycler.layoutManager?.scrollToPosition(0)
                return@setOnItemSelectedListener true
            }

            reTry.setOnClickListener {
                viewModel.getPopular()
            }

            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE)
                        viewModel.nextPage()
                }
            })
            search.doOnTextChanged { text, _, _, _ ->
                search(text)
            }
        }

        viewModel.getPopular()

        viewModel.items.observe(this) {
            binding.reTry.visibility = View.GONE
            when (it) {
                is Response.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.loader.visibility = View.GONE
                    adapter.update(it.data)
                }
                is Response.Error -> {
                    if (isFirstTry) {
                        binding.reTry.visibility = View.VISIBLE
                    }
                    binding.loader.visibility = View.GONE
                }
                else -> binding.loader.visibility = View.GONE
            }
        }
    }

    override fun onItemSelected(id: String, type: Type) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, DetailFragment.newInstance(id, type))
            .addToBackStack("Movie Detail").commit()
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun onFragmentDetached() {
        this.currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
        binding.search.setText("")
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun search(text: CharSequence?) {
        text?.let {
            if (it.isNotBlank()) {
                supportFragmentManager.beginTransaction()
                    .replace(binding.container.id, SearchFragment.newInstance(it.toString()))
                    .addToBackStack("Movie Search").commit()
                binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }
}