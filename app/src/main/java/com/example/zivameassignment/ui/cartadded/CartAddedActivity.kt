package com.example.zivameassignment.ui.cartadded

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.zivameassignment.R
import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.local.database.model.CartAdded
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import com.example.zivameassignment.databinding.ActivityCartAddedBinding
import com.example.zivameassignment.databinding.ActivityCartlistBinding
import com.example.zivameassignment.ui.adapter.CartAddedAdapter
import com.example.zivameassignment.ui.adapter.CartListAdapter
import com.example.zivameassignment.ui.base.BaseActivity
import com.example.zivameassignment.ui.cartItemListing.CartListViewModel
import com.example.zivameassignment.utils.*
import com.google.android.material.snackbar.Snackbar

class CartAddedActivity : BaseActivity() {
    private lateinit var binding: ActivityCartAddedBinding

    private val cartListViewModel: CartAddedViewModel by viewModels()
    private lateinit var cartListAdapter: CartAddedAdapter
    override fun observeViewModel() {
        observe(cartListViewModel.cartLiveDataPrivate, ::handleCartList)

    }
    private fun handleCartList(status: Resource<CartResponse>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(cartResponse = it.cartData) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { cartListViewModel.showToastMessage(it) }
            }
        }
    }
    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.recyclerView.toGone()
    }

    private fun bindListData(cartResponse: List<CartData>) {
        if (!(cartResponse.isNullOrEmpty())) {
            cartListAdapter = CartAddedAdapter(cartListViewModel, cartData = cartResponse)
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
    override fun initViewBinding() {
        binding = ActivityCartAddedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_added)
    }
}