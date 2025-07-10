package com.example.country.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.country.databinding.FragmentSelectCountryBinding
import com.example.country.presentation.adapters.CountryAdapter
import com.example.country.presentation.states.UiState
import com.example.country.presentation.utils.VisibilityUtils.setGone
import com.example.country.presentation.utils.VisibilityUtils.show
import com.example.country.presentation.viewmodels.SelectCountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectCountryFragment : Fragment() {
    lateinit var binding: FragmentSelectCountryBinding
    val viewModel: SelectCountryViewModel by viewModels()
    lateinit var countryAdapter: CountryAdapter
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSelectCountryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchInput()
       setupAdapter()
        observe()
        onClick()

    }

    private fun onClick(){
        binding.ivRemove.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun observe(){
        viewModel.countries.observe(viewLifecycleOwner) {

            when (it) {

                is UiState.Loading -> {
                    binding.loading.show()
                }

                is UiState.Error -> {
                    binding.loading.setGone()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
                    binding.loading.setGone()
                    countryAdapter.submitList(it.data)
                }
            }

        }
    }

    private fun setupAdapter(){
        countryAdapter = CountryAdapter {
            viewModel.selectCountry(it)
            findNavController().popBackStack()
        }
        binding.rvCountries.adapter = countryAdapter
    }

    private fun setupSearchInput() {
        binding.etSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                searchJob?.cancel()
                val query = s.toString()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    delay(300)
                    viewModel.searchCountry(query)
                }
            }
        })
    }
}