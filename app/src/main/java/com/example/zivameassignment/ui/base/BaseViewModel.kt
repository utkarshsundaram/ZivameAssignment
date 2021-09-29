package com.example.zivameassignment.ui.base

import androidx.lifecycle.ViewModel
import com.example.zivameassignment.errors.ErrorManager
import javax.inject.Inject

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */
abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}