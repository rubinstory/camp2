package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.app.R
import com.example.app.Register.Register
import com.example.app.Register.RegisterRepository
import com.example.app.Register.RegisterViewModel
import com.example.app.Register.RegisterViewModelFactory
import com.example.app.databinding.SignOutFragmentBinding
import com.example.app.databinding.SignupFragmentBinding

class SignOutFragment: Fragment() {
//    private lateinit var viewModel: RegisterViewModel

//    private var name: String = ""
//    private var email: String = ""
    private var _binding: SignOutFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignOutFragmentBinding.inflate(inflater, container, false)
        setSignOutBtn()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun setSignOutBtn() {
        binding.signOutBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, LoginFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setDataToView() {

    }
}