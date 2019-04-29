package com.exchange.rates.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.exchange.rates.R
import dagger.android.support.AndroidSupportInjection

class DetailFragment : MvpAppCompatFragment(), DetailView {
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.detail_fragment_layout, container, false)

    companion object {
        fun newInstance() = DetailFragment()
    }
}