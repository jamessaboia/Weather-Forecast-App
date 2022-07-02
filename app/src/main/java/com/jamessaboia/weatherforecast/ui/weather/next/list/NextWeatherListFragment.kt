package com.jamessaboia.weatherforecast.ui.weather.next.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamessaboia.weatherforecast.R

class NextWeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = NextWeatherListFragment()
    }

    private lateinit var viewModel: NextWeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.next_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NextWeatherListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}