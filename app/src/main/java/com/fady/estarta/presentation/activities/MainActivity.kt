package com.fady.estarta.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.fady.estarta.R
import com.fady.estarta.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import utils.common.setStatusBarColor

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setStatusBarColor(R.color.black)

    }

    private fun navController() = findNavController(R.id.nav_host_fragment)

    override fun onBackPressed() {
        navController().popBackStack()
    }

}