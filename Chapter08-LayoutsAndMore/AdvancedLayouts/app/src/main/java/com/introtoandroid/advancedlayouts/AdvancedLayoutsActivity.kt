package com.introtoandroid.advancedlayouts

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import java.util.SortedMap
import java.util.TreeMap


class AdvancedLayoutsActivity : AppCompatActivity() {
    private val actions: SortedMap<String?, Any?> = TreeMap<String?, Any?>()

    fun prepareMenu() {
        addMenuItem("1. Basic Layout", BasicLayoutActivity::class.java)
        addMenuItem("2. List Layout", MyListActivity::class.java)
        addMenuItem("3. GridView", GridLayoutActivity::class.java)
        addMenuItem("4. Adapters", AdaptersActivity::class.java)
        addMenuItem("5. Styles", StyleSamplesActivity::class.java)
        addMenuItem("6. Grid, List (Fragment)", GridListMenuActivity::class.java)
        addMenuItem("7. Dialog", DialogActivity::class.java)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        prepareMenu()

        val keys = actions.keys.toTypedArray<String?>()

        val adapter: ListAdapter =
            ArrayAdapter<String?>(this, android.R.layout.simple_list_item_1, keys)

        val av = findViewById<View?>(R.id.menu_list) as ListView
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

    fun addMenuItem(label: String?, cls: Class<*>?) {
        actions.put(label, Intent(this, cls))
    }

    companion object {
        const val DEBUG_TAG: String = "AdvancedLayoutsActivity"
    }
}
