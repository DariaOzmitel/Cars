package com.example.cars.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.CarApp
import com.example.cars.ViewModelFactory
import com.example.cars.activities.CarItemActivity
import com.example.cars.adapters.CarListAdapter
import com.example.cars.databinding.FragmentCarListBinding
import javax.inject.Inject

class CarListFragment : Fragment() {

    private val binding by lazy {
        FragmentCarListBinding.inflate(layoutInflater)
    }
    private val carListAdapter by lazy {
        CarListAdapter()
    }
    private val component by lazy {
        (requireActivity().application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CarListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[CarListViewModel::class.java]
        setupRecyclerView()
        binding.buttonAddCarItem.setOnClickListener {
            val intent = CarItemActivity.newIntentAddItem(requireContext())
            startActivity(intent)
        }
        viewModel.carList.observe(viewLifecycleOwner) {
            carListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvCarList.adapter = carListAdapter
        carListAdapter.onCarItemClickListener = {
            val intent = CarItemActivity.newIntentEditItem(requireContext(), it.id)
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