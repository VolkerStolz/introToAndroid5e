package com.introtoandroid.simplelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class GridLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_layout)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setTitle("Grid Layout")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}