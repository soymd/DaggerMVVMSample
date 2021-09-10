package io.github.soymd.daggermvvm.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import com.google.accompanist.appcompattheme.AppCompatTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.R
import io.github.soymd.daggermvvm.databinding.FragmentJetpackComposeBinding

@AndroidEntryPoint(Fragment::class)
class JetpackComposeFragment : Hilt_JetpackComposeFragment() {
    lateinit var binding: FragmentJetpackComposeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJetpackComposeBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@JetpackComposeFragment
        }

        val greeting: ComposeView = binding.greeting
        greeting.setContent {
            AppCompatTheme {
                Greeting()
            }
        }

        return binding.root
    }
}

@Composable
private fun Greeting() {
    Text(
        text = stringResource(R.string.greeting),
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
//            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}