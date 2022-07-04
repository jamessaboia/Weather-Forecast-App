package com.jamessaboia.weatherforecast.ui.weather.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.data.network.Api
import com.jamessaboia.weatherforecast.data.network.WeatherNetworkDataSourceImpl
import com.jamessaboia.weatherforecast.data.network.response.ConnectivityInterceptorImpl
import com.jamessaboia.weatherforecast.databinding.CurrentWeatherFragmentBinding
import com.jamessaboia.weatherforecast.internal.glide.GlideApp
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

            binding.groupLoading.visibility = View.GONE
            updateLocation("New York")
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelslike)
            updateCondition(it.weatherDescriptions)
            updatePrecipitation(it.precip)
            updateWind(it.windDir, it.windSpeed)
            updateVisibility(it.visibility)

//            GlideApp.with(this@CurrentWeatherFragment)
//                .load(it.weatherIcons[0])
//                .into(binding.imageViewConditionIcon)
        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial

    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = getString(R.string.today)
    }

    private fun updateTemperatures(temperature: Int, feelslike: Int) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        binding.textViewTemperature.text = "$temperature$unitAbbreviation"
        binding.textViewFeelsLikeTemperature.text = "Feels like $feelslike$unitAbbreviation"
    }

    private fun updateCondition(condition: List<String>) {
        binding.textViewCondition.text = condition.toString()
    }

    private fun updatePrecipitation(precipitation: Float) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        binding.textViewPrecipitation.text = "Precipitation: $precipitation $unitAbbreviation"
    }

    private fun updateWind(windDirection: String, windSpeed: Int) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        binding.textViewWind.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }

    private fun updateVisibility(visibility: Int) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi.")
        binding.textViewVisibility.text = "Visibility: $visibility $unitAbbreviation"
    }

}