package io.github.soymd.daggermvvm.jetpack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.soymd.daggermvvm.R

@Composable
fun ComposeView(
    viewModel: ComposeViewModel
) {
    Column {
        Text(
            text = stringResource(R.string.greeting),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { viewModel.tapped() },
        ) {
            Text("button")
        }
    }
}