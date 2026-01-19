package com.introtoandroid.advancedlayouts

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View


class StyleSamplesActivity : AppCompatActivity() {
    /* (non-Javadoc)
        * @see android.app.Activity#onCreate(android.os.Bundle)
        */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.ThemeGlow)
        setTheme(R.style.right)
        setContentView(R.layout.style_samples)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}