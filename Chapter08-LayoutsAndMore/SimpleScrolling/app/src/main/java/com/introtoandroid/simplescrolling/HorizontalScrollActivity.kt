package com.introtoandroid.simplescrolling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class HorizontalScrollActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("Horizontal Scroll")
        setContentView(R.layout.horizontalscroll)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}