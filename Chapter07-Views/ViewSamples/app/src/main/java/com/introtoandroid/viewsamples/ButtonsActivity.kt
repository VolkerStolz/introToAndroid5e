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

        val basicButton: Button = findViewById(R.id.basic_button)
        basicButton.setOnClickListener {
            Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
        }

        val image_button = findViewById<View?>(R.id.image_button) as ImageButton
        image_button.setOnClickListener {
            Toast.makeText(
                this@ButtonsActivity, "Image button clicked",
                Toast.LENGTH_SHORT
            ).show()
        }


        val toggle_button = findViewById<View?>(R.id.toggle_button) as ToggleButton
        toggle_button.setOnClickListener {
            val tb = findViewById<View?>(R.id.text_feature) as TextView
            tb.setText(if (toggle_button.isChecked()) "This feature is on" else "This feature is off")
            Toast.makeText(
                this@ButtonsActivity, "Toggle button clicked",
                Toast.LENGTH_SHORT
            ).show()
        }

        val basic_switch = findViewById<View?>(R.id.switch1) as Switch
        basic_switch.setOnClickListener {
            val strState =
                if (basic_switch.isChecked()) "The switch is on" else "The switch is off"
            Toast.makeText(this@ButtonsActivity, strState, Toast.LENGTH_SHORT).show()
        }

        val submit_button = findViewById<View?>(R.id.submit_demo) as Button
        submit_button.setOnClickListener {
            val tb = findViewById<View?>(R.id.toggle_button) as ToggleButton
            Toast.makeText(
                this@ButtonsActivity, if (tb.isChecked()) "On" else "Off",
                Toast.LENGTH_LONG
            ).show()
            this@ButtonsActivity.finish()
        }

        val check_button = findViewById<View?>(R.id.checkbox01) as CheckBox
        check_button.setOnClickListener {
            val cb = findViewById<View?>(R.id.checkbox01) as CheckBox
            cb.text =
                if (check_button.isChecked()) "This option is checked" else "This option is not checked"
            Toast.makeText(
                this@ButtonsActivity, "CheckBox clicked",
                Toast.LENGTH_SHORT
            ).show()
        }

        val group = findViewById<RadioGroup>(R.id.RadioGroup01)

        group.setOnCheckedChangeListener { _, checkedId ->
            val tv = findViewById<TextView>(R.id.TextView01)
            if (checkedId != -1) {
                val rb = findViewById<RadioButton>(checkedId)
                tv.text = "You chose: ${rb.text}"

                Toast.makeText(
                    this@ButtonsActivity,
                    "${rb.text} selected",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                tv.text = "Choose 1"
            }
        }

        val clear_choice = findViewById<View?>(R.id.Button01) as Button
        clear_choice.setOnClickListener {
            val group = findViewById<View?>(R.id.RadioGroup01) as RadioGroup?
            group?.let {
                it.clearCheck()
                Toast.makeText(
                    this@ButtonsActivity, "Choice cleared",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}