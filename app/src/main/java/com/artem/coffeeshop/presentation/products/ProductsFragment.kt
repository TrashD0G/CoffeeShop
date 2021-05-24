package com.artem.coffeeshop.presentation.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artem.coffeeshop.CoffeeShopApplication
import com.artem.coffeeshop.databinding.FragmentProductsBinding
import com.artem.coffeeshop.domain.Products
import com.artem.coffeeshop.presentation.products.adapters.ProductsAdapter
import com.artem.coffeeshop.presentation.products.viewModelProductsFragment.ViewModelProductsFragment
import com.artem.coffeeshop.presentation.products.viewModelProductsFragment.ViewModelProductsFragmentFactory

import javax.inject.Inject

class ProductsFragment : Fragment() {

    //Binding
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!


    private lateinit var recyclerProducts: RecyclerView

    private lateinit var viewModel: ViewModelProductsFragment

    @Inject
    lateinit var viewModelProductsFragmentFactory: ViewModelProductsFragmentFactory


    private lateinit var productsList: ArrayList<Products>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Dagger inject
        (requireActivity().applicationContext as CoffeeShopApplication)
            .applicationAppComponent.injectProductsFragment(this)

        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecycler()

        // ViewModel
        viewModel =  ViewModelProvider(requireActivity(), viewModelProductsFragmentFactory).get(ViewModelProductsFragment::class.java)
        viewModel.setAdapter(productsList, recyclerProducts)

        return view
    }



    private fun initRecycler() {
        recyclerProducts = binding.productsRecycleView
        productsList = ArrayList()
        recyclerProducts.layoutManager = LinearLayoutManager(requireContext())
        recyclerProducts.adapter = ProductsAdapter(productsList)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}