package com.jamessaboia.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.data.network.Api
import com.jamessaboia.weatherforecast.data.network.WeatherNetworkDataSourceImpl
import com.jamessaboia.weatherforecast.data.network.response.ConnectivityInterceptorImpl
import com.jamessaboia.weatherforecast.databinding.CurrentWeatherFragmentBinding
import com.jamessaboia.weatherforecast.ui.base.ScopedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    lateinit var binding: CurrentWeatherFragmentBinding
    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.current_weather_fragment,
                container, false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(CurrentWeatherViewModel::class.java)

        bindUi()
    }

    private fun bindUi() = launch{
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            binding.textView.text = it.toString()
        })
    }

}