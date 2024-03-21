package com.example.cars.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cars.databinding.ItemCarBinding
import com.example.cars.domain.models.CarItem

class CarListAdapter: ListAdapter<CarItem, CarItemViewHolder>(CarItemDiffCallback()) {

    var onCarItemClickListener: ((CarItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder {
        val binding = ItemCarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarItemViewHolder, position: Int) {
        val carItem = getItem(position)
        with(holder.binding) {
            with(carItem) {
                tvId.text = id.toString()
                tvManufacturer.text = manufacturer
                tvCarModel.text = carModel
                root.setOnClickListener {
                    onCarItemClickListener?.invoke(carItem)
                }
            }
        }
    }

}