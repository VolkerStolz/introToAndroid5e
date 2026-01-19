package com.introtoandroid.simplelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class FrameLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_layout)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setTitle("Frame Layout")
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}
