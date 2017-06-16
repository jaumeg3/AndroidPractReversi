package agut_giralt.androidpractreversi.fragments;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import agut_giralt.androidpractreversi.R;

/**
 * Created by Nil Agut and Jaume Giralt.
 * This file has been created for this deliver. It manages the menu of shared preferences.
 */

public class PreferencesFragment extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new OpcionesFragment()).commit();

    }

    public static class OpcionesFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstaceState) {
            super.onCreate(savedInstaceState);
            addPreferencesFromResource(R.xml.options);
            PreferenceManager.setDefaultValues(getActivity(), R.xml.options, false);
        }
    }
}
