package com.jamessaboia.weatherforecast.ui.nextweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.data.network.response.ForecastResponse
import com.jamessaboia.weatherforecast.databinding.NextWeatherFragmentBinding
import com.jamessaboia.weatherforecast.utils.NetworkResponse
import com.jamessaboia.weatherforecast.utils.PreferenceHelper.getLocation
import org.koin.androidx.viewmodel.ext.android.viewModel

class NextWeatherDetailsFragment : Fragment() {

    private val viewModel by viewModel<NextWeatherDetailsViewModel>()
    lateinit var binding: NextWeatherFragmentBinding

    private lateinit var adapter: NextWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.next_weather_fragment,
                container, false
            )

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.nextDaysRv.layoutManager = LinearLayoutManager(requireContext())
        binding.nextDaysRv.setHasFixedSize(true)
    }

    private fun setupObservers() {
        viewModel.getNextWeather(requireContext(), getLocation(requireContext()))
            .observe(requireActivity()) { resource ->
                resource?.let {
                    when (it) {
                        is NetworkResponse.Success -> {
                            showLoading(false)
                            setupRecyclerViewAdapter(it.data)
                        }

                        is NetworkResponse.Error -> handleError(it.exception)
                        is NetworkResponse.Loading -> showLoading(true)
                    }
                }
            }
    }

    private fun setupRecyclerViewAdapter(nextWeather: ForecastResponse) {
        adapter = NextWeatherAdapter(nextWeather)
        binding.nextDaysRv.adapter = adapter
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
        binding.nextDaysTitle.isVisible = !loading
    }

    private fun handleError(exception: String) {
        binding.progressBar.isVisible = false
        Toast.makeText(requireContext(), exception, Toast.LENGTH_LONG).show()
    }
}