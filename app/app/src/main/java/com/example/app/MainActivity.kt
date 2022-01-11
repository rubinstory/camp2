package com.example.app

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.annotation.ColorLong
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.Authentication.AuthenticationRepository
import com.example.app.Authentication.AuthenticationViewModel
import com.example.app.Authentication.AuthenticationViewModelFactory
import com.example.app.Fragment.SignInFragment
import com.example.app.Fragment.MainFragment
import com.example.app.Fragment.SearchFragment
import com.example.app.Fragment.SignOutFragment
import com.example.app.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AuthenticationViewModel

    private val ACCESS_GRANTED = 200
    private val UNAUTHORIZED = 401
    private val FORBIDDEN = 403

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMenuButton()
        checkTokenAuth()

        supportFragmentManager.registerFragmentLifecycleCallbacks(object: FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
                super.onFragmentAttached(fm, f, context)
                checkTokenAuth()
            }

            override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
                super.onFragmentStarted(fm, f)
                changeDropDownButtonColor(window.statusBarColor)
                checkDropDownMenuAndClose()
            }
        }, true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, MainFragment())
            .commit()
    }

    fun inintHomeBtn() {
        binding.dropdownHomeButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, MainFragment())
                .commit()
        }
    }


    fun inintSearchBtn() {
        binding.dropdownSearchBtn.setOnClickListener {
            closeDropDownMenu()
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, SearchFragment())
                .commit()
        }
    }


    fun checkDropDownMenuAndClose() {
        when(binding.dropdownLoginBtn.visibility) {
            FloatingActionButton.VISIBLE -> closeDropDownMenu()

        }
    }

    fun checkTokenAuth(): Boolean {
        val repository = AuthenticationRepository()
        val authenticationViewModelFactory = AuthenticationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, authenticationViewModelFactory).get(
            AuthenticationViewModel::class.java)
        viewModel.authenticateToken()

        var t = false
        viewModel.HTTP_STATUS.observe(this, Observer { status->
            when(status) {
                ACCESS_GRANTED -> {
                    binding.dropdownLoginBtn.setOnClickListener {
                        closeDropDownMenu()
                        supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            .replace(R.id.fragment, SignOutFragment())
                            .commit()
                    }
                }
                else -> {
                    binding.dropdownLoginBtn.setOnClickListener {
                        closeDropDownMenu()
                        supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            .replace(R.id.fragment, SignInFragment())
                            .commit()
                    }
                }
            }
        })
        return t
    }

    fun changeDropDownButtonColor(color: Int) {
        var tintColor: Int = color
        var backgroundColor: Int = ContextCompat.getColor(this, R.color.white)

        if (color == ContextCompat.getColor(this, R.color.white)) {
            backgroundColor = ContextCompat.getColor(this, R.color.black)
        }

        binding.dropdownMenuBtn.imageTintList = ColorStateList.valueOf(backgroundColor)
        binding.dropdownMenuBtn.backgroundTintList = ColorStateList.valueOf(tintColor)

        binding.dropdownHomeButton.imageTintList = ColorStateList.valueOf(tintColor)
        binding.dropdownHomeButton.backgroundTintList = ColorStateList.valueOf(backgroundColor)

        binding.dropdownSearchBtn.imageTintList = ColorStateList.valueOf(tintColor)
        binding.dropdownSearchBtn.backgroundTintList = ColorStateList.valueOf(backgroundColor)

        binding.dropdownLoginBtn.imageTintList = ColorStateList.valueOf(tintColor)
        binding.dropdownLoginBtn.backgroundTintList = ColorStateList.valueOf(backgroundColor)
    }

    fun initMenuButton() {
        binding.dropdownMenuBtn.setOnClickListener{
            when(binding.dropdownLoginBtn.visibility) {
                FloatingActionButton.GONE -> openDropDownMenu()
                FloatingActionButton.VISIBLE -> closeDropDownMenu()
            }
        }
        inintHomeBtn()
        inintSearchBtn()
    }

    fun openDropDownMenu() {
        binding.dropdownMenuBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_open_with_rotate))

        binding.dropdownHomeButton
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.dropdownHomeButton.visibility = FloatingActionButton.VISIBLE;
        binding.dropdownHomeButton.isClickable = true

        binding.dropdownLoginBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.dropdownLoginBtn.visibility = FloatingActionButton.VISIBLE;
        binding.dropdownLoginBtn.isClickable = true

        binding.dropdownSearchBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.dropdownSearchBtn.visibility = FloatingActionButton.VISIBLE;
        binding.dropdownSearchBtn.isClickable = true
    }

    fun closeDropDownMenu() {
        binding.dropdownMenuBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_close_with_rotate))

        binding.dropdownHomeButton
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.dropdownHomeButton.visibility = FloatingActionButton.GONE;
        binding.dropdownHomeButton.isClickable = false

        binding.dropdownLoginBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.dropdownLoginBtn.visibility = FloatingActionButton.GONE;
        binding.dropdownLoginBtn.isClickable = false

        binding.dropdownSearchBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.dropdownSearchBtn.visibility = FloatingActionButton.GONE;
        binding.dropdownSearchBtn.isClickable = false
    }
}