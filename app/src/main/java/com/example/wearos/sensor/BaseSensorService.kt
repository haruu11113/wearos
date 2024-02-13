package com.example.wearos.sensor

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import java.io.File
import java.io.FileWriter


open class BaseSensorService : Service(), SensorEventListener {
    public lateinit var sensorManager: SensorManager
    public lateinit var sensor: Sensor

    override fun onCreate() {
        super.onCreate()
//        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        Log.d("SensorService", "Sensor: $sensor")
//        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val csvString = "${event.timestamp},${event.values.joinToString(",")}\n"
        Log.d("SensorService", "$sensor csvString : $csvString")
//        var udp: UdpSender = UdpSender()
//        Thread {
//            udp.sendUDPMessage("message", "192.168.50.234", 6666)
//        }.start()
//        Log.d("SensorService", "aaaaaaaaaa")

        saveToCSV(csvString)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // 精度が変更された場合の処理
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun saveToCSV(dataString: String) {
        val file = File(applicationContext.filesDir, "sensor_data.csv")
        val writer = FileWriter(file, true)
        writer.append()
        writer.flush()
        writer.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}
