package com.introtoandroid.advancedlayouts

import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.CursorLoader
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView


class ContactAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestedColumns = arrayOf<String?>(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
        val loader = CursorLoader(
            this,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            requestedColumns, null, null, null
        )
        val contacts = loader.loadInBackground()

        setContentView(R.layout.contact)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val adapter: ListAdapter = SimpleCursorAdapter(
            this,
            R.layout.contact_item_simple,
            contacts,
            arrayOf<String>(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            ),
            intArrayOf(
                R.id.contact_item_simple_text
            ), 0
        )

        val av = findViewById<View?>(R.id.contact_list_view) as ListView
        av.setAdapter(adapter)

        av.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                val phone = parent.getItemAtPosition(position) as Cursor

                val tv = (view as TextView)
                val name = phone.getString(
                    phone.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                )
                val num = phone.getString(
                    phone.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )

                val displayed = tv.getText().toString()
                if (displayed.compareTo(name) == 0) {
                    tv.setText(num)
                } else {
                    tv.setText(name)
                }
                Log.d(
                    AdvancedLayoutsActivity.Companion.DEBUG_TAG, "Cursor pos: " +
                            phone.getPosition() + "== list pos: " + position
                )
                Log.d(
                    AdvancedLayoutsActivity.Companion.DEBUG_TAG, "Cursor id: " +
                            phone.getString(
                                phone.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone._ID
                                )
                            ) +
                            "== list id: " + id
                )
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