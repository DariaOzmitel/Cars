package com.example.cars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cars.adapters.CarListAdapter
import com.example.cars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val carListAdapter by lazy {
        CarListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvCarList.adapter = carListAdapter
        carListAdapter.onCarItemClickListener = {
            TODO()
        }
    }
}