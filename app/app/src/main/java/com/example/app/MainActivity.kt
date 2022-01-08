package com.example.app

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.app.Fragment.LoginFragment
import com.example.app.Fragment.MainFragment
import com.example.app.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMenuButton()
        initLoginButton()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, MainFragment())
            .commit()
    }

    fun initLoginButton() {
        binding.dropdownLoginBtn.setOnClickListener {
            closeDropDownMenu()
            changeDropDownButtonColor("black")
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, LoginFragment())
                .commit()
        }
    }

    fun changeDropDownButtonColor(color: String) {
        var tintColor: Int = R.color.black
        var backgroundColor: Int = R.color.white

        if (color == "white") {
            tintColor = R.color.white
            backgroundColor = R.color.black
        }

        binding.dropdownMenuBtn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, tintColor))
        binding.dropdownMenuBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, backgroundColor))

        binding.dropdownSearchBtn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, backgroundColor))
        binding.dropdownSearchBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, tintColor))

        binding.dropdownLoginBtn.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, backgroundColor))
        binding.dropdownLoginBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, tintColor))
    }

    fun initMenuButton() {
        binding.dropdownMenuBtn.setOnClickListener{
            when(binding.dropdownLoginBtn.visibility) {
                FloatingActionButton.INVISIBLE -> openDropDownMenu()
                FloatingActionButton.VISIBLE -> closeDropDownMenu()
            }
        }
    }

    fun openDropDownMenu() {
        binding.dropdownMenuBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_open_with_rotate))

        binding.dropdownLoginBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.dropdownLoginBtn.visibility = FloatingActionButton.VISIBLE;

        binding.dropdownSearchBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_from_top))
        binding.dropdownSearchBtn.visibility = FloatingActionButton.VISIBLE;

    }

    fun closeDropDownMenu() {
        binding.dropdownMenuBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_close_with_rotate))

        binding.dropdownLoginBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.dropdownLoginBtn.visibility = FloatingActionButton.INVISIBLE;

        binding.dropdownSearchBtn
            .startAnimation(AnimationUtils.loadAnimation(this, R.anim.dropdown_to_top))
        binding.dropdownSearchBtn.visibility = FloatingActionButton.INVISIBLE;
    }
}