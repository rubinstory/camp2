package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.MediaController
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.app.Adapter.ContractAdapter
import com.example.app.Adapter.InfluencerAdapter
import com.example.app.Contract.ContractRepository
import com.example.app.Contract.ContractViewModel
import com.example.app.Contract.ContractViewModelFactory
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.RetrofitInstance
import com.example.app.User.UserRepository
import com.example.app.User.UserViewModel
import com.example.app.User.UserViewModelFactory
import com.example.app.databinding.ContractFragmentBinding

class ContractFragment: Fragment() {
    private lateinit var viewModel: UserViewModel
    private var _binding: ContractFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var contractAdapter: ContractAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContractFragmentBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        contractAdapter = ContractAdapter(this.requireContext())
        loadContracts()
        return binding.root
    }

    fun loadContracts() {
        val repository = UserRepository()
        val userViewModelRepository = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, userViewModelRepository).get(UserViewModel::class.java)
        viewModel.getUserById(RetrofitInstance.TOKENUSERID)
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            contractAdapter.itemList = user.contractList.toMutableList()
            binding.receiptViewpager.adapter = contractAdapter
        })
    }
}