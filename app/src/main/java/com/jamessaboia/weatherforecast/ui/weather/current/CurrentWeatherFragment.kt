package com.jamessaboia.weatherforecast.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.databinding.CurrentWeatherFragmentBinding
import com.jamessaboia.weatherforecast.internal.glide.GlideApp
import com.jamessaboia.weatherforecast.ui.base.ScopedFragment
import kotlinx.coroutines.launch
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
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.current_weather_fragment,
                container, false
            )
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[CurrentWeatherViewModel::class.java]

        bindUi()

    }

    private fun bindUi() = launch {
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            it.current.apply {
                binding.groupLoading.visibility = View.GONE
                updateLocation("Manaus")
                updateDateToToday()
                updateTemperatures(temp_c, feelslike_c)
                updateCondition(condition.text)
                updatePrecipitation(precip_mm)
                updateWind(wind_mph)
                updateVisibility(vis_km)

                GlideApp.with(this@CurrentWeatherFragment)
                    .load("https:${condition.icon}")
                    .into(binding.imageViewConditionIcon)
            }
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

    private fun updateTemperatures(temperature: Int, feelslike: Float) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        binding.textViewTemperature.text = "$temperature$unitAbbreviation"
        binding.textViewFeelsLikeTemperature.text = "Feels like $feelslike$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        binding.textViewCondition.text = condition
    }

    private fun updatePrecipitation(precipitation: Float) {
        binding.textViewPrecipitation.text = "Precipitation: $precipitation"
    }

    private fun updateWind(windDirection: Float) {
        binding.textViewWind.text = "Wind: $windDirection"
    }

    private fun updateVisibility(visibility: Int) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi.")
        binding.textViewVisibility.text = "Visibility: $visibility $unitAbbreviation"
    }

}