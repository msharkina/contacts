package com.mshark.contactappexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mshark.contactappexample.business.ContactInteractor
import com.mshark.contactappexample.models.ContactModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author msharkina
 * @since 4/12/19.
 */
class MainViewModel(private val contactInteractor: ContactInteractor) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _contactList = MutableLiveData<List<ContactModel>>()
    val contactList: LiveData<List<ContactModel>>
        get() = _contactList


    fun getContacts() {
        compositeDisposable += contactInteractor.getContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _contactList.value = it
                },
                onError = {
                    Timber.e(it, "Unable to get contacts")
                }
            )
    }
    override fun onCleared() = compositeDisposable.dispose()
}