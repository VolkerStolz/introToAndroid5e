package com.introtoandroid.viewsamples

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView


class ViewSamplesActivity : AppCompatActivity() {
    protected var actions: MutableMap<String?, Any?> = HashMap<String?, Any?>()

    fun prepareMenu() {
        addMenuItem("Forms", FormsActivity::class.java)
        addMenuItem("Indicators", IndicatorsActivity::class.java)
        addMenuItem("Containers", ContainersActivity::class.java)
        addMenuItem("Text Display", TextDisplayActivity::class.java)
        addMenuItem("Events", EventsActivity::class.java)
        addMenuItem("Video", VideoViewActivity::class.java)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

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

    companion object {
        const val debugTag: String = "ViewSamples"
    }
}
