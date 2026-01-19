package com.introtoandroid.samelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class ResourceLayoutActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resource_based_layout)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}