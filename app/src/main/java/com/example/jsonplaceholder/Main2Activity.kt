package com.example.jsonplaceholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val data = intent.extras
        val table : Table = data.getSerializable("info") as Table

        val info_url :ImageView = findViewById(R.id.main2_info_url)
        val info_id : TextView = findViewById(R.id.main2_info_id)
        val info_title : TextView = findViewById(R.id.main2_info_title)

        Picasso.get().load(table?.getUrl()).fit().centerInside().into(info_url)
        info_id.text = table.getId()
        info_title.text = table.getTitle()
    }
}
