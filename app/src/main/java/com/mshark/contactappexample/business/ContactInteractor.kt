package com.mshark.contactappexample.business

import com.mshark.contactappexample.data.ContactsRepository
import com.mshark.contactappexample.models.ContactModel
import io.reactivex.Single

/**
 * @author msharkina
 * @since 4/12/19.
 */
class ContactInteractor(private val repository: ContactsRepository) {
    fun getContacts(): Single<List<ContactModel>> {

        //Place business logic here
        return repository.getContacts()
    }

    fun getSortedContacts(data: Cursor, order: SortingOrder): Single<List<ContactModel>> {
        return repository.getContacts(data: Cursor)
            .map {
                // Do sorting
            }
    }
}