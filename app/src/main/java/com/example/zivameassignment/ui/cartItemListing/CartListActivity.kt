package com.example.zivameassignment.ui.base.cartItemListing

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.remote.model.CartResponse
import com.example.zivameassignment.databinding.ActivityCartlistBinding
import com.example.zivameassignment.ui.adapter.CartListAdapter
import com.example.zivameassignment.ui.base.BaseActivity
import com.example.zivameassignment.ui.cartItemListing.CartListViewModel
import com.example.zivameassignment.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartListActivity : BaseActivity() {

    private lateinit var binding: ActivityCartlistBinding

    private val cartListViewModel: CartListViewModel by viewModels()
    private lateinit var cartListAdapter: CartListAdapter
    override fun observeViewModel() {
        observe(cartListViewModel.cartLiveData, ::handleCartList)
        observeSnackBarMessages(cartListViewModel.showSnackBar)
        observeToast(cartListViewModel.showToast)
    }
    private fun handleCartList(status: Resource<CartResponse>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(cartResponse = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { cartListViewModel.showToastMessage(it) }
            }
        }
    }

    private fun bindListData(cartResponse: CartResponse) {
        if (!(cartResponse.cartData.isNullOrEmpty())) {
            cartListAdapter = CartListAdapter(cartListViewModel, cartData = cartResponse.cartData)
            binding.recyclerView.adapter = cartListAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }
    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.recyclerView.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.recyclerView.toGone()
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }


    override fun initViewBinding() {
        binding = ActivityCartlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "CartItems"
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        cartListViewModel.getCartItems()
    }


}