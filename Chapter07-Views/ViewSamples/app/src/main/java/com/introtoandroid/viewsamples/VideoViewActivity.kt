package com.introtoandroid.viewsamples

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.MediaController
import android.widget.VideoView

class VideoViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.videoview)
        val vv = findViewById<View?>(R.id.videoView) as VideoView

        val mc = MediaController(this)
        val video = Uri.parse("http://www.archive.org/download/Unexpect2001/Unexpect2001_512kb.mp4")
        vv.setMediaController(mc)
        vv.setVideoURI(video)

        checkNotNull(getSupportActionBar())
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
    }
}
