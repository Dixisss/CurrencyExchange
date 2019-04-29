package com.exchange.rates.presentation.converter

import android.os.Bundle
import com.exchange.rates.R
import com.exchange.rates.util.view.replaceFragmentInActivity
import com.exchange.rates.util.view.setupActionBar
import dagger.android.support.DaggerAppCompatActivity

class ConverterActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        setupActionBar(R.id.toolbar){}

        supportFragmentManager.findFragmentById(R.id.contentFrame) as ConverterFragment?
            ?: ConverterFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame)
            }
    }

}