package com.example.jsonplaceholder

import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList


object Json {

    fun getTable(response: String): List<Table> {
        var tableList = ArrayList<Table>()
        try {
            val location = JSONArray(response)
            for (i in 0 until location.length()) {
                val jsonObject = location.getJSONObject(i)

                val id = jsonObject.optString("id")
                val title = jsonObject.optString("title")
                val url = jsonObject.optString("url")
                val thumbnailUrl = jsonObject.optString("thumbnailUrl")

                tableList.add(Table(id, title, url, thumbnailUrl))
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return tableList
    }

}

