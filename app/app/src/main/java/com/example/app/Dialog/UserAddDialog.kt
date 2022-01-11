package com.example.madcamp_1st_week

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import com.example.app.R

class UserAddDialog(var context: Context) {
    private val dialog = Dialog(context)
    private lateinit var listener : OKClickedListener

    fun start(content: String) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.confirm_modal)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            listener.onOKClicked("dsf")
            dialog.dismiss()
        }

        dialog.show()
    }

    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object: OKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)

            }
        }
    }


    interface OKClickedListener {
        fun onOKClicked(content : String)
    }
}