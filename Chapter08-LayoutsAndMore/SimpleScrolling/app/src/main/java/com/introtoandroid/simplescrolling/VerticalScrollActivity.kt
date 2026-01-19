package com.introtoandroid.simplescrolling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class VerticalScrollActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("Vertical Scroll")
        setContentView(R.layout.verticalscroll)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}