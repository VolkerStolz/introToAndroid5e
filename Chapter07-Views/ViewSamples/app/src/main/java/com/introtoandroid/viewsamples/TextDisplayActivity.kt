package com.introtoandroid.viewsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import android.widget.TextView


class TextDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_display)
        val text = findViewById<View?>(R.id.TextView02) as TextView?
        registerForContextMenu(text)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if ((v as TextView).getLinksClickable()) {
            menu.add("Disable Clickability")
        } else {
            menu.add("Enable Clickability")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        super.onContextItemSelected(item)

        val text = findViewById<View?>(R.id.TextView02) as TextView
        if (text.getLinksClickable()) {
            //text.setLinksClickable(false);
            text.setMovementMethod(null)
        } else {
            text.setLinksClickable(true)
            text.setMovementMethod(LinkMovementMethod())
        }
        return true
    }
}