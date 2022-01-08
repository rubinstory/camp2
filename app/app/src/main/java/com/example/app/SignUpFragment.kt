package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.databinding.SignupFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel

    private var name: String = ""
    private var email: String = ""
    private var _binding: SignupFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupFragmentBinding.inflate(inflater, container, false)
        if (arguments?.isEmpty == false) {
            name = arguments?.getString("name")!!
            email = arguments?.getString("email")!!
        }
        setView()
        initSignUpButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()

    }

    fun initSignUpButton() {
        binding.signUpBtn.setOnClickListener {
            signUp()
        }
    }


    fun setView() {
        binding.nameInputField.setText(name)
        binding.emailInputField.setText(email)
    }

    fun signUp() {
        var newUser = Register()
        val repository = RegisterRepository()
        val registerViewModelFactory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)
        newUser.username = binding.idInputField.text.toString()
        newUser.email = binding.emailInputField.text.toString()
        newUser.password1 = binding.pwInputField.text.toString()
        newUser.password2 = binding.pwCheckInputField.text.toString()
        viewModel.registerNewUser(newUser)
        viewModel.registerInfo.observe(viewLifecycleOwner, Observer { token ->
            RetrofitInstance.setAccessToken(token.access_token!!)
            RetrofitInstance.setRefreshToken(token.refresh_token!!)
            Log.d("TOKEN", token.access_token!!)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setDataToView() {

    }
}