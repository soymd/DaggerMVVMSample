package io.github.soymd.daggermvvm.fizzbuzz

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.soymd.daggermvvm.databinding.ItemFizzBuzzBinding

class FizzBuzzAdapter(
    private val context: Context,
    private val viewModel: FizzBuzzViewModel,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return FizzBuzzViewHolder(
            ItemFizzBuzzBinding.inflate(
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