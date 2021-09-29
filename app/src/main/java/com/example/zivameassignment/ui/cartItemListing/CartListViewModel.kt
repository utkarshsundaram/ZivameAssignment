package com.example.zivameassignment.ui.cartItemListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.local.database.dao.CartDao
import com.example.zivameassignment.data.local.database.model.CartAdded
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import com.example.zivameassignment.data.remote.repository.DataRepositorySource
import com.example.zivameassignment.ui.base.BaseViewModel
import com.example.zivameassignment.utils.SingleEvent
import com.example.zivameassignment.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
@HiltViewModel
class CartListViewModel@Inject
constructor(private val dataRepositoryRepository: DataRepositorySource
,private val cartDao: CartDao) : BaseViewModel() {

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */

    val cartLiveDataPrivate = MutableLiveData<Resource<CartResponse>>()
    val cartLiveData: LiveData<Resource<CartResponse>> get() = cartLiveDataPrivate
    lateinit var  cartFlowCollector:FlowCollector<Resource<CartResponse>>


    /**
     * Error handling as UI
     */

    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate


    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate



    @OptIn(InternalCoroutinesApi::class)
    fun getCartItems() {
        viewModelScope.launch {
            cartLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestCartItems().collect {
                    cartLiveDataPrivate.value = it
                }
            }
        }
    }

    fun saveCartItems(cartData: CartData){
        viewModelScope.launch{
            saveData(cartData)
        }

    }

    private  suspend fun saveData(cartData: CartData) {
        withContext(Dispatchers.IO) {
            for(i in cartDao.getAllCartData()){
                if(cartData.name.equals(i.name)){
                    return@withContext
                }
            }
            val rnds = (0..100000000).random()
            cartDao.insertCartItems(
                cartItems = CartAdded(id=rnds,
                    name = cartData.name.toString(), image = cartData.image_url,
                    price = cartData.price, rating = cartData.rating
                )
            )
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}



