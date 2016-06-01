package com.example.hm5_agrawal_mishra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;
import com.example.hm5_agrawal_mishra.R;

public class PrefsFragmentSettings extends PreferenceFragment implements OnSharedPreferenceChangeListener {

final static String TAG = "PrefsFragmentSettings";
	

	/*
	 * ___________________________________________________________________ | |
	 * Function: PrefsFragmentSettings (constructor)
	 * |__________________________________________________________________
	 */
	public PrefsFragmentSettings() {
	}

	/*
	 * ___________________________________________________________________ | |
	 * Function: onCreate
	 * |__________________________________________________________________
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Load the preferences from an XML resource
		
	//	Toast.makeText(context, "btnClicked3333", Toast.LENGTH_SHORT).show(); 
		addPreferencesFromResource(R.xml.prefs_fragment_settings);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		super.onSaveInstanceState(savedInstanceState);	
	}

	/*
	 * ___________________________________________________________________ | |
	 * Function: onResume
	 * |__________________________________________________________________
	 */
	@Override
	public void onResume() {
		super.onResume();

		// Set up a listener whenever a key changes
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);

		// Set up a click listener
		Preference pref;
		pref = getPreferenceScreen().findPreference("key_company_info");
//		pref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//			public boolean onPreferenceClick(Preference preference) {
//				// Handle action on click here
//				try {					
//					Uri site = Uri.parse("https://en.wikipedia.org/wiki/Zoo");
//					Intent myIntent = new Intent(Intent.ACTION_VIEW, site);
//					startActivity(myIntent);
//				} catch (Exception e) {
//					// Log.e (TAG, "Browser failed", e);
//				}
//				return (true);
//			}
//		});
		
		
		pref = getPreferenceScreen().findPreference("high_score");
		String score = "High score of game is: "+Assets.high_score;
		pref.setSummary(score);
	}

	/*
	 * ___________________________________________________________________ | |
	 * Function: onSharedPreferenceChanged
	 * |__________________________________________________________________
	 */
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//		if (key.equals("prefs_music_enabled")) {
//			boolean b = sharedPreferences.getBoolean("prefs_music_enabled", true);
//			if (b == false) {
//				if (Assets.mp != null)
//					Assets.mp.setVolume(0, 0);			
//			} else {
//				if (Assets.mp != null)
//					Assets.mp.setVolume(1, 1);
//			}
//		}
	}	
}
