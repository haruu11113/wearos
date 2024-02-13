package com.example.wearos.network

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UdpSender {
    private lateinit var message: String // = "Hello, UDP!";
    private lateinit var ip: String // = "192.168.50.234"
    private var port: Int = 6666 // = 12345

    fun init(message: String, ip: String, port: Int) {
        this.message = message;
        this.ip = ip;
        this.port = port;
    }

    // この関数はバックグラウンドスレッドで呼び出す必要があります
    // 例: Coroutine, AsyncTask, Threadなどを使用してください
    // 以下は単純なスレッドでの使用例です

    fun sendUDPMessage(message: String, address: String, port: Int) {
        val socket = DatagramSocket() // UDPソケットを開く
        socket.use { datagramSocket ->
            val buffer = message.toByteArray() // 文字列をバイト配列に変換
            val packet = DatagramPacket(buffer, buffer.size, InetAddress.getByName(address), port) // パケットを作成
            datagramSocket.send(packet) // パケットを送信
        }
    }
}
