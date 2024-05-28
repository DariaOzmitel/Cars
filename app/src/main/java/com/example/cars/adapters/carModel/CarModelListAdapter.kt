package com.example.cars.adapters.carModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cars.databinding.ItemCarModelBinding
import com.example.cars.domain.models.CarModelItem

class CarModelListAdapter :
    ListAdapter<CarModelItem, CarModelItemViewHolder>(CarModelItemDiffCallback()) {

    var onCarModelItemClickListener: ((CarModelItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarModelItemViewHolder {
        val binding = ItemCarModelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarModelItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarModelItemViewHolder, position: Int) {
        val carModelItem = getItem(position)
        with(holder.binding) {
            with(carModelItem) {
                tvId.text = id.toString()
                tvManufacturer.text = manufacturerName
                tvCarModel.text = carModelName
                root.setOnClickListener {
                    onCarModelItemClickListener?.invoke(carModelItem)
                }
            }
        }
    }

}