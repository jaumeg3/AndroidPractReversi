package agut_giralt.androidpractreversi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.activities.DetailActivity;

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
    public void onGameSelected(int position) {
        FragmentDetail fgdet = (FragmentDetail) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        boolean hayDetalle = (fgdet != null && fgdet.isInLayout());
        if (hayDetalle) {
            fgdet.viewDetails(position);
        }
        else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.POSITION, position);
            startActivity(intent);
        }
    }
}
