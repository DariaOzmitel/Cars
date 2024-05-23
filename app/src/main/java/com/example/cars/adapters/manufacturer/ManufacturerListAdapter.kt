package com.example.cars.adapters.manufacturer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cars.databinding.ItemManufacturerBinding
import com.example.cars.domain.models.ManufacturerItem

class ManufacturerListAdapter :
    ListAdapter<ManufacturerItem, ManufacturerItemViewHolder>(ManufacturerItemDiffCallback()) {

    var onManufacturerItemClickListener: ((ManufacturerItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerItemViewHolder {
        val binding = ItemManufacturerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ManufacturerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ManufacturerItemViewHolder, position: Int) {
        val manufacturerItem = getItem(position)
        with(holder.binding) {
            with(manufacturerItem) {
                tvId.text = id.toString()
                tvManufacturer.text = manufacturerName
                root.setOnClickListener {
                    onManufacturerItemClickListener?.invoke(manufacturerItem)
                }
            }
        }
    }

}