package com.introtoandroid.simplepermissions

import android.content.pm.PackageManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.*

class GridListMenuActivity : AppCompatActivity(),
    OnRequestPermissionsResultCallback {
    private val actions: java.util.SortedMap<kotlin.String?, kotlin.Any?> =
        java.util.TreeMap<kotlin.String?, kotlin.Any?>()

    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        val av = findViewById(R.id.list) as android.widget.ListView

        prepareMenu()

        val keys: kotlin.Array<kotlin.String?> = actions.keys.toTypedArray<kotlin.String?>()

        av.setAdapter(
            ArrayAdapter<Any?>(
                this,
                android.R.layout.simple_list_item_1, keys
            )
        )

        av.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: kotlin.Int,
                id: kotlin.Long
            ) {
                android.util.Log.d(
                    GridListMenuActivity.Companion.DEBUG_TAG,
                    "pos: " + position + " , id: " + id
                )
                handleClick((parent as android.widget.ListView?)!!, view, position, id)
            }
        })
    }

    fun addMenuItem(label: kotlin.String, cls: java.lang.Class<*>?) {
        actions.put(label, android.content.Intent(this, cls))
    }

    fun prepareMenu() {
        addMenuItem("1. Grid w/Adapter", SimpleGridFragmentActivity::class.java)
        addMenuItem("2. List w/Adapter", SimpleListFragmentActivity::class.java)
    }

    protected fun handleClick(
        l: android.widget.ListView,
        v: android.view.View?,
        position: kotlin.Int,
        id: kotlin.Long
    ) {
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                    !== PackageManager.PERMISSION_GRANTED)
            || (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CONTACTS)
                    !== PackageManager.PERMISSION_GRANTED)
        ) {
            android.util.Log.i(
                GridListMenuActivity.Companion.DEBUG_TAG,
                "Contact permissions not granted. Requesting permissions."
            )
            ActivityCompat
                .requestPermissions(
                    this@GridListMenuActivity, GridListMenuActivity.Companion.PERMISSIONS_CONTACT,
                    GridListMenuActivity.Companion.REQUEST_CONTACTS
                )
        } else {
            android.util.Log.i(
                GridListMenuActivity.Companion.DEBUG_TAG,
                "Contact permissions granted. Displaying contacts."
            )
            val key = l.getItemAtPosition(position) as kotlin.String?
            startActivity(actions.get(key) as android.content.Intent?)
        }
    }

    public override fun onRequestPermissionsResult(
        requestCode: kotlin.Int, @NonNull permissions: kotlin.Array<kotlin.String?>,
        @NonNull grantResults: kotlin.IntArray
    ) {
        if (requestCode == GridListMenuActivity.Companion.REQUEST_CONTACTS) {
            android.util.Log.d(
                GridListMenuActivity.Companion.DEBUG_TAG,
                "Received response for contact permissions request."
            )

            // All Contact permissions must be checked
            if (GridListMenuActivity.Companion.verifyPermissions(grantResults)) {
                // All required permissions granted, proceed as usual
                android.util.Log.d(
                    GridListMenuActivity.Companion.DEBUG_TAG,
                    "Contacts permissions were granted."
                )
                Toast.makeText(
                    this, "Contacts Permission Granted",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                android.util.Log.d(
                    GridListMenuActivity.Companion.DEBUG_TAG,
                    "Contacts permissions were denied."
                )
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
        private const val DEBUG_TAG = "GridListMenuActivity"
        private const val REQUEST_CONTACTS = 1
        private val PERMISSIONS_CONTACT = kotlin.arrayOf<kotlin.String?>(
            android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.WRITE_CONTACTS
        )

        fun verifyPermissions(grantResults: kotlin.IntArray): kotlin.Boolean {
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