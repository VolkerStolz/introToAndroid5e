package com.introtoandroid.samelayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView


class ProgrammaticLayoutActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        val text1 = TextView(this)
        text1.setText(R.string.string1)

        val text2 = TextView(this)
        text2.setText(R.string.string2)
        text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60f)


        /*
         * Convert DP to PX
         * Our DP padding setting from our dimen resource
         * is in DP but setPadding requires pixels
         */
        val pixelDimen = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 16f,
            getResources().getDisplayMetrics()
        ).toInt()

        val ll = LinearLayout(this)
        ll.setOrientation(LinearLayout.VERTICAL)
        ll.setPadding(
            pixelDimen, pixelDimen,
            pixelDimen, pixelDimen
        )
        ll.addView(text1)
        ll.addView(text2)

        setContentView(ll)
    }
}