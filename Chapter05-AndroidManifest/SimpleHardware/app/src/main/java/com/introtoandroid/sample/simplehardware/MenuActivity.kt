package com.introtoandroid.sample.simplehardware

import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

abstract class MenuActivity : AppCompatActivity(),
    ActivityCompat.OnRequestPermissionsResultCallback {
    private val actions: java.util.SortedMap<kotlin.String?, kotlin.Any?> =
        java.util.TreeMap<kotlin.String?, kotlin.Any?>()

    protected override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        val av = findViewById(R.id.list) as android.widget.ListView

        prepareMenu()

        val keys = actions.keys.toTypedArray<kotlin.String?>()

        av.setAdapter(
            ArrayAdapter<Any?>(
                this,
                android.R.layout.simple_list_item_1, keys
            )
        )

        av.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: kotlin.Int,
                id: kotlin.Long
            ) {
                android.util.Log.d(
                    MenuActivity.Companion.DEBUG_TAG,
                    "pos: " + position + " , id: " + id
                )
                val key = parent.getItemAtPosition(position) as kotlin.String?
                startActivity(actions.get(key) as android.content.Intent?)
            }
        })
    }

    fun addMenuItem(label: kotlin.String?, cls: java.lang.Class<*>?) {
        actions.put(label, android.content.Intent(this, cls))
    }

    abstract fun prepareMenu()

    public override fun onCreateOptionsMenu(menu: android.view.Menu?): kotlin.Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu)
        return true
    }

    public override fun onOptionsItemSelected(item: android.view.MenuItem): kotlin.Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val DEBUG_TAG = "MenuActivity"
    }
}
