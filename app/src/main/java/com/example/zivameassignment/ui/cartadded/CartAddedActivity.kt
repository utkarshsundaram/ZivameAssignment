package com.example.zivameassignment.ui.cartadded

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivameassignment.R
import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import com.example.zivameassignment.databinding.ActivityCartAddedBinding
import com.example.zivameassignment.ui.adapter.CartAddedAdapter
import com.example.zivameassignment.ui.base.BaseActivity
import com.example.zivameassignment.ui.checkout.CheckOutActivity
import com.example.zivameassignment.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            is Resource.Success -> status.data?.let { showDataView(true)
                bindListData(cartResponse = it.products) }
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
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        cartListViewModel.getCartAddedFromDb()
        binding.checkout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, CheckOutActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

}