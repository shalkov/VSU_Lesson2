package ru.shalkoff.vsu_lesson2_2024.provider

import android.content.ContentResolver
import android.provider.ContactsContract
import android.util.Log

class ContactsContentResolver(
    private val contentResolver: ContentResolver
) {

    fun fetchContacts() {
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val contactName = cursor.getString(nameIndex)
                if (!contactName.isNullOrEmpty()) {
                    Log.d("LOG_CONTACT", contactName)
                }
            }
        }
    }
}