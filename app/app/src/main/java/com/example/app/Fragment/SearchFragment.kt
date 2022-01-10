package com.example.app.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.app.Adapter.SearchAdapter
import com.example.app.Influencer.Influencer
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.databinding.SearchFragmentBinding
import org.w3c.dom.Text

class SearchFragment : Fragment() {
    private lateinit var viewModel: InfluencerViewModel
    private lateinit var searchAdapter: SearchAdapter
    private var influencers = mutableListOf<Influencer>()
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)
        getInfluencerListAndSetSearchAdapter()
        searchAdapter = SearchAdapter(this.requireContext())
        setSearchBarListener()
        return binding.root
    }

    fun getInfluencerListAndSetSearchAdapter() {
        val repository = InfluencerRepository()
        val influencerViewModelFactory = InfluencerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, influencerViewModelFactory).get(InfluencerViewModel::class.java)
        viewModel.getInfluencers()
        viewModel.influencerList.observe(viewLifecycleOwner, Observer { influencerList ->
            searchAdapter.itemList = influencerList.toMutableList()
            searchAdapter.filterList = influencerList.toMutableList()
            binding.searchResultView.adapter = searchAdapter
            binding.searchResultView.layoutManager = GridLayoutManager(context, 3)
        })
    }



    fun setSearchBarListener() {
        binding.searchField.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { searchAdapter.filter.filter(p0) }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}
