package com.introtoandroid.simplelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_layout)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setTitle("Linear Layout")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}
