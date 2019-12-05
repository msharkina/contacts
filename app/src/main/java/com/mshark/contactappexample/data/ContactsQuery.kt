package com.mshark.contactappexample.data

import android.annotation.SuppressLint
import android.provider.ContactsContract

/**
 * @author msharkina
 * @since 4/12/19.
 */
class ContactsQuery {
    companion object {
        val QUERY_ID = 1
        val CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI

        @SuppressLint("InlinedApi")
        val SELECTION =
            ContactsContract.CommonDataKinds.Email.DATA + " IS NOT NULL" + " AND " + ContactsContract.Contacts.IN_VISIBLE_GROUP + "=1"

        @SuppressLint("InlinedApi")
        val SORT_ORDER = ContactsContract.Contacts.DISPLAY_NAME

        @SuppressLint("InlinedApi")
        val PROJECTION = arrayOf(

            ContactsContract.Contacts._ID,

            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,

            ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,

            ContactsContract.CommonDataKinds.Email.DATA
        )

        val ID = 0
        val DISPLAY_NAME = 1
        val PHOTO_THUMBNAIL_DATA = 2
        val EMAIL = 3
    }
}