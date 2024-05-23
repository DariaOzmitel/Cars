package com.example.cars.adapters.manufacturer

import androidx.recyclerview.widget.DiffUtil
import com.example.cars.domain.models.ManufacturerItem

class ManufacturerItemDiffCallback : DiffUtil.ItemCallback<ManufacturerItem>() {
    override fun areItemsTheSame(oldItem: ManufacturerItem, newItem: ManufacturerItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ManufacturerItem, newItem: ManufacturerItem): Boolean {
        return oldItem == newItem
    }
}