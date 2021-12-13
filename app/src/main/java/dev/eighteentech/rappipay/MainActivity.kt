package dev.eighteentech.rappipay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dev.eighteentech.rappipay.common.ItemAdapter
import dev.eighteentech.rappipay.common.ItemSelected
import dev.eighteentech.rappipay.databinding.ActivityMainBinding
import dev.eighteentech.rappipay.entities.Response
import dev.eighteentech.rappipay.entities.Type
import dev.eighteentech.rappipay.ui.DetailFragment
import dev.eighteentech.rappipay.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, ItemSelected {

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

            bottomNavigationView.setOnNavigationItemSelectedListener(this@MainActivity)

            reTry.setOnClickListener {
                viewModel.getPopular()
            }

            recycler.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.nextPage()
                    }
                }
            })
        }

        viewModel.getPopular()

        viewModel.items.observe(this){
            binding.reTry.visibility = View.GONE
                when(it){
                is Response.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.loader.visibility = View.GONE
                    adapter.update(it.data)
                }
                is Response.Error -> {
                    if(isFirstTry){
                        binding.reTry.visibility = View.VISIBLE
                    }
                    binding.loader.visibility = View.GONE
                }
                else -> binding.loader.visibility = View.GONE
            }
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.popular -> {
                viewModel.getPopular()
            }
            R.id.top -> viewModel.getTopRated()
        }
        binding.recycler.layoutManager?.scrollToPosition(0)

        return true
    }

    override fun onItemSelected(id: String, type: Type) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, DetailFragment.newInstance(id, type))
            .addToBackStack("Movie Detail").commit()
    }
}