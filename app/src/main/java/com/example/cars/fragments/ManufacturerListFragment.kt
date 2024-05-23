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
import com.example.cars.activities.ManufacturerItemActivity
import com.example.cars.adapters.manufacturer.ManufacturerListAdapter
import com.example.cars.databinding.FragmentManufacturerListBinding
import javax.inject.Inject

class ManufacturerListFragment : Fragment() {

    private val binding by lazy {
        FragmentManufacturerListBinding.inflate(layoutInflater)
    }
    private val manufacturerListAdapter by lazy {
        ManufacturerListAdapter()
    }
    private val component by lazy {
        (requireActivity().application as CarApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ManufacturerListViewModel

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
        viewModel = ViewModelProvider(this, viewModelFactory)[ManufacturerListViewModel::class.java]
        setupRecyclerView()
        binding.buttonAddManufacturerItem.setOnClickListener {
            val intent = ManufacturerItemActivity.newIntentAddItem(requireContext())
            startActivity(intent)
        }
        viewModel.manufacturerList.observe(viewLifecycleOwner) {
            manufacturerListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvManufacturerList.adapter = manufacturerListAdapter
        manufacturerListAdapter.onManufacturerItemClickListener = {
            val intent = ManufacturerItemActivity.newIntentEditItem(requireContext(), it.id)
            startActivity(intent)
        }
        setupSwipeListener(binding.rvManufacturerList)
    }

    private fun setupSwipeListener(rvManufacturerList: RecyclerView) {
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
                val item = manufacturerListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteManufacturerItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvManufacturerList)
    }
}