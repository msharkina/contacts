package com.mshark.contactappexample.data

import android.content.ContentResolver
import android.database.Cursor
import com.mshark.contactappexample.exceptions.ContactException
import com.mshark.contactappexample.models.ContactModel
import io.reactivex.Single

/**
 * @author msharkina
 * @since 4/12/19.
 */
class ContactsRepository constructor(private val contentResolver: ContentResolver) {

    fun getContacts(): Single<List<ContactModel>> {

        return Single.fromCallable {
            val contactList = mutableListOf<ContactModel>()
            val cursor = contentResolver.query(
                ContactsQuery.CONTENT_URI,
                ContactsQuery.PROJECTION,
                ContactsQuery.SELECTION,
                null,
                ContactsQuery.SORT_ORDER
            )

            cursor?.let {
                while (it.moveToNext()) {
                    map(it)?.let {
                        contactList.add(it)
                    }
                }
                cursor.close()
            }
            contactList
        }
    }

    private fun map(data: Cursor): ContactModel? {
            val id = data.getInt(ContactsQuery.ID)
            val displayName = data.getString(ContactsQuery.DISPLAY_NAME)
            val nameParts = displayName.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            var firstName = ""
            var lastName = ""
            if (nameParts.isNotEmpty()) {
                firstName = nameParts[0]
            }
            if (nameParts.size > 1) {
                lastName = nameParts[1]
            }
            return ContactModel(
                id,
                displayName,
                firstName,
                lastName,
                data.getString(ContactsQuery.EMAIL)
            )
    }
}