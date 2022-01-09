package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.app.Authentication.Authentication
import com.example.app.Authentication.AuthenticationRepository
import com.example.app.Authentication.AuthenticationViewModel
import com.example.app.Authentication.AuthenticationViewModelFactory
import com.example.app.R
import com.example.app.databinding.SignOutFragmentBinding

class SignOutFragment: Fragment() {
    private lateinit var viewModel: AuthenticationViewModel

    private var _binding: SignOutFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignOutFragmentBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        setSignOutBtn()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSignOutBtn()
    }

    override fun onResume() {
        super.onResume()
        setSignOutBtn()
    }

    fun setSignOutBtn() {
        binding.signOutBtn.setOnClickListener {
            var repository = AuthenticationRepository()
            val authenticationViewModelFactory = AuthenticationViewModelFactory(repository)
            viewModel = ViewModelProvider(this, authenticationViewModelFactory).get(AuthenticationViewModel::class.java)
            viewModel.logout()

            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, SignInFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setDataToView() {

    }
}