package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FirstFragment.ClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)

        if (supportFragmentManager.findFragmentByTag(FIRST_FRAGMENT_TAG) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, FirstFragment(), FIRST_FRAGMENT_TAG)
                .commit()
        }
    }

    override fun onNumClicked(num: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, SecondFragment.createInstance(num))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
    }
}
