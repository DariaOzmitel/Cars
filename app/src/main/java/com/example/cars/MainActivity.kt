package com.example.cars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
            val intent = CarItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        }
        setupSwipeListener(binding.rvCarList)
    }

    private fun setupSwipeListener(rvCarList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = carListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCarItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvCarList)
    }

}