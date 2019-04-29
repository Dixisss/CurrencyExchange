package com.exchange.rates.presentation.detail

import android.os.Bundle
import com.exchange.rates.R
import com.exchange.rates.util.view.replaceFragmentInActivity
import com.exchange.rates.util.view.setupActionBar
import dagger.android.support.DaggerAppCompatActivity

class DetailActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_layout)

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        supportFragmentManager.findFragmentById(R.id.contentFrame) as DetailFragment?
            ?: DetailFragment.newInstance().also {
                replaceFragmentInActivity(it, R.id.contentFrame)
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}