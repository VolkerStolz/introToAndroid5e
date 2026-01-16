package com.introtoandroid.viewsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.text.InputFilter.LengthFilter
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast


class TextInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.textinput)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        // Older Android:
        val text1 = findViewById<View?>(R.id.EditText01) as EditText
        // Since API 26(?) - no cast necessary!
        val text2 = findViewById<EditText>(R.id.EditText02)
        val spin = findViewById<View?>(R.id.Spinner01) as Spinner
        val submit = findViewById<View?>(R.id.submit) as Button

        submit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val text_sel = spin.getSelectedView() as TextView
                Toast.makeText(
                    this@TextInputActivity,
                    "1 = " + text1.getText() + " 2 = " + text2.getText() +
                            "\n spinner = " + text_sel.getText(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val COLORS = arrayOf<String?>(
            "red", "green", "orange", "blue", "purple",
            "black", "yellow", "cyan", "magenta"
        )

        val adapter = ArrayAdapter<String?>(
            this,
            android.R.layout.simple_dropdown_item_1line, COLORS
        )

        val text = findViewById<View?>(R.id.AutoCompleteTextView01) as AutoCompleteTextView
        text.setAdapter<ArrayAdapter<String?>?>(adapter)

        val mtext =
            findViewById<View?>(R.id.MultiAutoCompleteTextView01) as MultiAutoCompleteTextView
        mtext.setAdapter<ArrayAdapter<String?>?>(adapter)
        mtext.setTokenizer(CommaTokenizer())

        val text_filtered = findViewById<View?>(R.id.input_filtered) as EditText
        text_filtered.setFilters(
            arrayOf<InputFilter>(
                AllCaps(),
                LengthFilter(2)
            )
        )
    }
}