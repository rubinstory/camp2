package com.example.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.Image.Video
import com.example.app.Portfolio.Image
import com.example.app.databinding.PortfolioImageItemBinding

class ImageAdapter(private val context: Context): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private lateinit var viewPagerBinding: PortfolioImageItemBinding
    var itemList = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewPagerBinding = PortfolioImageItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(binding: PortfolioImageItemBinding): RecyclerView.ViewHolder(binding.root)  {
        private val portfolioItem: ImageView = binding.modelPortfolioItem


        fun bind(item: Image) {
            Glide.with(context).load(item.url).into(portfolioItem)
        }

        fun unbind() {

        }
    }
}