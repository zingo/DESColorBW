/*
MIT License

Copyright (c) 2022 Zingo Andersen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.crazycapy.descolorbw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    private val LOGTAG = "DESColorBW"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val DESColor : Int? = getProperty("persist.sys.hwc.enablecolor","0")?.toInt()
        if (DESColor == 0) {
            setProperty("persist.sys.hwc.enablecolor","1")
        }
        else {
            setProperty("persist.sys.hwc.enablecolor","0")
        }
        finishAffinity()
        System.exit(0)
    }

    fun getProperty(key: String?, value: String?): String? {
        try {
            val c = Class.forName("android.os.SystemProperties")
            val get: Method = c.getMethod("get", String::class.java)
            return get.invoke(c, key).toString()
        } catch (e: Exception) {
            Log.d(LOGTAG, "getProperty() exception")
            e.printStackTrace()
        }
        return value
    }

    fun setProperty(key: String?, value: String?) {
        try {
            val c = Class.forName("android.os.SystemProperties")
            val set: Method = c.getMethod("set", String::class.java, String::class.java)
            set.invoke(c, key, value)
        } catch (e: Exception) {
            Log.d(LOGTAG, "setProperty() exception")
            e.printStackTrace()
        }
    }
}