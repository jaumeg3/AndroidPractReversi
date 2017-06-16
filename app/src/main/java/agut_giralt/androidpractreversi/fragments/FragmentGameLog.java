package agut_giralt.androidpractreversi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import agut_giralt.androidpractreversi.R;

/**
 * Created by Nil Agut and Jaume Giralt.
 * This file has been created for this delivery. This fragment manages the log of the actual game.
 * It gets a string and it's displayed in the screen. Only for large screen devices.
 */

public class FragmentGameLog extends Fragment {

    TextView txtDetalle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_log, container, false);
    }

    public void showLog(String log) {
        txtDetalle = (TextView) getView().findViewById(R.id.LogText);
        txtDetalle.setText(log);
    }
}
