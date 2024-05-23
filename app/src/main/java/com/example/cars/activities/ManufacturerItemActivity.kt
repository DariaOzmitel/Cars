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
import com.example.cars.databinding.ActivityManufacturerItemBinding
import com.example.cars.domain.models.Item
import com.example.cars.state.CloseManufacturerItemScreen
import com.example.cars.state.ErrorInputManufacturerName
import com.example.cars.state.ManufacturerItemInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManufacturerItemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityManufacturerItemBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ManufacturerItemViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        launchRightMode()
        addAfterTextChanged()
    }

    private fun addAfterTextChanged() {
        binding.etManufacturer.doAfterTextChanged {
            viewModel.resetErrorInputManufacturerName()
        }
    }

    private fun observeManufacturerItem() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is ManufacturerItemInfo -> {
                        binding.etManufacturer.setText(it.value.manufacturerName)
                    }

                    is ErrorInputManufacturerName -> {
                        if (it.value) binding.tilManufacturer.error =
                            getString(R.string.param_manufacturer_is_absent)
                        else binding.tilManufacturer.error = null
                    }

                    is CloseManufacturerItemScreen -> {
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
                observeManufacturerItem()
            }

            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchAddMode() {
        binding.buttonSave.setOnClickListener {
            viewModel.addManufacturerItem(
                binding.etManufacturer.text.toString()
            )
        }
        observeManufacturerItem()
    }

    private fun launchEditMode() {
        viewModel.getManufacturerItem(defineManufacturerItemId())
        binding.buttonSave.setOnClickListener {
            viewModel.editManufacturerItem(
                binding.etManufacturer.text.toString()
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

    private fun defineManufacturerItemId(): Int {
        if (!intent.hasExtra(EXTRA_MANUFACTURER_ITEM_ID)) {
            throw RuntimeException("Param shop item id is absent")
        }
        return intent.getIntExtra(EXTRA_MANUFACTURER_ITEM_ID, Item.UNDEFINED_ID)
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_MANUFACTURER_ITEM_ID = "extra_manufacturer_item_id"

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ManufacturerItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, manufacturerItemId: Int): Intent {
            val intent = Intent(context, ManufacturerItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_MANUFACTURER_ITEM_ID, manufacturerItemId)
            return intent
        }
    }
}