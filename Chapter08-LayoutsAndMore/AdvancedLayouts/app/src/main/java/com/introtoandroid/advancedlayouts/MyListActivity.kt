package com.introtoandroid.advancedlayouts

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView


class MyListActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val items = arrayOf<String?>("Basic Layout", "List Layout", "Grid View")

        val adapter: ListAdapter = ArrayAdapter<String?>(this, R.layout.textview, items)

        val av = findViewById<View?>(R.id.menu_list) as ListView
        av.setAdapter(adapter)
        av.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Log.d(
                    AdvancedLayoutsActivity.Companion.DEBUG_TAG,
                    "pos: " + position + " , id: " + id
                )
                when (position) {
                    0 -> {
                        val intent = Intent(
                            getApplicationContext(),
                            BasicLayoutActivity::class.java
                        )
                        startActivity(intent)
                    }

                    1 -> {
                        val tv = view as TextView
                        tv.setText("Changed")
                    }

                    2 -> {
                        val original = parent
                            .getItemAtPosition(position) as String?
                        Log.d(
                            AdvancedLayoutsActivity.Companion.DEBUG_TAG, "original string: "
                                    + original
                        )
                        (view as TextView).setText("Updated")
                    }
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                val upIntent = NavUtils.getParentActivityIntent(this)
                if (NavUtils.shouldUpRecreateTask(this, upIntent!!)) {
                    TaskStackBuilder.create(this)
                        .addNextIntentWithParentStack(upIntent)
                        .startActivities()
                } else {
                    NavUtils.navigateUpTo(this, upIntent)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}