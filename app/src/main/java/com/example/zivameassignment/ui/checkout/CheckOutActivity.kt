package com.example.zivameassignment.ui.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zivameassignment.R
import com.example.zivameassignment.databinding.ActivityCartAddedBinding
import com.example.zivameassignment.databinding.ActivityCheckOutBinding
import com.example.zivameassignment.ui.base.BaseActivity
import com.example.zivameassignment.ui.base.cartItemListing.CartListActivity
import com.example.zivameassignment.ui.cartadded.CartAddedActivity
import com.example.zivameassignment.ui.cartadded.CartAddedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutActivity : BaseActivity() {

    private lateinit var binding: ActivityCheckOutBinding

    private val cartCheckOutViewModel: CheckoutViewModel by viewModels()
    override fun observeViewModel() {
     //no to do
    }

    override fun initViewBinding() {
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        cartCheckOutViewModel.clearTheData()
        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, CartListActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
    }
}