package com.ottsolution.demo.data.utils;

import android.content.Context
import android.util.Log
import org.xmlpull.v1.XmlPullParser

object ThemesUtil {

    fun getHashMapResource(c: Context, hashMapResId: Int): Map<String, String> {
        var map: HashMap<String, String> = HashMap()
        val parser = c.resources.getXml(hashMapResId)

        var key: String? = null
        var value: String? = null

        try {
            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    Log.d("utils", "Start document")
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (parser.name == "map") {
                        val isLinked = parser.getAttributeBooleanValue(null, "linked", false)

                        map = if (isLinked) LinkedHashMap() else HashMap()
                    } else if (parser.name == "entry") {
                        key = parser.getAttributeValue(null, "key")

                        if (null == key) {
                            parser.close()
                            return map
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (parser.name == "entry") {
                        map[key!!] = value!!
                        key = null
                        value = null
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (null != key) {
                        value = parser.text
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return map
        }

        return map
    }

    fun getResId(resName: String, c: Class<*>): Int {
        return try {
            val idField = c.getDeclaredField(resName)
            idField.getInt(idField)
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }
}