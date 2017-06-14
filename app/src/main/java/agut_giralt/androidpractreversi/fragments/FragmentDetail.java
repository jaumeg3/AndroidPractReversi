package agut_giralt.androidpractreversi.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.utils.SQLite;

public class FragmentDetail extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void viewDetails(int position) {
        TextView txtDetalle = (TextView) getView().findViewById(R.id.TxtDetalle);
        txtDetalle.setText(makeText(position));
    }

    private String makeText(int position) {
        SQLite database = SQLite.getInstance(getContext());
        Cursor cursor = database.getDataFromDB();
        cursor.moveToPosition(position);
        String string = "";
        string += SQLite.GameTable.USER + ": " + cursor.getString(1) + '\n' +
        SQLite.GameTable.DATE + ": " + cursor.getString(2) + "\n";
        //TODO : FINISH THE STRING
        return string;
    }
}

