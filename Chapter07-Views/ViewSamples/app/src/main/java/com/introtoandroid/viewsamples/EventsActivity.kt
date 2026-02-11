package com.introtoandroid.viewsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.View.OnLongClickListener
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener
import android.view.ViewTreeObserver.OnTouchModeChangeListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class EventsActivity : AppCompatActivity() {
    private var mSaveText: String? = null
    private var mGestures: GestureDetector? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (mGestures != null) {
            return mGestures!!.onTouchEvent(event)
        } else {
            return super.onTouchEvent(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        /* events to demonstrate: touch mode changes
        events on entire screen long press gesture
        focus changes on views key events -- hmm...
        not working the way I thought, maybe skip
        */
        val events = findViewById<View?>(R.id.last_event_text) as TextView
        val all = findViewById<View>(R.id.events_screen)
        val vto = all.getViewTreeObserver()

        vto.addOnTouchModeChangeListener(object : OnTouchModeChangeListener {
            override fun onTouchModeChanged(isInTouchMode: Boolean) {
                events.setText("Touch mode: " + isInTouchMode)
            }
        })

        vto.addOnGlobalFocusChangeListener(object : OnGlobalFocusChangeListener {
            override fun onGlobalFocusChanged(oldFocus: View?, newFocus: View?) {
                if (oldFocus != null && newFocus != null) {
                    events.setText(
                        ("Focus \nfrom: " + oldFocus.toString() + " \nto: "
                                + newFocus.toString())
                    )
                }
            }
        })

        val long_press = findViewById<View?>(R.id.long_press) as Button

        long_press.setOnLongClickListener(object : OnLongClickListener {
            override fun onLongClick(v: View): Boolean {
                // we know what View already, use it anyway
                events.setText("Long click: " + v.toString())
                return true
            }
        })

        mGestures = GestureDetector(this, object : SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?, e2: MotionEvent?, velocityX: Float,
                velocityY: Float
            ): Boolean {
                events.setText("Fling! \nx= " + velocityX + "px/s\ny=" + velocityY + "px/s")
                return super.onFling(e1, e2, velocityX, velocityY)
            }

            override fun onScroll(
                e1: MotionEvent?, e2: MotionEvent?, distanceX: Float,
                distanceY: Float
            ): Boolean {
                events.setText("Scroll! \nX = " + distanceX + "\nY = " + distanceY)
                return super.onScroll(e1, e2, distanceX, distanceY)
            }
        })

        val focus = findViewById<View?>(R.id.text_focus_change) as TextView

        focus.setOnFocusChangeListener(object : OnFocusChangeListener {
            override fun onFocusChange(v: View, hasFocus: Boolean) {
                if (hasFocus) {
                    if (mSaveText != null) {
                        (v as TextView).setText(mSaveText)
                    }
                } else {
                    mSaveText = (v as TextView).getText().toString()
                    v.setText("")
                }
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent?): Boolean {
        Log.d(ViewSamplesActivity.Companion.debugTag, "Multi code = " + keyCode)
        return super.onKeyMultiple(keyCode, repeatCount, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyUp(keyCode, event)
    }
}