package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment() {
    interface ClickListener : NumbersListAdapter.ClickListener

    private val listener: ClickListener by lazy {
        activity as ClickListener
    }
    private lateinit var numbersList: RecyclerView
    private lateinit var numberListAdapter: NumbersListAdapter
    private lateinit var addNumButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.first_fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        numbersList = view.findViewById(R.id.numbers_list)
        addNumButton = view.findViewById(R.id.add_number_button)

        initNumbersList()
        initAddNumButton()
    }

    private fun initAdapter(savedInstanceState: Bundle?) {
        val listSize = savedInstanceState?.getInt(ARRAY_COUNT_KEY, DEFAULT_LIST_SIZE)
            ?: DEFAULT_LIST_SIZE
        numberListAdapter = NumbersListAdapter(
            List(listSize) { index -> index + 1 }
        )
    }

    private fun initNumbersList() {
        numberListAdapter.setOnClickListener(listener)
        val colsNumber = if (isLandscape()) {
            LANDSCAPE_COLS_NAM
        } else {
            PORTRAIT_COLS_NUM
        }
        numbersList.run {
            layoutManager = GridLayoutManager(context, colsNumber)
            adapter = numberListAdapter
        }
    }

    private fun initAddNumButton() {
        addNumButton.setOnClickListener {
            numberListAdapter.addNum(numberListAdapter.itemCount + 1)
        }
    }

    private fun isLandscape() = resources.getBoolean(R.bool.is_landscape)

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARRAY_COUNT_KEY, numberListAdapter.itemCount)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        numberListAdapter.setOnClickListener(null)
    }

    companion object {
        private const val LANDSCAPE_COLS_NAM = 4
        private const val PORTRAIT_COLS_NUM = 3
        private const val ARRAY_COUNT_KEY = "ARRAY_COUNT_KEY"
        private const val DEFAULT_LIST_SIZE = 100
    }
}
