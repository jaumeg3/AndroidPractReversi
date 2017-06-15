package agut_giralt.androidpractreversi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import agut_giralt.androidpractreversi.R;


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
        Log.d("LogForTheGame",log);
    }
}
