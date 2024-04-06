package com.example.cars

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cars.databinding.ActivityCarItemBinding
import javax.inject.Inject

class CarItemActivity : AppCompatActivity() {

    private var screenMode = ""
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
        parseIntent()
        launchRightMode()
    }

    private fun launchRightMode() {
        when (screenMode) {
            //MODE_EDIT -> launchEditMode()
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

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT)
            throw RuntimeException("Unknown screen mode $mode")
        screenMode = mode
        if (mode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_CAR_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            //carItemId = intent.getIntExtra(EXTRA_CAR_ITEM_ID, CarItem.UNDEFINED_ID)
        }
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

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, CarItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_CAR_ITEM_ID, shopItemId)
            return intent
        }
    }
}