package io.github.soymd.daggermvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.R
import io.github.soymd.daggermvvm.databinding.ActivityMainBinding

@AndroidEntryPoint(AppCompatActivity::class)
class MainActivity : Hilt_MainActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            fizzbuzzRecyclerView.adapter = FizzBuzzAdapter(
                this@MainActivity,
                this@MainActivity.viewModel
            )
        }

    }
}