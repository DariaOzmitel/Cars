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
import com.example.cars.domain.models.Item
import com.example.cars.state.CarModelItemInfo
import com.example.cars.state.CloseCarModelItemScreen
import com.example.cars.state.ErrorInputCarModelName
import com.example.cars.state.ErrorInputManufacturerItem
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarModelItemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCarItemBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CarModelItemViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        launchRightMode()
        addAfterTextChanged()
        observeManufacturerList()
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
        }
    }

    private fun addAfterTextChanged() {
        binding.etManufacturer.doAfterTextChanged {
            viewModel.resetErrorInputManufacturerItem()
        }
        binding.etCarModel.doAfterTextChanged {
            viewModel.resetErrorInputCarModelName()
        }
    }

    private fun observeCarItem() {

        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CarModelItemInfo -> {
                        binding.etManufacturer.setText(it.value.manufacturerName)
                        binding.etCarModel.setText(it.value.carModelName)
                    }

                    is ErrorInputManufacturerItem -> {
                        if (it.value) binding.tilManufacturer.error =
                            getString(R.string.param_manufacturer_is_absent)
                        else binding.tilManufacturer.error = null
                    }

                    is ErrorInputCarModelName -> {
                        if (it.value) binding.tilCarModel.error =
                            getString(R.string.param_car_model_is_absent)
                        else binding.tilCarModel.error = null
                    }

                    is CloseCarModelItemScreen -> {
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
            viewModel.addCarModelItem(
                binding.etManufacturer.text.toString(),
                binding.etCarModel.text.toString()
            )
        }
        observeCarItem()
    }

    private fun launchEditMode() {
        viewModel.getCarModelItem(defineCarItemId())
        binding.buttonSave.setOnClickListener {
            viewModel.editCarModelItem(
                binding.etManufacturer.text.toString(),
                binding.etCarModel.text.toString()
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
            val intent = Intent(context, CarModelItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, carItemId: Int): Intent {
            val intent = Intent(context, CarModelItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_CAR_ITEM_ID, carItemId)
            return intent
        }
    }
}