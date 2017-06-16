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

/**
 * Created by Nil Agut and Jaume Giralt.
 * This file has been created for this delivery. This fragment manages the detail of previous games.
 * It connects to the database and represents the data saved.
 */

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
                SQLite.GameTable.DATE + ": " + cursor.getString(2) + "\n" +
                SQLite.GameTable.SIZE + ": " + cursor.getString(3) + "\n" +
                SQLite.GameTable.TIME + ": " + cursor.getString(4) + "\n" +
                SQLite.GameTable.PLAYERS + ": " + cursor.getString(5) + "\n" +
                SQLite.GameTable.WHITE_PIECES + ": " + cursor.getString(6) + "\n" +
                SQLite.GameTable.BLACK_PIECES + ": " + cursor.getString(7) + "\n" +
                SQLite.GameTable.FINAL_TIME + ": " + cursor.getString(8) + "\n" +
                SQLite.GameTable.POSITION + ": " +
                getResources().getString(Integer.valueOf(cursor.getString(9))) + "\n";
        return string;
    }
}

