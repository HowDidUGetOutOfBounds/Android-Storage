package com.example.storage.settings

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.storage.R

class SettingsFragmnet : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.filter_settings)

        val sortPreference: ListPreference? =
            findPreference("sortTanksByListPreference")

        if (sortPreference?.value == null) {
            // to ensure we don't get a null value
            // set first value by default
            sortPreference?.setValueIndex(0)
        }
        sortPreference?.summary = sortPreference?.entry.toString();
        sortPreference?.setOnPreferenceChangeListener(
            Preference.OnPreferenceChangeListener { preference, newValue ->
                preference?.summary = getResources().getStringArray(R.array.tank_filter_values_hum)[newValue.toString().toInt()]
                true
            })
    }


}