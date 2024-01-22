package com.jamessaboia.weatherforecast.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.base.ScopedFragment
import com.jamessaboia.weatherforecast.data.db.entity.History
import com.jamessaboia.weatherforecast.data.network.response.CurrentWeatherResponse
import com.jamessaboia.weatherforecast.databinding.HomeFragmentBinding
import com.jamessaboia.weatherforecast.utils.NetworkResponse
import com.jamessaboia.weatherforecast.utils.PreferenceHelper.getLocation
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.Date

class HomeFragment : ScopedFragment() {

    private val viewModel by viewModel<HomeViewModel>()
    lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.home_fragment,
                container, false
            )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        binding.nextDaysTitle.text =
            String.format(getString(R.string.today_city), getLocation(requireActivity()))
    }

    private fun setupObservers() {

        viewModel.getCurrentWeather(requireContext(), getLocation(requireContext()))
            .observe(requireActivity()) {
                it?.let { currentWeatherResponse ->
                    handleCurrentWeatherResponse(currentWeatherResponse)
                }
            }
    }

    private fun handleCurrentWeatherResponse(currentWeatherResponse: NetworkResponse<CurrentWeatherResponse>) {
        when (currentWeatherResponse) {
            is NetworkResponse.Success -> handleSuccessResponse(currentWeatherResponse.data)
            is NetworkResponse.Error -> handleError(currentWeatherResponse.exception)
            is NetworkResponse.Loading -> showLoading()
        }
    }

    private fun handleSuccessResponse(weatherData: CurrentWeatherResponse) {
        binding.run {
            progressBar.visibility = View.GONE
            infoContent.visibility = View.VISIBLE
        }
        addWeatherDataToHistory(weatherData)
    }

    private fun addWeatherDataToHistory(weatherData: CurrentWeatherResponse) {
        viewModel.currentWeather.value = weatherData
        viewModel.addHistory(
            History(
                getLocation(requireContext()),
                weatherData.current.temp_c,
                getCurrentTime().toString()
            )
        )
    }

    private fun handleError(exception: String) {
        binding.run {
            progressBar.visibility = View.GONE
            infoContent.visibility = View.GONE
        }

        Toast.makeText(requireContext(), exception, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.run {
            progressBar.visibility = View.VISIBLE
            infoContent.visibility = View.GONE
        }
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }
}