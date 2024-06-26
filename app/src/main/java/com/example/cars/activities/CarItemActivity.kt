package com.example.cars.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cars.CarApp
import com.example.cars.R
import com.example.cars.ViewModelFactory
import com.example.cars.databinding.ActivityCarItemBinding
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.models.Item
import com.example.cars.state.CarItemInfo
import com.example.cars.state.CloseScreen
import com.example.cars.state.ErrorInputCarModel
import com.example.cars.state.ErrorInputManufacturer
import com.example.cars.state.ErrorInputPrice
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarItemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCarItemBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var carModelList: List<CarModelItem>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CarItemViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        launchRightMode()
        addAfterTextChanged()
        observeManufacturerList()
        setItemClickListener()
    }

    private fun observeManufacturerList() {
        viewModel.manufacturerList.observe(this) { manufacturerItemList ->
            val manufacturerItems: Array<String> =
                if (!manufacturerItemList.isNullOrEmpty()) {
                    var i = 0
                    Array(
                        manufacturerItemList.size
                    ) { manufacturerItemList[i++].manufacturerName }
                } else {
                    arrayOf("")
                }

            (binding.etManufacturer as? MaterialAutoCompleteTextView)?.setSimpleItems(
                manufacturerItems
            )
            observeCarModelList()
        }
    }

    private fun observeCarModelList() {
        viewModel.carModelList.observe(this) { carModelItemList ->
            carModelList = carModelItemList
            refreshCarModelList(carModelList)
        }
    }

    private fun refreshCarModelList(list: List<CarModelItem>) {
        val carModelItems: Array<String> =
            if (list.isNotEmpty()) {
                val filteredList = list.filter {
                    it.manufacturerName == binding.etManufacturer.text.toString()
                }
                var i = 0
                Array(
                    filteredList.size
                ) { filteredList[i++].carModelName }
            } else {
                arrayOf("")
            }

        (binding.etCarModel as? MaterialAutoCompleteTextView)?.setSimpleItems(
            carModelItems
        )
    }

    private fun setItemClickListener() {
        binding.etManufacturer.setOnItemClickListener { _, _, _, _ ->
            refreshCarModelList(carModelList)
            binding.etCarModel.setText("")
        }
    }

    private fun addAfterTextChanged() {
        binding.etManufacturer.doAfterTextChanged {
            viewModel.resetErrorInputManufacturer()

        }
        binding.etCarModel.doAfterTextChanged {
            viewModel.resetErrorInputCarModel()
        }

        binding.etPrice.doAfterTextChanged {
            viewModel.resetErrorInputPrice()
        }
    }

    private fun observeCarItem() {

        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CarItemInfo -> {
                        binding.etManufacturer.setText(it.value.manufacturer)
                        binding.etCarModel.setText(it.value.carModel)
                        binding.etPrice.setText(it.value.price.toString())
                    }

                    is ErrorInputManufacturer -> {
                        if (it.value) binding.tilManufacturer.error =
                            getString(R.string.param_manufacturer_is_absent)
                        else binding.tilManufacturer.error = null
                    }

                    is ErrorInputCarModel -> {
                        if (it.value) binding.tilCarModel.error =
                            getString(R.string.param_car_model_is_absent)
                        else binding.tilCarModel.error = null
                    }

                    is ErrorInputPrice -> {
                        if (it.value) binding.tilPrice.error =
                            getString(R.string.param_price_is_absent)
                        else binding.tilPrice.error = null
                    }

                    is CloseScreen -> {
                        finish()
                    }
                }

            }
        }

    }

    private fun launchRightMode() {
        when (defineScreenMode()) {
            MODE_EDIT -> {
                launchEditMode()
                observeCarItem()
            }

            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchAddMode() {
        binding.buttonSave.setOnClickListener {
            viewModel.addCarItem(
                binding.etManufacturer.text.toString(),
                binding.etCarModel.text.toString(),
                binding.etPrice.text.toString()
            )
        }
        observeCarItem()
    }

    private fun launchEditMode() {
        viewModel.getCarItem(defineCarItemId())
        binding.buttonSave.setOnClickListener {
            viewModel.editCarItem(
                binding.etManufacturer.text.toString(),
                binding.etCarModel.text.toString(),
                binding.etPrice.text.toString()
            )
        }
    }

    private fun defineScreenMode(): String {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT)
            throw RuntimeException("Unknown screen mode $mode")
        return mode
    }

    private fun defineCarItemId(): Int {
        if (!intent.hasExtra(EXTRA_CAR_ITEM_ID)) {
            throw RuntimeException("Param shop item id is absent")
        }
        return intent.getIntExtra(EXTRA_CAR_ITEM_ID, Item.UNDEFINED_ID)
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_CAR_ITEM_ID = "extra_car_item_id"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, carItemId: Int): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_CAR_ITEM_ID, carItemId)
            return intent
        }
    }
}