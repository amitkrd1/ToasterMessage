package com.example.toastermessage

import android.content.Context
import android.widget.Toast

class ToasterMessage {
    companion object {
        fun toastMessage(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}