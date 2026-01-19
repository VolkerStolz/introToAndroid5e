package com.introtoandroid.simplelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class RelativeLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relative_layout)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setTitle("Relative Layout")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}
