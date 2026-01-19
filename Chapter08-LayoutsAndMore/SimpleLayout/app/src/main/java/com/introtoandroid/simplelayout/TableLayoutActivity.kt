package com.introtoandroid.simplelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class TableLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_layout)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setTitle("Table Layout")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}