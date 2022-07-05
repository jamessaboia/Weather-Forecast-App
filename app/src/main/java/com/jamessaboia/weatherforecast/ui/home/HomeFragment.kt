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
import com.jamessaboia.weatherforecast.databinding.HomeFragmentBinding
import com.jamessaboia.weatherforecast.utils.NetworkResponse
import com.jamessaboia.weatherforecast.utils.PreferenceHelper.getLocation
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
            .observe(requireActivity(), {
                it?.let { resource ->
                    when (resource) {
                        is NetworkResponse.Success -> {
                            binding.progressBar.visibility = View.GONE
                            viewModel.currentWeather.value = resource.data

                            viewModel.addHistory(
                                History(
                                    getLocation(requireContext()),
                                    resource.data.current.temp_c,
                                    getCurrentTime().toString()
                                )
                            )
                        }
                        is NetworkResponse.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), resource.exception, Toast.LENGTH_LONG)
                                .show()
                        }
                        is NetworkResponse.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            })
    }

    private fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }


}