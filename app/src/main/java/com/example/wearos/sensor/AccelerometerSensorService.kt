package com.example.wearos.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.Log


class AccelerometerSensorService : BaseSensorService () {
    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        Log.d("SensorService", "Sensor: $sensor")
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
}