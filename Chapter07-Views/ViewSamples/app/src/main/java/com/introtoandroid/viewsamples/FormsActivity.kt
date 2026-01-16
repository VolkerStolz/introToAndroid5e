package com.introtoandroid.viewsamples

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView

class FormsActivity : AppCompatActivity() {
    protected var actions: MutableMap<String?, Any?> = HashMap<String?, Any?>()

    fun prepareMenu() {
        addMenuItem("Text Input", TextInputActivity::class.java)
        addMenuItem("Buttons", ButtonsActivity::class.java)
        addMenuItem("Pickers", PickersActivity::class.java)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        prepareMenu()
        val keys = actions.keys.toTypedArray<String?>()

        val av = findViewById<View?>(R.id.menu_list) as ListView
        val adapter: ListAdapter =
            ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, keys)

        av.setAdapter(adapter)
        av.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                val key = parent.getItemAtPosition(position) as String?
                startActivity(actions.get(key) as Intent?)
            }
        })
    }

    fun addMenuItem(label: String, cls: Class<*>?) {
        actions.put(label, Intent(this, cls))
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