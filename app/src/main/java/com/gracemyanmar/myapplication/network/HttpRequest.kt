package com.gracemyanmar.myapplication.network

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class HttpRequest(private val url: URL) {
    enum class Method {
        POST, PUT, DELETE, GET
    }

    private val con: HttpURLConnection
    private var os: OutputStream? = null

    constructor(url: String?) : this(URL(url)) {
        Log.d("parameters", url)
    }

    @Throws(IOException::class)
    private fun prepareAll(method: Method) {
        con.doInput = true
        con.requestMethod = method.name
        if (method == Method.POST || method == Method.PUT) {
            con.doOutput = true
            os = con.outputStream
        }
    }

    @Throws(IOException::class)
    fun prepare(): HttpRequest {
        prepareAll(Method.GET)
        return this
    }

    @Throws(IOException::class)
    fun prepare(method: Method): HttpRequest {
        prepareAll(method)
        return this
    }

    fun withHeaders(vararg headers: String): HttpRequest {
        var i = 0
        val last = headers.size
        while (i < last) {
            val h = headers[i].split("[:]").toTypedArray()
            con.setRequestProperty(h[0], h[1])
            i++
        }
        return this
    }

    @Throws(IOException::class)
    fun withData(query: String?): HttpRequest {
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(query)
        writer.close()
        return this
    }

    @Throws(IOException::class)
    fun withData(params: HashMap<String, String>): HttpRequest {
        val result = StringBuilder()
        for ((key, value) in params) {
            result.append((if (result.length > 0) "&" else "") + key + "=" + value) //appends: key=value (for first param) OR &key=value(second and more)
            Log.d("parameters", "$key  ===>  $value")
        }
        withData(result.toString())
        return this
    }

    @Throws(IOException::class)
    fun send(): Int {
        return con.responseCode
    }

    @Throws(IOException::class)
    fun sendAndReadString(): String {
        val br = BufferedReader(InputStreamReader(con.inputStream))
        val response = StringBuilder()
        var line: String
        while (br.readLine().also { line = it } != null) {
            response.append("""
    $line
    
    """.trimIndent())
        }
        Log.d("ressss", response.toString())
        return response.toString()
    }

    @Throws(IOException::class)
    fun sendAndReadBytes(): ByteArray {
        val buffer = ByteArray(8192)
        val `is` = con.inputStream
        val output = ByteArrayOutputStream()
        var bytesRead: Int
        while (`is`.read(buffer).also { bytesRead = it } >= 0) {
            output.write(buffer, 0, bytesRead)
        }
        return output.toByteArray()
    }

    @Throws(JSONException::class, IOException::class)
    fun sendAndReadJSON(): JSONObject {
        return JSONObject(sendAndReadString())
    }

    init {
        con = url.openConnection() as HttpURLConnection
    }
}