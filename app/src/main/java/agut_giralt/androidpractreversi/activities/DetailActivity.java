package agut_giralt.androidpractreversi.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.fragments.FragmentDetail;

public class DetailActivity extends FragmentActivity {

    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FragmentDetail detalle = (FragmentDetail) getSupportFragmentManager().
                findFragmentById(R.id.FrgDetalle);
        detalle.viewDetails(getIntent().getIntExtra(POSITION, 0));
    }
}
