package com.introtoandroid.simplescrolling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class BothScrollActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("Both Scroll")
        setContentView(R.layout.allscroll)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}