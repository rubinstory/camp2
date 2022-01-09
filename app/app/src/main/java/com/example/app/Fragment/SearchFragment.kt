package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.app.R
import com.example.app.databinding.SearchFragmentBinding
import com.example.app.databinding.SignOutFragmentBinding

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)

//        binding.gridView = View.INVISIBLE
//
//        var influencers = arrayOf("Hannah", "Goodal")
//
//        val adapter = ArrayAdapter<>(R.layout.circle_img_view)
        return binding.root

    }
}
