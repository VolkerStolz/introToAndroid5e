package com.introtoandroid.simplescrolling

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class SimpleScrollingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_scrolling)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_simple_scrolling, menu)
        menu.findItem(R.id.noscroll_menu_item)
            .setIntent(Intent(this, NoScrollActivity::class.java))
        menu.findItem(R.id.verticalscroll_menu_item)
            .setIntent(Intent(this, VerticalScrollActivity::class.java))
        menu.findItem(R.id.horizontalscroll_menu_item)
            .setIntent(Intent(this, HorizontalScrollActivity::class.java))
        menu.findItem(R.id.bothscroll_menu_item)
            .setIntent(Intent(this, BothScrollActivity::class.java))
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(item.getIntent())
        super.onOptionsItemSelected(item)
        return true
    }
}
