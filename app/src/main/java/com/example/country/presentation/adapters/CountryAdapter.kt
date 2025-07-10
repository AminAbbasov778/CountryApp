package com.example.country.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.country.databinding.CountryItemBinding
import com.example.country.presentation.models.CountryUi

class CountryAdapter(
    private val onClick: (CountryUi) -> Unit
) : ListAdapter<CountryUi, CountryAdapter.CountryViewHolder>(DiffCallback()) {

    inner class CountryViewHolder(val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
          holder.binding.country = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(getItem(position))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CountryUi>() {
        override fun areItemsTheSame(oldItem: CountryUi, newItem: CountryUi): Boolean {
            return oldItem.name == newItem.name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CountryUi, newItem: CountryUi): Boolean {
            return oldItem == newItem
        }
    }
}



