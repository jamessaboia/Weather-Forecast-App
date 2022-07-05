package com.jamessaboia.weatherforecast.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.jamessaboia.weatherforecast.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

}