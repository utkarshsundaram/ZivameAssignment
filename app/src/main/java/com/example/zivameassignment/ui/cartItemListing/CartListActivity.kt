package com.example.zivameassignment.ui.base.cartItemListing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zivameassignment.R
import com.example.zivameassignment.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    override fun initViewBinding() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}