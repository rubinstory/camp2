package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.Adapter.ContractAdapter
import com.example.app.Contract.ContractRepository
import com.example.app.Contract.ContractViewModel
import com.example.app.Contract.ContractViewModelFactory
import com.example.app.R
import com.example.app.RetrofitInstance
import com.example.app.User.UserRepository
import com.example.app.User.UserViewModel
import com.example.app.User.UserViewModelFactory
import com.example.app.databinding.ContractFragmentBinding

class ContractFragment: Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var contractviewModel: ContractViewModel
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
        viewModel.getUserById(RetrofitInstance.USER_ID)
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->

            if(user.is_admin){
                val contractRepository = ContractRepository()
                val contractViewModelRepository = ContractViewModelFactory(contractRepository)

                contractviewModel = ViewModelProvider(this, contractViewModelRepository).get(ContractViewModel::class.java)
                contractviewModel.getContracts()
                contractviewModel.contractList.observe(viewLifecycleOwner, Observer { contractList ->
                    contractAdapter.itemList = contractList.toMutableList()
                    binding.receiptViewpager.adapter = contractAdapter
                })

            }
            else {
                contractAdapter.itemList = user.contractList.toMutableList()
                binding.receiptViewpager.adapter = contractAdapter
            }
        })
    }
}