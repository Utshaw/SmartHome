package com.example.utshaw.smarthome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

/**
 * Created by utshaw on 6/19/17.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String PREF_KEY_EDIT_TEXT = "pref_key_edit_text";
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);


        //Getting SharedPreference
        sharedPreferences = getPreferenceManager().getSharedPreferences();

        //"Painting" the screens preference screen
        onCreatePreference();

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    void onCreatePreference()
    {
        Preference editTextPreference = findPreference(PREF_KEY_EDIT_TEXT);
        editTextPreference.setSummary(sharedPreferences.getString(PREF_KEY_EDIT_TEXT,"Not Found"));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        updatePreference(findPreference(s),s);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void updatePreference(Preference preference, String key)
    {
        if(preference ==null)
        {
            return;
        }
        if(preference instanceof EditTextPreference)
        {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            if(editTextPreference.getKey().equals(PREF_KEY_EDIT_TEXT))
            {
                editTextPreference.setSummary("Summary Changed");
            }
        }
    }
}
