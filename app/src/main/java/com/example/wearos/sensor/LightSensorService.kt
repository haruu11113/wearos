package com.example.wearos.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.util.Log


import com.example.wearos.sensor.BaseSensorService

class LightSensorService : BaseSensorService() {
    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ALL)
        if (this.sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            // 新しいgetSystemServiceの使用方法
        } else {
            Log.e("AccelerometerSensorService", "The sensor not available.")
        }
    }

    override fun formatMessage(event: SensorEvent): String {
        var message: String = "${event.timestamp}, light, ${event.values.joinToString(",")}\n"
        Log.i("SensorService", "LightSensorService: $sensor")
        return message
    }
}
