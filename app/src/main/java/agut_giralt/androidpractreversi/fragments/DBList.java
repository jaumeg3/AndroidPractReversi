package agut_giralt.androidpractreversi.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.activities.DetailActivity;
import agut_giralt.androidpractreversi.utils.Game;

public class DBList extends FragmentActivity implements FragmentList.GameListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_main);
        FragmentList fragmentListado = (FragmentList) getSupportFragmentManager().
                findFragmentById(R.id.ListFrag);
        fragmentListado.setGameListener(this);
    }

    @Override
    public void onGameSelected(Game c) {
        FragmentDetail fgdet = (FragmentDetail) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());

        if (hayDetalle) {
            fgdet.viewDetails(c.getPosition());
        }
        else {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(DetailActivity.EXTRA_TEXTO, c.getPosition());
            startActivity(i);
        }

    }
}
