package io.github.soymd.daggermvvm

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint(AppCompatActivity::class)
class TestActivity : Hilt_TestActivity() {
}