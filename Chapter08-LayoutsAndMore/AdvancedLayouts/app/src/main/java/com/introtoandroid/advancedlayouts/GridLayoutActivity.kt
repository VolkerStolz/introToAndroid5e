package com.introtoandroid.advancedlayouts

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView


class GridLayoutActivity : AppCompatActivity() {
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.grid)

        val toolbar = findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val grid = findViewById<View?>(R.id.text_grid) as GridView
        grid.setAdapter(ArrayAdapter<String?>(this, R.layout.bigtextview, numbers))
        grid.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // GridView grid = (GridView) parent;
                val text = view as TextView
                Log.d(
                    AdvancedLayoutsActivity.Companion.DEBUG_TAG,
                    "pos: " + position + " , id: " + id
                )
                var num = text.getText() as String
                try {
                    num = (num.toInt() + 1).toString()
                    text.setText(num)
                } catch (e: Exception) {
                    // probably not a number, absorb the exception
                }
            }
        })
    }

    companion object {
        private val numbers =
            arrayOf<String?>("1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C")
    }
}
