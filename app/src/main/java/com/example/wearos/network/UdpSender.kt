package com.example.wearos.network

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import android.util.Log
import java.net.InetSocketAddress

open class UdpSender(address: String, port: Int) {
    private var address: String // = "192.168.50.234"
    private var port: Int = 6666 // = 12345

    init {
        this.address = address;
        this.port = port;
    }

    // この関数はバックグラウンドスレッドで呼び出す必要があります
    // 例: Coroutine, AsyncTask, Threadなどを使用してください
    // 以下は単純なスレッドでの使用例です
    fun sendUDPMessage(message: String) {
        Log.i("UdpSender", "sendUDPMessage message: ${message} on ${this.address}:${this.port}")

        var sendData: ByteArray = message.toByteArray()
        val socket: DatagramSocket = DatagramSocket() // UDPソケットを開く
        var socketAddress: InetSocketAddress = InetSocketAddress(this.address, this.port)
        val pack = DatagramPacket(sendData, sendData.size, socketAddress)
        Log.i("UdpSender", "${pack}")
        socket.send(pack)
        socket.close()
        Log.i("UdpSender", "Done send")
    }
}
