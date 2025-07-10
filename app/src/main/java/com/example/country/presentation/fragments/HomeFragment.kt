package com.example.country.presentation.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.country.R
import com.example.country.databinding.FragmentHomeBinding
import com.example.country.presentation.utils.PicassoUtil.loadUrl
import com.example.country.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSelectedCountry()
        setupClickListeners()
        observe()
    }

    private fun setupClickListeners() {
        binding.cvSelectCountry.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSelectCountryFragment()
            )
        }

        binding.cvDelete.setOnClickListener {
            viewModel.clearSelection()
        }
    }

    private fun observe() {
        viewModel.selectedCountry.observe(viewLifecycleOwner) { country ->

            val green = ContextCompat.getColor(requireContext(), R.color.green)
            val white = ContextCompat.getColor(requireContext(), R.color.white)
            val grey = ContextCompat.getColor(requireContext(), R.color.grey)

            binding.apply {
                if (country != null) {
                    clSelectedCountry.visibility = View.VISIBLE
                    tvSelectCountry.visibility = View.GONE
                    tvCountryName.text = country.name?.common
                    ivFlag.loadUrl(country.flags?.png)
                    cvDelete.backgroundTintList = ColorStateList.valueOf(green)
                    tvDelete.setTextColor(white)
                } else {
                    clSelectedCountry.visibility = View.GONE
                    tvSelectCountry.visibility = View.VISIBLE
                    cvDelete.backgroundTintList = ColorStateList.valueOf(white)
                    tvDelete.setTextColor(grey)
                }
            }
        }
    }
}
