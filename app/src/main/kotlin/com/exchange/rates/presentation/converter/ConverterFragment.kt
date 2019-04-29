package com.exchange.rates.presentation.converter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.exchange.rates.presentation.detail.ConverterPresenter
import com.exchange.rates.presentation.detail.ConverterView
import com.exchange.rates.presentation.detail.DetailActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.converter_fragment_layout.*
import javax.inject.Inject
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.exchange.rates.R
import java.util.*

class ConverterFragment : MvpAppCompatFragment(), ConverterView {

    @Inject
    @InjectPresenter
    lateinit var converterPresenter: ConverterPresenter

    @ProvidePresenter
    fun providePresenter() = converterPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.converter_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_exchange.setOnClickListener { converterPresenter.onExchangeClicked() }
    }

    override fun setFromCurrency(currency: Currency) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setToCurrency(currency: Currency) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun conversionResult(result: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNetworkNotAvailableError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openDetailScreen() {
        startActivity(Intent(context, DetailActivity::class.java))
    }

    companion object {
        fun newInstance() = ConverterFragment()
    }
}