package com.jamessaboia.weatherforecast.ui.weather.next.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamessaboia.weatherforecast.R

class NextWeatherDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = NextWeatherDetailsFragment()
    }

    private lateinit var viewModel: NextWeatherDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_weather_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NextWeatherDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}