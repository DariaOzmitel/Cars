package com.example.cars.adapters.carModel

import androidx.recyclerview.widget.DiffUtil
import com.example.cars.domain.models.CarModelItem

class CarModelItemDiffCallback : DiffUtil.ItemCallback<CarModelItem>() {
    override fun areItemsTheSame(oldItem: CarModelItem, newItem: CarModelItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CarModelItem, newItem: CarModelItem): Boolean {
        return oldItem == newItem
    }
}