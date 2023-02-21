package io.github.soymd.daggermvvm.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.appcompattheme.AppCompatTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.databinding.FragmentJetpackComposeBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint(Fragment::class)
class JetpackComposeFragment : Hilt_JetpackComposeFragment() {
    lateinit var binding: FragmentJetpackComposeBinding
    private val viewModel: ComposeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentJetpackComposeBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@JetpackComposeFragment
        }

        lifecycleScope.launch {
            viewModel.closeEvent.collect {
                parentFragmentManager.popBackStack()
            }
        }

        val greeting: ComposeView = binding.greeting
        greeting.setContent {
            AppCompatTheme {
                ComposeView(viewModel)
            }
        }

        return binding.root
    }
}