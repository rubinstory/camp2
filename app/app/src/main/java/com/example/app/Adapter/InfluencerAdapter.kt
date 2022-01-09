package com.example.app.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
import com.example.app.databinding.MainFragmentBinding
import com.example.app.databinding.ModelProfileItemBinding

class InfluencerAdapter(private val context: Context): RecyclerView.Adapter<InfluencerAdapter.ViewHolder>() {
    private lateinit var viewPagerBinding: ModelProfileItemBinding
    var itemList = mutableListOf<Influencer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewPagerBinding = ModelProfileItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(binding: ModelProfileItemBinding): RecyclerView.ViewHolder(binding.root)  {
        private val profileImg: ImageView = binding.mainFragmentModelProfileImg
        private val profileName: TextView = binding.mainFragmentModelProfileName
        private val profileDescription: TextView = binding.mainFragmentModelProfileDescription
        private val profileCard: ConstraintLayout = binding.modelProfileItem


        fun bind(item: Influencer) {
            profileName.text = item.getFullName()
            profileDescription.text = item.description
            Glide.with(context).load(item.imageList[0].url).into(profileImg)
            profileCard.setOnClickListener {
                var modelDetailFragment = ModelDetailFragment()
                var bundle = Bundle()
                bundle.putInt("id", item.id)
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.fragment, modelDetailFragment)
                    .commitAllowingStateLoss()
            }
        }

        fun unbind() {

        }
    }
}