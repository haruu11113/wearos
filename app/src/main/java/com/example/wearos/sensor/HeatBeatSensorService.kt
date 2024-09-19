package com.example.wearos.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.util.Log


class HeatBeatSensorService : BaseSensorService () {
    override fun onCreate() {
        super.onCreate()
        Log.i("HeatBeatSensorService", "created")
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT)
//        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        if (this.sensor != null) {
            sensorManager.registerListener(this, this.sensor, SensorManager.SENSOR_DELAY_NORMAL)
            Log.i("HeatBeatSensorService", "Sensor: $sensor")
            // 新しいgetSystemServiceの使用方法
        } else {
            Log.e("HeatBeatSensorService", "The sensor not available.")
        }
    }

    override fun formatMessage(event: SensorEvent): String {
        var message: String = "${event.timestamp}, heart rate, ${event.values.joinToString(",")}\n"
        return message
    }
}
