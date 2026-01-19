package com.introtoandroid.simplelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MultipleLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_layout)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setTitle("Multiple Layout")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}
