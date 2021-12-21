package dev.eighteentech.rappipay.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.eighteentech.rappipay.R
import dev.eighteentech.rappipay.common.Constants.IMAGE_BASE_PATH
import dev.eighteentech.rappipay.databinding.ItemBinding
import dev.eighteentech.rappipay.entities.Item
import dev.eighteentech.rappipay.entities.Type

class ItemAdapter(private val listener : ItemSelected? = null) : RecyclerView.Adapter<ItemAdapter.VH>() {

    private var items = listOf<Item>()

    inner class VH(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: VH, position: Int) = with(holder.binding) {
        imageView.setFromUrl(IMAGE_BASE_PATH+items[position].posterPath)
        var type:Type = Type.Movie
        val color = if (items[position].title != null){
            root.context.getColor(R.color.movieColor)
        }
        else{
            type = Type.Series
            root.context.getColor(R.color.seriesColor)
        }

        listener?.apply {
            root.setOnClickListener {
                this.onItemSelected(items[position].id, type)
            }
        }

        root.setBackgroundColor(color)
        title.text = items[position].title ?: items[position].name
        date.text = items[position].releaseDate ?: items[position].firstAirDate
        overview.text = items[position].overview

        return@with
    }

    override fun getItemCount() = items.size

    fun update(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}

interface ItemSelected{
    fun onItemSelected(id:String, type:Type)
}