package com.example.wearos.sensor

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.wearos.network.UdpSender
import java.io.File
import java.io.FileWriter
import android.util.Log


open class BaseSensorService : Service(), SensorEventListener {
    public lateinit var sensorManager: SensorManager
    public var sensor: Sensor? = null

    override fun onCreate() {
        super.onCreate()
//        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        Log.d("SensorService", "Sensor: $sensor")
//        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val csvString: String = this.formatMessage(event)
        // var udp: UdpSender = UdpSender("192.168.179.13", 6666)
        var udp: UdpSender = UdpSender("192.0.0.2", 6666)
        Thread {
            udp.sendUDPMessage("${csvString}")
        }.start()
        // saveToCSV(csvString)
    }

    open fun formatMessage(event: SensorEvent): String {
        var message: String = "${event.timestamp}, ${event.values.joinToString(",")}\n"
        return message
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
