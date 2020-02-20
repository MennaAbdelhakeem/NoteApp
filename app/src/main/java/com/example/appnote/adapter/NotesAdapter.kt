package com.example.appnote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appnote.R
import com.example.appnote.database.model.Note

class NotesAdapter(var notes:List<Note>?):RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){

        var title:TextView
        var date:TextView
        init {
            title=itemView.findViewById(R.id.textview_title)
            date=itemView.findViewById(R.id.textview_date)
        }
    }
    var onItemClickListener: OnItemClickListener?=null
    interface OnItemClickListener{

        fun onItemClick(position: Int,item: Note?)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val view  =LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)

return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return notes?.size?:0

    }

    fun changeData(list:List<Note>?){
      this.notes=list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note =notes?.get(position)
        holder.title.setText(note?.title)
        holder.date.setText(note?.time)

        holder.itemView.setOnClickListener({onItemClickListener?.onItemClick(position,note)})
    }
}