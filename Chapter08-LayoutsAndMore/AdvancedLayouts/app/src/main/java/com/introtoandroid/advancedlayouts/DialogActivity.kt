package com.introtoandroid.advancedlayouts

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button


class DialogActivity : AppCompatActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val b = findViewById<View?>(R.id.do_dialog_btn) as Button
        b.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AlertDialog.Builder(this@DialogActivity)
                    .setMessage("Press agree to continue...")
                    .setPositiveButton("Agree", null)
                    .show()
            }
        })
    }
}