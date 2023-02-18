package io.github.soymd.daggermvvm.jetpack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ComposeView(
    viewModel: ComposeViewModel
) {
    val text: String by viewModel.textFlow.collectAsState()

    Column {
        Text(
            text = text,
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