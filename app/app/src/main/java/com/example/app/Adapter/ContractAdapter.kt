package com.example.app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.Contract.Contract
import com.example.app.Portfolio.Image
import com.example.app.databinding.ContractCardItemBinding

class ContractAdapter(private var context: Context) : RecyclerView.Adapter<ContractAdapter.ViewHolder>() {
    private lateinit var viewPagerBinding: ContractCardItemBinding
    var itemList = mutableListOf<Contract>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewPagerBinding = ContractCardItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(binding: ContractCardItemBinding): RecyclerView.ViewHolder(binding.root)  {
        private val hashValue: TextView = binding.hashValue
        private val influencerName: TextView = binding.influencerName
        private val contractorName: TextView = binding.hashValue


        fun bind(item: Contract) {
            hashValue.text = "2829-4525-3958-6739"
            influencerName.text = item.influencer.getFullName()
            contractorName.text = item.user.username
        }

        fun unbind() {

        }
    }
}