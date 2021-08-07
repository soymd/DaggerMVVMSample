package io.github.soymd.daggermvvm.main

import androidx.recyclerview.widget.RecyclerView
import io.github.soymd.daggermvvm.databinding.ItemFizzbuzzBinding

class FizzBuzzViewHolder(
    private val binding: ItemFizzbuzzBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String) {
        binding.fizzbuzzText.text = text
    }
}