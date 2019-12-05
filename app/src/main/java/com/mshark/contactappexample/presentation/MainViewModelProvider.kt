package com.mshark.contactappexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mshark.contactappexample.business.ContactInteractor

/**
 * @author msharkina
 * @since 4/12/19.
 */
class MainViewModelProvider(private val interactor: ContactInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                interactor
            ) as T
        }
        throw IllegalArgumentException("Unknown Class Exception")
    }
}