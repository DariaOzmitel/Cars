package com.example.cars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cars.adapters.CarListAdapter
import com.example.cars.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val carListAdapter by lazy {
        CarListAdapter()
    }
    private val component by lazy {
        (application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setupRecyclerView()
        binding.buttonAddCarItem.setOnClickListener {
            val intent = CarItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
        viewModel.carList.observe(this) {
            carListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvCarList.adapter = carListAdapter
        carListAdapter.onCarItemClickListener = {
            TODO()
        }
    }

}