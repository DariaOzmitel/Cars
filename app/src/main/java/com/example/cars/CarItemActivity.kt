package com.example.cars

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cars.databinding.ActivityCarItemBinding
import com.example.cars.domain.models.CarItem
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

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CarItemViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        launchRightMode()

    }

    private fun observeCarItem() {
        viewModel.carItem.observe(this) {
            binding.etManufacturer.setText(it.manufacturer)
            binding.etCarModel.setText(it.carModel)
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
                binding.etCarModel.text.toString()
            )
            finish()
        }
    }

    private fun launchEditMode() {
        viewModel.getCarItem(defineCarItemId())
        binding.buttonSave.setOnClickListener {
            viewModel.editCarItem(
                binding.etManufacturer.text.toString(),
                binding.etCarModel.text.toString()
            )
            finish()
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
        return intent.getIntExtra(EXTRA_CAR_ITEM_ID, CarItem.UNDEFINED_ID)
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