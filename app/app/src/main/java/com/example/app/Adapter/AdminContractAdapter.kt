package com.example.app.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.Contract.Contract
import com.example.app.Fragment.ContractFragment
import com.example.app.Influencer.Influencer
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.MainActivity
import com.example.app.Portfolio.Image
import com.example.app.RetrofitInstance
import com.example.app.User.UserRepository
import com.example.app.User.UserViewModel
import com.example.app.User.UserViewModelFactory
import com.example.app.databinding.ContractCardItemBinding

class AdminContractAdapter(private var context: Context, private var influencer: Influencer) : RecyclerView.Adapter<AdminContractAdapter.ViewHolder>() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var influencerViewModel: InfluencerViewModel
    private var i : Int = 0

    private lateinit var viewPagerBinding: ContractCardItemBinding
    var itemList = mutableListOf<Contract>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewPagerBinding = ContractCardItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(itemList[position]) }

    override fun getItemCount(): Int { return itemList.size }

    inner class ViewHolder(binding: ContractCardItemBinding): RecyclerView.ViewHolder(binding.root)  {
        private val hashValue: TextView = binding.hashValue
        private val influencerName: TextView = binding.influencerName
        private val contractorName: TextView = binding.contractorName
        private val signatureImg: ImageView = binding.signatureImg

        fun bind(item: Contract) {

            hashValue.text = item.id.toString()

            influencerName.text = influencer.getFullName()

//            contractorName.text = influencer.contractList[i].user_id.toString()

            Glide.with((context) as MainActivity).load(item.signature).into(signatureImg)
            Log.d("SIGNATURE_IMG", item.signature)

            var repository =  UserRepository()
            var userViewModelFactory = UserViewModelFactory(repository)
            userViewModel = ViewModelProvider((context as MainActivity), userViewModelFactory).get(UserViewModel::class.java)
            userViewModel.getUserById(influencer.contractList[i].user_id)
            userViewModel.user.observe((context as MainActivity), Observer { user ->
                contractorName.text = user.username
            })

            i ++
        }
    }


//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }

}