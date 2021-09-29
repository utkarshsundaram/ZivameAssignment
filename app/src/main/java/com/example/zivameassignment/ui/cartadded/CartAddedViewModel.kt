package com.example.zivameassignment.ui.cartadded

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zivameassignment.data.local.Resource
import com.example.zivameassignment.data.local.database.dao.CartDao
import com.example.zivameassignment.data.remote.model.CartData
import com.example.zivameassignment.data.remote.model.CartResponse
import com.example.zivameassignment.ui.base.BaseViewModel
import com.example.zivameassignment.utils.SingleEvent
import com.example.zivameassignment.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Utkarsh Sundaram on 29-09-2021.
 */
@HiltViewModel
class CartAddedViewModel @Inject constructor(private val cartDao: CartDao) : BaseViewModel()
{
    val cartLiveDataPrivate = MutableLiveData<Resource<CartResponse>>()
    lateinit var  cartData : ArrayList<CartData>
    val cartLiveData: LiveData<Resource<CartResponse>> get() = cartLiveDataPrivate

    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate
    fun getCartAddedFromDb(){
        cartLiveDataPrivate.value = Resource.Loading()
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                for(i in cartDao.getAllCartData()){
                    var data= i.rating?.let { CartData(name = i.name.toString(),price = i.price,image_url = i.image,rating = it) }
                    data?.let { cartData.add(it) }
                }
                cartLiveDataPrivate.value=Resource.Success(CartResponse(cartData))
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}