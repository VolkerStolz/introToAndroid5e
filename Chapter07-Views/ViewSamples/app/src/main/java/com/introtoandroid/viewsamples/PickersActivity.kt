package com.introtoandroid.viewsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import android.widget.TextView
import android.widget.TimePicker
import android.widget.TimePicker.OnTimeChangedListener
import java.util.Calendar

class PickersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.pickers)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val text = findViewById<View?>(R.id.text_datetime) as TextView
        val date = findViewById<View?>(R.id.DatePicker01) as DatePicker
        val time = findViewById<View?>(R.id.TimePicker01) as TimePicker

        time.setOnTimeChangedListener(object : OnTimeChangedListener {
            override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute
                )
                text.setText(calendar.getTime().toString())
            }
        })

        val cal = Calendar.getInstance()

        date.init(
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH), object : OnDateChangedListener {
                override fun onDateChanged(
                    view: DatePicker?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    val calendar = Calendar.getInstance()
                    calendar.set(
                        year, monthOfYear, dayOfMonth, time.getCurrentHour(),
                        time.getCurrentMinute()
                    )
                    text.setText(calendar.getTime().toString())
                }
            })
    }
}