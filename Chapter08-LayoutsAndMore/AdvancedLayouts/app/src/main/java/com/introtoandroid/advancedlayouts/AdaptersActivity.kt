package com.introtoandroid.advancedlayouts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
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
import android.widget.Toast

class AdaptersActivity : AppCompatActivity(), OnRequestPermissionsResultCallback {
    private val actions: MutableMap<String?, Any?> = HashMap<String?, Any?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actions.put(menu[0], Intent(this, ContactAdapterActivity::class.java))
        actions.put(menu[1], Intent(this, ListAdapterSampleActivity::class.java))

        setContentView(R.layout.menu_layout)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val av = findViewById<View?>(R.id.menu_list) as ListView
        val adapter: ListAdapter =
            ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, menu)

        av.setAdapter(adapter)
        av.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                if (ActivityCompat.checkSelfPermission(
                        this@AdaptersActivity,
                        Manifest.permission.READ_CONTACTS
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    Log.i(DEBUG_TAG, "Contact permissions not granted. Requesting permissions.")
                    ActivityCompat
                        .requestPermissions(
                            this@AdaptersActivity, PERMISSIONS_CONTACT,
                            REQUEST_CONTACTS
                        )
                } else {
                    Log.i(
                        DEBUG_TAG,
                        "Contact permissions granted. Displaying contacts."
                    )
                    val key = parent.getItemAtPosition(position) as String?
                    startActivity(actions.get(key) as Intent?)
                }
                //                String text = (String) parent.getItemAtPosition(position);
//                startActivity((Intent) actions.get(text));
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

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CONTACTS) {
            Log.d(DEBUG_TAG, "Received response for contact permissions request.")

            // All Contact permissions must be checked
            if (verifyPermissions(grantResults)) {
                // All required permissions granted, proceed as usual
                Log.d(DEBUG_TAG, "Contacts permissions were granted.")
                Toast.makeText(
                    this, "Contacts Permission Granted",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                Log.d(DEBUG_TAG, "Contacts permissions were denied.")
                Toast.makeText(
                    this, "Contacts Permission Denied",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    companion object {
        private val menu = arrayOf<String?>("ContactAdapter", "ListAdapterSample")
        private const val DEBUG_TAG = "AdaptersActivity"
        private const val REQUEST_CONTACTS = 0
        private val PERMISSIONS_CONTACT = arrayOf<String?>(Manifest.permission.READ_CONTACTS)

        fun verifyPermissions(grantResults: IntArray): Boolean {
            // One result must be present
            if (grantResults.size < 1) {
                return false
            }

            // Verify each required permission is granted
            for (result in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }
    }
}