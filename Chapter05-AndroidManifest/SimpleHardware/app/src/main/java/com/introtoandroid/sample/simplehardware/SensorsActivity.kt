package com.introtoandroid.sample.simplehardware

import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SensorsActivity : AppCompatActivity(), SensorEventListener {
    private var sensors: SensorManager? = null
    protected override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)
        val status = findViewById(R.id.status) as android.widget.TextView
        sensors = getSystemService(android.content.Context.SENSOR_SERVICE) as SensorManager?

        val sensorPicker: RadioGroup = findViewById(R.id.sensor_group) as RadioGroup

        val start = findViewById(R.id.start_sensor) as android.widget.Button
        val stop = findViewById(R.id.stop_sensor) as android.widget.Button

        sensorPicker
            .setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup, checkedId: kotlin.Int) {
                    android.util.Log.v(SensorsActivity.Companion.DEBUG_TAG, "onCheckedChanged")
                    handleStartSensor(status, start, stop, checkedId)
                }
            })

        start.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View?) {
                android.util.Log.v(SensorsActivity.Companion.DEBUG_TAG, "onClickListener")
                val checkedId: kotlin.Int = sensorPicker.getCheckedRadioButtonId()
                if (checkedId == -1) {
                    Toast.makeText(
                        this@SensorsActivity,
                        "Select a sensor first",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    handleStartSensor(status, start, stop, checkedId)
                }
            }
        })

        stop.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View?) {
                sensors?.unregisterListener(this@SensorsActivity)
                start.setVisibility(android.view.View.VISIBLE)
                stop.setVisibility(android.view.View.GONE)
            }
        })
    }

    override fun onAccuracyChanged(sensor: android.hardware.Sensor, accuracy: kotlin.Int) {
        val sensorName = sensor.getName()

        val status = findViewById(R.id.status) as android.widget.TextView

        status.setText(
            ("Sensor: " + sensorName + " changed accuracy to: "
                    + accuracy)
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // do as little as possible here; offload work to another thread if
        // needed; don't block
        val sensorMessage = java.lang.StringBuilder(event?.sensor!!.getName())
            .append(" new values: ")

        for (value in event?.values!!) {
            sensorMessage.append("[").append(value).append("]")
        }

        sensorMessage.append(" with accuracy ").append(event.accuracy)
        sensorMessage.append(" at timestamp ").append(event.timestamp)

        sensorMessage.append(".")

        val status = findViewById(R.id.status) as android.widget.TextView
        status.setText(sensorMessage)
    }

    override fun onPause() {
        sensors?.unregisterListener(this)
        super.onPause()
    }

    private fun handleStartSensor(
        status: android.widget.TextView, start: android.widget.Button, stop: android.widget.Button,
        checkedId: kotlin.Int
    ) {
        val sensorId: kotlin.Int = SensorsActivity.Companion.buttonSensorMap.get(checkedId)
        val defaultSensor = sensors?.getDefaultSensor(sensorId)
        var isAvailable = false
        if (defaultSensor != null) {
            isAvailable = sensors?.registerListener(
                this@SensorsActivity,
                defaultSensor, SensorManager.SENSOR_DELAY_NORMAL
            ) == true
        }
        if (!isAvailable) {
            val checked: RadioButton = findViewById(checkedId) as RadioButton
            status.setText(
                ("Current sensor (" + checked.getText()
                        + ") not available.")
            )
        } else {
            stop.setVisibility(android.view.View.VISIBLE)
            start.setVisibility(android.view.View.GONE)
        }
    }

    public override fun onCreateOptionsMenu(menu: android.view.Menu?): kotlin.Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensors, menu)
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
        private const val DEBUG_TAG = "SensorsActivity"
        private val buttonSensorMap: android.util.SparseIntArray =
            object : android.util.SparseIntArray() {
                init {
                    put(R.id.sensor_accel, android.hardware.Sensor.TYPE_ACCELEROMETER)
                    put(R.id.sensor_gravity, android.hardware.Sensor.TYPE_GRAVITY)
                    put(R.id.sensor_linear_accel, android.hardware.Sensor.TYPE_LINEAR_ACCELERATION)
                    put(R.id.sensor_mag, android.hardware.Sensor.TYPE_MAGNETIC_FIELD)
                    put(R.id.sensor_orient, android.hardware.Sensor.TYPE_ORIENTATION)
                    put(R.id.sensor_gyro, android.hardware.Sensor.TYPE_GYROSCOPE)
                    put(R.id.sensor_rot_vector, android.hardware.Sensor.TYPE_ROTATION_VECTOR)
                    put(R.id.sensor_light, android.hardware.Sensor.TYPE_LIGHT)
                    put(R.id.sensor_prox, android.hardware.Sensor.TYPE_PROXIMITY)
                    put(R.id.sensor_pressure, android.hardware.Sensor.TYPE_PRESSURE)
                    put(R.id.sensor_rel_humid, android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY)
                    put(R.id.sensor_ambient_temp, android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE)
                    put(R.id.sensor_temp, android.hardware.Sensor.TYPE_TEMPERATURE)
                    put(R.id.sensor_step_detector, android.hardware.Sensor.TYPE_STEP_DETECTOR)
                    put(R.id.sensor_step_counter, android.hardware.Sensor.TYPE_STEP_COUNTER)
                }
            }
    }
}
