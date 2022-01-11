package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.Adapter.AdminContractAdapter
import com.example.app.Adapter.ContractAdapter
import com.example.app.Contract.ContractViewModel
import com.example.app.Influencer.Influencer
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.databinding.AdminContractFragmentBinding

class AdminContractFragment: Fragment() {
    private lateinit var viewModel: InfluencerViewModel
    private lateinit var contractviewModel: ContractViewModel
    private var _binding: AdminContractFragmentBinding? = null
    private val binding get() = _binding!!
    private var influencerId: Int = 1

    private lateinit var admincontractAdapter: AdminContractAdapter
    private lateinit var influencer: Influencer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AdminContractFragmentBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        influencerId = arguments?.getInt("influencerId")!!

        loadContracts()
        return binding.root
    }

    fun loadContracts() {
        val repository = InfluencerRepository()
        val influencerViewModelRepository = InfluencerViewModelFactory(repository)

        viewModel = ViewModelProvider(this, influencerViewModelRepository).get(InfluencerViewModel::class.java)
        viewModel.getInfluencerById(influencerId)
        viewModel.influencer.observe(viewLifecycleOwner, Observer { influencer ->
            admincontractAdapter = AdminContractAdapter(this.requireContext(), influencer)
            admincontractAdapter.itemList = influencer.contractList.toMutableList()
            binding.receiptViewpager.adapter = admincontractAdapter
        })
    }
}