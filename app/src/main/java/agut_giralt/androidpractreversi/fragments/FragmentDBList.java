package agut_giralt.androidpractreversi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.activities.DetailActivity;
import agut_giralt.androidpractreversi.activities.MainActivity;

/**
 * Created by Nil Agut and Jaume Giralt.
 * This file has been created for this delivery. Is the activity that invoke the list fragment and
 * the detail fragment depending of the size screen.
 */

public class FragmentDBList extends FragmentActivity implements FragmentList.GameListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);
        FragmentList fragmentListado = (FragmentList) getSupportFragmentManager().
                findFragmentById(R.id.ListFrag);
        fragmentListado.setGameListener(this);
    }

    @Override
    public void onGameSelected(int position) {
        FragmentDetail fgdet = (FragmentDetail) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());
        if (hayDetalle) {
            fgdet.viewDetails(position);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.POSITION, position);
            startActivity(intent);
        }
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
