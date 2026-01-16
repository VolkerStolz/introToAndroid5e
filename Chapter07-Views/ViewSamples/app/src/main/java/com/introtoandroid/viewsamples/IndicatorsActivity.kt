package com.introtoandroid.viewsamples

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Chronometer
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView


class IndicatorsActivity : AppCompatActivity() {
    private var mToolbarHorizontalProgress: ProgressBar? = null
    private var mProgress: ProgressBar? = null
    private var mProgress2: ProgressBar? = null
    private var mProgress3: ProgressBar? = null
    private var mProgressHorizontalStatus = 0
    private var mProgressStatus = 0
    private var mProgress2Status = 0
    private var mProgress3Status = 0

    private val me: Activity = this

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS)
        supportRequestWindowFeature(Window.FEATURE_PROGRESS)

        setContentView(R.layout.indicators)

        val toolbar = findViewById<View?>(R.id.toolbar_progress) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        if (getSupportActionBar() != null) {
            getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        }

        val mToolbarProgress = findViewById<View?>(R.id.toolbar_spinner) as ProgressBar
        mToolbarProgress.setIndeterminate(false)
        mToolbarProgress.setVisibility(View.VISIBLE)
        mToolbarProgress.setProgress(5000)

        val timer = findViewById<View?>(R.id.Chronometer01) as Chronometer
        val base = timer.getBase()
        Log.d(ViewSamplesActivity.Companion.debugTag, "base = " + base)
        timer.setBase(0)

        mToolbarHorizontalProgress =
            findViewById<View?>(R.id.toolbar_horizontal_progress) as ProgressBar

        // Start lengthy operation in a background thread
        Thread(object : Runnable {
            override fun run() {
                while (mProgressHorizontalStatus < 100) {
                    try {
                        synchronized(this) {
                            (this as Object).wait(50)
                        }
                    } catch (e: Exception) {
                        Log.e(ViewSamplesActivity.Companion.debugTag, "wait failed", e)
                    }
                    mProgressHorizontalStatus++

                    // Update the progress bar
                    mHandler.post(object : Runnable {
                        override fun run() {
                            mToolbarHorizontalProgress!!.setProgress(mProgressHorizontalStatus)
                        }
                    })
                }
            }
        }).start()

        mProgress = findViewById<View?>(R.id.progress_bar) as ProgressBar

        // Start lengthy operation in a background thread
        Thread(object : Runnable {
            override fun run() {
                while (mProgressStatus < 100) {
                    try {
                        synchronized(this) {
                            (this as Object).wait(50)
                        }
                    } catch (e: Exception) {
                        Log.e(ViewSamplesActivity.Companion.debugTag, "wait failed", e)
                    }
                    mProgressStatus++

                    // Update the progress bar
                    mHandler.post(object : Runnable {
                        override fun run() {
                            mProgress!!.setProgress(mProgressStatus)
                        }
                    })
                }
            }
        }).start()

        mProgress2 = findViewById<View?>(R.id.progress_bar2) as ProgressBar

        // Start lengthy operation in a background thread
        Thread(object : Runnable {
            override fun run() {
                timer.start()
                while (mProgress2Status < 100) {
                    try {
                        synchronized(this) {
                            (this as Object).wait(100)
                        }
                    } catch (e: Exception) {
                        Log.e(ViewSamplesActivity.Companion.debugTag, "wait failed", e)
                    }
                    mProgress2Status++

                    // Update the progress bar
                    mHandler.post(object : Runnable {
                        override fun run() {
                            mProgress2!!.setProgress(mProgress2Status / 4)
                            mProgress2!!.setSecondaryProgress(mProgress2Status)
                            me.setProgress(mProgress2Status * 100)
                        }
                    })
                }
                mHandler.post(object : Runnable {
                    override fun run() {
                        val prog_stop = findViewById<View?>(R.id.progress_bar3) as ProgressBar
                        prog_stop.setVisibility(ProgressBar.GONE)
                    }
                })
                timer.stop()
            }
        }).start()

        mProgress3 = findViewById<View?>(R.id.progress_bar3) as ProgressBar

        // Start lengthy operation in a background thread
        Thread(object : Runnable {
            override fun run() {
                while (mProgress3Status < 100) {
                    try {
                        synchronized(this) {
                            (this as Object).wait(100)
                        }
                    } catch (e: Exception) {
                        Log.e(ViewSamplesActivity.Companion.debugTag, "wait failed", e)
                    }
                    mProgress3Status++

                    // Update the progress bar
                    mHandler.post(object : Runnable {
                        override fun run() {
                            mProgress3!!.setProgress(mProgress3Status / 4)
                            mProgress3!!.setSecondaryProgress(mProgress3Status)
                        }
                    })
                }
            }
        }).start()

        val seek = findViewById<View?>(R.id.seekbar1) as SeekBar

        seek.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromTouch: Boolean) {
                Log.d(
                    ViewSamplesActivity.Companion.debugTag,
                    "progress = " + progress + " fromTouch = " + fromTouch
                )
                (findViewById<View?>(R.id.seek_text) as TextView).setText("Value: " + progress)
                seekBar.setSecondaryProgress((progress + seekBar.getMax()) / 2)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // TODO Auto-generated method stub
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // TODO Auto-generated method stub
            }
        })

        val rate = findViewById<View?>(R.id.ratebar1) as RatingBar

        rate.setOnRatingBarChangeListener(object : OnRatingBarChangeListener {
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromTouch: Boolean) {
                Log.d(
                    ViewSamplesActivity.Companion.debugTag,
                    "rating = " + rating + " fromTouch = " + fromTouch
                )
                (findViewById<View?>(R.id.rating_text) as TextView).setText("Rating: " + rating)
            }
        })

        registerForContextMenu(timer)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        super.onContextItemSelected(item)

        var result = false
        val timer = findViewById<View?>(R.id.Chronometer01) as Chronometer
        when (item.getItemId()) {
            R.id.stop_timer -> {
                timer.stop()
                result = true
            }

            R.id.start_timer -> {
                timer.start()
                result = true
            }

            R.id.reset_timer -> {
                timer.setBase(SystemClock.elapsedRealtime())
                result = true
            }
        }
        return result
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v.getId() == R.id.Chronometer01) {
            getMenuInflater().inflate(R.menu.timer_context, menu)
            menu.setHeaderIcon(android.R.drawable.ic_media_play).setHeaderTitle("Timer controls")
        }
    }
}
