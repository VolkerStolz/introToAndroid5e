package com.introtoandroid.simplescrolling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class NoScrollActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("No Scrolling")
        setContentView(R.layout.noscroll)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}