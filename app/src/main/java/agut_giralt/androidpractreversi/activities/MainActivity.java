package agut_giralt.androidpractreversi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.fragments.FragmentDBList;
import agut_giralt.androidpractreversi.fragments.PreferencesFragment;

/**
 * Created by Nil Agut and Jaume Giralt.
 * This file has been edited. Now, we have a new button and a menu.
 */

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnHelp = (Button) findViewById(R.id.btnHelp);
        Button btnInit = (Button) findViewById(R.id.btnInit);
        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnHelp.setOnClickListener(this);
        btnInit.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        // Part 2 work

        Button btnDB = (Button) findViewById(R.id.btnDB);
        btnDB.setOnClickListener(this);
        PreferenceManager.setDefaultValues(this, R.xml.options, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHelp:
                Intent intent = new Intent(this, ActivityHelp.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnInit:
                Intent intent1 = new Intent(this, ActivityGame.class);  // Change the intent
                startActivity(intent1);
                finish();
                break;
            case R.id.btnDB:
                Intent intent2 = new Intent(this, FragmentDBList.class);
                startActivity(intent2);
                finish();
            case R.id.btnExit:
                finish();
                break;
        }
    }

    // Part 2 work

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_settings, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                Intent intent = new Intent(this, PreferencesFragment.class);
                startActivity(intent);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}
