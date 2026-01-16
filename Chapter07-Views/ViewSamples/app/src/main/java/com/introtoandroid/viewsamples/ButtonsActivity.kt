package com.introtoandroid.viewsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton


class ButtonsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.buttons)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val basic_button = findViewById<View?>(R.id.basic_button) as Button
        basic_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@ButtonsActivity, "Button clicked", Toast.LENGTH_SHORT).show()
            }
        })

        val image_button = findViewById<View?>(R.id.image_button) as ImageButton
        image_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(
                    this@ButtonsActivity, "Image button clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


        val toggle_button = findViewById<View?>(R.id.toggle_button) as ToggleButton
        toggle_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val tb = findViewById<View?>(R.id.text_feature) as TextView
                tb.setText(if (toggle_button.isChecked()) "This feature is on" else "This feature is off")
                Toast.makeText(
                    this@ButtonsActivity, "Toggle button clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val basic_switch = findViewById<View?>(R.id.switch1) as Switch
        basic_switch.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val strState =
                    if (basic_switch.isChecked()) "The switch is on" else "The switch is off"
                Toast.makeText(this@ButtonsActivity, strState, Toast.LENGTH_SHORT).show()
            }
        })

        val submit_button = findViewById<View?>(R.id.submit_demo) as Button
        submit_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val tb = findViewById<View?>(R.id.toggle_button) as ToggleButton
                Toast.makeText(
                    this@ButtonsActivity, if (tb.isChecked()) "On" else "Off",
                    Toast.LENGTH_LONG
                ).show()
                this@ButtonsActivity.finish()
            }
        })

        val check_button = findViewById<View?>(R.id.checkbox01) as CheckBox
        check_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val cb = findViewById<View?>(R.id.checkbox01) as CheckBox
                cb.setText(if (check_button.isChecked()) "This option is checked" else "This option is not checked")
                Toast.makeText(
                    this@ButtonsActivity, "CheckBox clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val group = findViewById<View?>(R.id.RadioGroup01) as RadioGroup
        group.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                val tv = findViewById<View?>(R.id.TextView01) as TextView
                if (checkedId != -1) {
                    val rb = findViewById<View?>(checkedId) as RadioButton?
                    if (rb != null) {
                        tv.setText("You chose: " + rb.getText())
                        Toast.makeText(
                            this@ButtonsActivity, rb.getText().toString() + " selected",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    tv.setText("Choose 1")
                }
            }
        })

        val clear_choice = findViewById<View?>(R.id.Button01) as Button
        clear_choice.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val group = findViewById<View?>(R.id.RadioGroup01) as RadioGroup?
                if (group != null) {
                    group.clearCheck()
                    Toast.makeText(
                        this@ButtonsActivity, "Choice cleared",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}