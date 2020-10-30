package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private var num: Int = DEFAULT_NUMBER_VALUE
    private lateinit var numTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreNum(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val color = if (num.isOdd()) {
            Color.BLUE
        } else {
            Color.RED
        }

        numTextView = view.findViewById(R.id.num_text_view)
        numTextView.run {
            text = num.toString()
            setTextColor(color)
        }
    }

    private fun restoreNum(savedInstanceState: Bundle?) {
        val argumentsNum = arguments?.getInt(NUMBER_KEY, DEFAULT_NUMBER_VALUE)
            ?: DEFAULT_NUMBER_VALUE
        num = savedInstanceState?.getInt(NUMBER_KEY, argumentsNum) ?: argumentsNum
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(NUMBER_KEY, num)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val NUMBER_KEY = "NUMBER_KEY"
        private const val DEFAULT_NUMBER_VALUE = 0

        fun createInstance(number: Int): SecondFragment {
            val extras = Bundle().apply {
                putInt(NUMBER_KEY, number)
            }
            return SecondFragment().apply {
                arguments = extras
            }
        }
    }
}
