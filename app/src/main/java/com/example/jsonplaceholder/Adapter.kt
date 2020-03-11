package com.example.jsonplaceholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var tableList = ArrayList<Table>()
    private var listener: Listener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val table = tableList[position]

        Picasso.get().load(table.getThumbnailUrl()).fit().centerInside()
            .into(holder.info_thumbnailUrl)
        holder.info_id.setText(table.getId())
        holder.info_title.setText(table.getTitle())

        holder.info_thumbnailUrl.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                listener?.onClick(position)
            }
        })
    }

    override fun getItemCount(): Int {
        return tableList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var info_thumbnailUrl: ImageView
        var info_id: TextView
        var info_title: TextView

        init {
            info_thumbnailUrl = itemView.findViewById(R.id.info_thumbnailUrl)
            info_id = itemView.findViewById(R.id.info_id)
            info_title = itemView.findViewById(R.id.info_title)
        }
    }

    fun jsonList(tableList: List<Table>) {
        this.tableList.addAll(tableList)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(position: Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }
}