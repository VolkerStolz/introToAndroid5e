package com.introtoandroid.sample.simplehardware

import android.content.BroadcastReceiver
import android.os.BatteryManager
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class BatteryActivity : AppCompatActivity() {
    private var receiverRegistered = false
    protected override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery)
        val start = findViewById(R.id.start) as android.widget.Button
        val stop = findViewById(R.id.stop) as android.widget.Button
        start.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View?) {
                try {
                    registerReceiver(
                        batteryRcv, android.content.IntentFilter(
                            android.content.Intent.ACTION_BATTERY_CHANGED
                        )
                    )
                    Toast.makeText(
                        this@BatteryActivity,
                        "Battery monitoring started", Toast.LENGTH_SHORT
                    )
                        .show()
                    receiverRegistered = true
                } catch (e: java.lang.Exception) {
                    android.util.Log.e(
                        BatteryActivity.Companion.DEBUG_TAG,
                        "Failed to register receiver"
                    )
                }
                start.setVisibility(android.view.View.GONE)
                stop.setVisibility(android.view.View.VISIBLE)
            }
        })
        stop.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View?) {
                if (receiverRegistered) {
                    try {
                        unregisterReceiver(batteryRcv)
                    } catch (e: java.lang.Exception) {
                        android.util.Log.e(
                            BatteryActivity.Companion.DEBUG_TAG,
                            "Failed to unregister receiver. Was it really registered?",
                            e
                        )
                    }
                    receiverRegistered = false
                }
                Toast.makeText(
                    this@BatteryActivity,
                    "Battery monitoring stopped", Toast.LENGTH_SHORT
                )
                    .show()
                stop.setVisibility(android.view.View.GONE)
                start.setVisibility(android.view.View.VISIBLE)
            }
        })
    }

    protected override fun onPause() {
        if (receiverRegistered) {
            try {
                unregisterReceiver(batteryRcv)
            } catch (e: java.lang.Exception) {
                android.util.Log.e(
                    BatteryActivity.Companion.DEBUG_TAG,
                    "Failed to unregister receiver. Was it really registered?",
                    e
                )
            }
            receiverRegistered = false
        }
        super.onPause()
    }

    private val batteryRcv: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: android.content.Intent) {
            try {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val maxValue = intent.getIntExtra(
                    BatteryManager.EXTRA_SCALE,
                    -1
                )
                val batteryStatus = intent.getIntExtra(
                    BatteryManager.EXTRA_STATUS, -1
                )
                val batteryHealth = intent.getIntExtra(
                    BatteryManager.EXTRA_HEALTH, -1
                )
                val batteryPlugged = intent.getIntExtra(
                    BatteryManager.EXTRA_PLUGGED, -1
                )
                val batteryTech = intent
                    .getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
                val batteryIcon = intent.getIntExtra(
                    BatteryManager.EXTRA_ICON_SMALL, -1
                )
                val batteryVoltage = intent.getIntExtra(
                    BatteryManager.EXTRA_VOLTAGE, -1
                ).toFloat() / 1000
                val battery = intent.getBooleanExtra(
                    BatteryManager.EXTRA_PRESENT, false
                )
                val batteryTemp = intent.getIntExtra(
                    BatteryManager.EXTRA_TEMPERATURE, -1
                ).toFloat() / 10
                /*
				 * used to determine keys and types Bundle extras =
				 * intent.getExtras(); Set<String> keys = extras.keySet();
				 * Iterator<String> allKeys = keys.iterator(); while
				 * (allKeys.hasNext()) { String key = allKeys.next();
				 * Log.v("Battery", key); }
				 */
                val chargedPct = (level * 100) / maxValue
                val batteryInfo = ("Battery Info:\nHealth="
                        + BatteryActivity.Companion.healthValueMap.get(batteryHealth) + "\n" + "Status="
                        + BatteryActivity.Companion.statusValueMap.get(batteryStatus) + "\n"
                        + "Charged % = " + chargedPct + "%\n" + "Plugged = "
                        + BatteryActivity.Companion.pluggedValueMap.get(batteryPlugged) + "\n"
                        + "Type = " + batteryTech + "\n" + "Voltage = "
                        + batteryVoltage + " volts\n" + "Temperature = "
                        + batteryTemp + "¡C\n" + "Battery present = " + battery
                        + "\n")
                val status = findViewById(R.id.status) as android.widget.TextView
                val icon = findViewById(R.id.icon) as android.widget.ImageView
                status.setText(batteryInfo)
                icon.setImageResource(batteryIcon)
                Toast.makeText(
                    this@BatteryActivity, "Battery state changed",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: java.lang.Exception) {
                android.util.Log.e(
                    BatteryActivity.Companion.DEBUG_TAG,
                    "Battery receiver failed: ",
                    e
                )
            }
        }
    }

    public override fun onCreateOptionsMenu(menu: android.view.Menu?): kotlin.Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_battery, menu)
        return true
    }

    public override fun onOptionsItemSelected(item: android.view.MenuItem): kotlin.Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val DEBUG_TAG = "BatteryActivity"
        private val healthValueMap: SparseArray<String?> =
            object : android.util.SparseArray<kotlin.String?>() {
                init {
                    put(BatteryManager.BATTERY_HEALTH_DEAD, "Dead")
                    put(BatteryManager.BATTERY_HEALTH_GOOD, "Good")
                    put(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE, "Over voltage")
                    put(BatteryManager.BATTERY_HEALTH_OVERHEAT, "Over heating")
                    put(BatteryManager.BATTERY_HEALTH_UNKNOWN, "Unknown")
                    put(
                        BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE,
                        "Failure, but unknown"
                    )
                    put(-1, "Not Reported")
                }
            }

        private val statusValueMap: android.util.SparseArray<kotlin.String?> =
            object : android.util.SparseArray<kotlin.String?>() {
                init {
                    put(BatteryManager.BATTERY_STATUS_CHARGING, "Charging")
                    put(BatteryManager.BATTERY_STATUS_DISCHARGING, "Discharging")
                    put(BatteryManager.BATTERY_STATUS_FULL, "Full")
                    put(BatteryManager.BATTERY_STATUS_NOT_CHARGING, "Not Charging")
                    put(BatteryManager.BATTERY_STATUS_UNKNOWN, "Unknown")
                    put(-1, "Not Reported")
                }
            }

        private val pluggedValueMap: android.util.SparseArray<kotlin.String?> =
            object : android.util.SparseArray<kotlin.String?>() {
                init {
                    put(BatteryManager.BATTERY_PLUGGED_AC, "On AC")
                    put(BatteryManager.BATTERY_PLUGGED_USB, "On USB")
                    put(-1, "Not Reported")
                }
            }
    }
}
