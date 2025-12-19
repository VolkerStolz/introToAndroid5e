package com.introtoandroid.myfirstandroidapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MyFirstAndroidAppActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_first_android_app)

        Log.i(DEBUG_TAG, "In the onCreate() method of the MyFirstAndroidAppActivity")

        // Uncomment forceError() below to test debugging
//        forceError();
    }

    fun forceError() {
        if (true) {
            throw Error("Whoops")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_first_android_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val DEBUG_TAG = "MyFirstAppLogging"
    }
}
