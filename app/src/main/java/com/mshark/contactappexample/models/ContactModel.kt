package com.mshark.contactappexample.models

/**
 * @author msharkina
 * @since 4/12/19.
 */
data class ContactModel(
    val id: Int,
    val displayName: String,
    val firstName: String,
    val lastName: String,
    val emailAddress: String
)
