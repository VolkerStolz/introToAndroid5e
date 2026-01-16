package com.introtoandroid.viewsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.Toast


class ContainersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.containers)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val switch_button = findViewById<View?>(R.id.flip_button) as Button
        val switcher = findViewById<View?>(R.id.img_switch) as ImageSwitcher

        switch_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@ContainersActivity, "Switching", Toast.LENGTH_SHORT).show()
                switcher.showNext()
            }
        })
    }
}