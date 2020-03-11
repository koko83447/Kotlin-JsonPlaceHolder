package com.example.jsonplaceholder

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WelcomeActivity : AppCompatActivity() {

    private var response: String? = null
    private var path = "https://jsonplaceholder.typicode.com/photos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

    }

    private fun receive() {
        Thread(
            Runnable { response = httpGet() }
        ).start()
    }

    private fun httpGet(): String? {

        var ins: InputStream? = null

        with(URL(path).openConnection() as HttpURLConnection){
            connectTimeout = 5000
            readTimeout = 5000
            doInput = true
            requestMethod = "GET"
            if (responseCode == 200){
                ins = inputStream
                return parseInfo(ins)
            }
        }
        return null
    }

    @Throws(IOException::class)
    private fun parseInfo(ins: InputStream?): String {
        val br = BufferedReader(InputStreamReader(ins))
        val sb = StringBuilder()
        var line:String? = null
        while ({line = br.readLine();line}() != null){
            sb.append(line!!+"\n")
        }
        return sb.toString()
    }


    fun gotomain(view: View) {
        if (haveInternet()) {
            receive()
            Toast.makeText(this@WelcomeActivity, "歡迎使用", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                val preferences = getSharedPreferences("Main", Context.MODE_MULTI_PROCESS)
                preferences.edit().putString("JsonList", response).commit()
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
            }, 1000)
        } else {
            Toast.makeText(this@WelcomeActivity, "您尚未連線", Toast.LENGTH_LONG).show()
        }
    }

    private fun haveInternet(): Boolean {
        var result = false
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connManager.activeNetworkInfo
        if (info == null || !info.isConnected) {
            result = false
        } else {
            result = true
        }
        return result
    }
}
