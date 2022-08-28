package com.ferhatozcelik.terminal;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
	public SettingsFragment() {
	}

	@Override
	public void onDisplayPreferenceDialog(Preference preference) {

	}

	@Override
	public void onCreatePreferences(Bundle bundle, String rootKey) {
		setPreferencesFromResource(R.xml.preferences, rootKey);
	}
}
