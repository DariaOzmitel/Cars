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
import com.example.cars.activities.CarModelItemActivity
import com.example.cars.adapters.carModel.CarModelListAdapter
import com.example.cars.databinding.FragmentCarModelListBinding
import javax.inject.Inject

class CarModelListFragment : Fragment() {

    private val binding by lazy {
        FragmentCarModelListBinding.inflate(layoutInflater)
    }
    private val carModelListAdapter by lazy {
        CarModelListAdapter()
    }
    private val component by lazy {
        (requireActivity().application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CarModelListViewModel

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
        viewModel = ViewModelProvider(this, viewModelFactory)[CarModelListViewModel::class.java]
        setupRecyclerView()
        binding.buttonAddCarModelItem.setOnClickListener {
            val intent = CarModelItemActivity.newIntentAddItem(requireContext())
            startActivity(intent)
        }
        viewModel.carModelList.observe(viewLifecycleOwner) {
            carModelListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvCarModelList.adapter = carModelListAdapter
        carModelListAdapter.onCarModelItemClickListener = {
            val intent = CarModelItemActivity.newIntentEditItem(requireContext(), it.id)
            startActivity(intent)
        }
        setupSwipeListener(binding.rvCarModelList)
    }

    private fun setupSwipeListener(rvCarModelList: RecyclerView) {
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
                val item = carModelListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCarModelItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvCarModelList)
    }
}