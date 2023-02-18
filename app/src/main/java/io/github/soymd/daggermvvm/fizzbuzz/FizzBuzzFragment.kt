package io.github.soymd.daggermvvm.fizzbuzz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.soymd.daggermvvm.databinding.FragmentFizzBuzzBinding

@AndroidEntryPoint(Fragment::class)
class FizzBuzzFragment : Hilt_FizzBuzzFragment() {
    lateinit var binding: FragmentFizzBuzzBinding
    private val viewModel: FizzBuzzViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFizzBuzzBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@FizzBuzzFragment
            viewModel = this@FizzBuzzFragment.viewModel

            fizzbuzzRecyclerView.adapter = FizzBuzzAdapter(
                requireContext(),
                this@FizzBuzzFragment.viewModel
            )
        }

        viewModel.closeEvent.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}