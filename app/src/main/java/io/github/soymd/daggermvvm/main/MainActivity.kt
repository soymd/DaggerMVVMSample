package io.github.soymd.daggermvvm.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.R
import io.github.soymd.daggermvvm.common.ArgumentKeys
import io.github.soymd.daggermvvm.databinding.ActivityMainBinding
import io.github.soymd.daggermvvm.di.AppViewModelProviders
import io.github.soymd.daggermvvm.di.ViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModelProviders = AppViewModelProviders()

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getSerializableExtra(ArgumentKeys.VIEW_MODEL_PROVIDERS.key)?.let {
            viewModelProviders = it as AppViewModelProviders
        }

        viewModel = viewModelProviders.of(this, viewModelFactory).get(
            MainViewModel::class.java
        )

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

        // MVVMを使わない場合の例
        binding.minusButton.setOnClickListener {
            val repository = MainRepositoryImpl(context = applicationContext)
            val newCount = repository.getCount() - 1
            repository.saveCount(newCount)
            binding.countText.text = newCount.toString()
        }
    }
}