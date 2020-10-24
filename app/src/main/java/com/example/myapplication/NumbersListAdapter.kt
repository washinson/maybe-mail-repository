package com.example.myapplication

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NumbersListAdapter(
    numbersList: List<Int>
) : RecyclerView.Adapter<NumbersListViewHolder>() {
    interface ClickListener {
        fun onNumClicked(num: Int)
    }

    private var listener: ClickListener? = null
    private val numbers = numbersList.toMutableList()

    fun setOnClickListener(listener: ClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersListViewHolder {
        return NumbersListViewHolder.createInstance(parent.context, parent)
    }

    override fun onBindViewHolder(holder: NumbersListViewHolder, position: Int) {
        val textColor = if (position % 2 == 0) {
            Color.RED
        } else {
            Color.BLUE
        }
        val value = position + 1
        holder.numTextView.run {
            text = value.toString()
            setTextColor(textColor)
            setOnClickListener {
                listener?.onNumClicked(value)
            }
        }
    }

    override fun getItemCount() = numbers.size

    fun addNum(num: Int) {
        numbers.add(num)
        notifyDataSetChanged()
    }
}