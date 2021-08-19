package io.github.soymd.daggermvvm.fizzbuzz

import androidx.recyclerview.widget.RecyclerView
import io.github.soymd.daggermvvm.databinding.ItemFizzBuzzBinding

class FizzBuzzViewHolder(
    val binding: ItemFizzBuzzBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String) {
        binding.fizzbuzzText.text = text
    }
}