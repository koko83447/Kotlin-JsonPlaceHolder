package com.example.jsonplaceholder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response:String = getSharedPreferences("Main", Context.MODE_MULTI_PROCESS).getString("JsonList", "")
        val myAdapter = Adapter()
        myAdapter.jsonList(Json.getTable(response))
        val recyclerView : RecyclerView = findViewById(R.id.recycle_view)
        recyclerView.setAdapter(myAdapter)

        val gridLayoutManager = GridLayoutManager(this@MainActivity, 4)
        recyclerView.layoutManager = gridLayoutManager

        myAdapter.setListener(object : Adapter.Listener {
            override fun onClick(position: Int) {
                val table = Json.getTable(response)[position]
                val intent = Intent(this@MainActivity, Main2Activity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("info", table)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }
}
