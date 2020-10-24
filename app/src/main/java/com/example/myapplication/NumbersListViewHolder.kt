package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumbersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val numTextView = itemView.findViewById<TextView>(R.id.num_text_view)

    companion object {
        fun createInstance(context: Context, parent: ViewGroup): NumbersListViewHolder {
            val view = LayoutInflater
                    .from(context)
                    .inflate(R.layout.numbers_list_item, parent, false)
            return NumbersListViewHolder(view)
        }
    }
}