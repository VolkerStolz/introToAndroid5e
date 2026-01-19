package com.introtoandroid.advancedlayouts

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View


class BasicLayoutActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_layout)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}