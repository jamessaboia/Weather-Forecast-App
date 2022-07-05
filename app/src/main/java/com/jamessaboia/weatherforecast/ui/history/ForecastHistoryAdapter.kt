package com.jamessaboia.weatherforecast.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jamessaboia.weatherforecast.R
import com.jamessaboia.weatherforecast.data.db.entity.History
import com.jamessaboia.weatherforecast.databinding.ForecastHistoryItemBinding


class ForecastHistoryAdapter(private val dataSet: List<History>) :
    RecyclerView.Adapter<ForecastHistoryAdapter.ViewHolder>() {

    lateinit var binding: ForecastHistoryItemBinding

    class ViewHolder(private val binding: ForecastHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.cidade.text = history.region
            binding.temp.text = "" + history.temp
            binding.dateTv.text = history.date
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context), R.layout.forecast_history_item,
                viewGroup, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val forecast = dataSet[position]
        viewHolder.bind(forecast)
    }

    override fun getItemCount() = dataSet.size
}
