package io.github.soymd.daggermvvm.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.R
import io.github.soymd.daggermvvm.count.CountActivity
import io.github.soymd.daggermvvm.databinding.ActivityMainBinding
import io.github.soymd.daggermvvm.fizzbuzz.FizzBuzzFragment

@AndroidEntryPoint(AppCompatActivity::class)
class MainActivity : Hilt_MainActivity() {
    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

        viewModel.countActivityEvent.observe(this, {
            this@MainActivity.callCountActivity()
        })
        viewModel.fizzBuzzFragmentEvent.observe(this, {
            this@MainActivity.callFizzBuzzFragment()
        })
    }

    private fun callCountActivity() {
        val intent = Intent(applicationContext, CountActivity::class.java)
        startActivity(intent)
    }

    private fun callFizzBuzzFragment() {
        val fragment = FizzBuzzFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityRoot, fragment)
            .addToBackStack(null)
            .commit()
    }
}