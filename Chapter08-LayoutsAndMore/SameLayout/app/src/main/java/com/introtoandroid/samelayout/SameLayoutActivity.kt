package com.introtoandroid.samelayout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class SameLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_same_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_same_layout, menu)
        menu.findItem(R.id.program_menu_item)
            .setIntent(Intent(this, ProgrammaticLayoutActivity::class.java))
        menu.findItem(R.id.resource_menu_item)
            .setIntent(Intent(this, ResourceLayoutActivity::class.java))
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(item.getIntent())
        super.onOptionsItemSelected(item)
        return true
    }
}
