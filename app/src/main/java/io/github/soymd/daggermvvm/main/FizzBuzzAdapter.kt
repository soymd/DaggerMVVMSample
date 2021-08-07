package io.github.soymd.daggermvvm.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.soymd.daggermvvm.databinding.ItemFizzbuzzBinding

class FizzBuzzAdapter(
    private val context: Context,
    private val viewModel: MainViewModel,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return FizzBuzzViewHolder(
            ItemFizzbuzzBinding.inflate(
                inflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val text = viewModel.fizzbuzz(position + 1)
        (holder as FizzBuzzViewHolder).bind(text)
    }

    override fun getItemCount(): Int {
        return 50
    }
}