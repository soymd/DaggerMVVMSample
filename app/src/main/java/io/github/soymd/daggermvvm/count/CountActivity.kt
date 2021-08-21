package io.github.soymd.daggermvvm.count

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.R
import io.github.soymd.daggermvvm.databinding.ActivityCountBinding

@AndroidEntryPoint(AppCompatActivity::class)
class CountActivity : Hilt_CountActivity() {
    lateinit var binding: ActivityCountBinding

    private val viewModel: CountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_count)
        binding.apply {
            lifecycleOwner = this@CountActivity
            viewModel = this@CountActivity.viewModel
        }

        viewModel.closeEvent.observe(this, {
            this.finish()
        })

        // MVVMを使わない場合の例
        binding.minusButton.setOnClickListener {
            val repository = CountRepository(context = applicationContext)
            val newCount = repository.getCount() - 1
            repository.saveCount(newCount)
            binding.countText.text = newCount.toString()
        }
    }
}