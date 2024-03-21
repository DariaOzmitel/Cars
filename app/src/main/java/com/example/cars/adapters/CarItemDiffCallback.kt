package com.example.cars.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cars.domain.models.CarItem

class CarItemDiffCallback: DiffUtil.ItemCallback<CarItem>() {
    override fun areItemsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
        return oldItem == newItem
    }
}