package com.example.app.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.Fragment.ModelDetailFragment
import com.example.app.Influencer.Influencer
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.MainActivity
import com.example.app.R
import com.example.app.databinding.CircleImgViewBinding
import com.example.app.databinding.MainFragmentBinding
import com.example.app.databinding.ModelProfileItemBinding

class SearchAdapter(private val context: Context): RecyclerView.Adapter<SearchAdapter.ViewHolder>(),
    Filterable {
    private lateinit var viewPagerBinding: CircleImgViewBinding
    var itemList = mutableListOf<Influencer>()
    var filterList = mutableListOf<Influencer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewPagerBinding = CircleImgViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterList[position])
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    inner class ViewHolder(binding: CircleImgViewBinding): RecyclerView.ViewHolder(binding.root)  {
        private val profileImg: ImageView = binding.modelProfileImg
        fun bind(item: Influencer) {
            Glide.with(context).load(item.imageList[0].url).into(profileImg)
            profileImg.setOnClickListener {
                val modelDetailFragment = ModelDetailFragment()
                val bundle = Bundle()
                bundle.putInt("id", item.id)
                modelDetailFragment.arguments = bundle
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.fragment, modelDetailFragment)
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint.toString()

                filterList = if (keyword.isEmpty()) {
                    itemList
                } else {
                    val tempList = mutableListOf<Influencer>()
                    for (influencer in itemList) {
                        if (influencer.getFullName().lowercase().contains(keyword.lowercase())) {
                            tempList.add(influencer)
                        }
                    }
                    tempList
                }
                val filterResult = FilterResults()
                filterResult.values = filterList
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as MutableList<Influencer>
                notifyDataSetChanged()
            }

        }
    }
}