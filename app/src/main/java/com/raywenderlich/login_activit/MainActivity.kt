package com.raywenderlich.login_activit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raywenderlich.login_activit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {





    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Login_activit)
        setContentView(binding.root)
    }
}