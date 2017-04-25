package agut_giralt.androidpractreversi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import agut_giralt.androidpractreversi.R;
import agut_giralt.androidpractreversi.utils.Variables;

/**
 * Created by Nil Agut and Jaume Giralt.
 *
 * Els comentaris han sigut un intent de fer que la layout fos visible i invisible un Edit Text per
 * jugar 2 persones en un mateix dispositiu. En la següent entrega esperem implementar-ho.
 */

public class ActivityOptions extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText player = (EditText) findViewById(R.id.aliasEditText1);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.size);
        RadioButton sizeGrid = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        CheckBox time = (CheckBox) findViewById(R.id.time);
        //RadioButton OnePlayer = (RadioButton) findViewById(R.id.OnePlayer);
        //RadioButton TwoPlayers = (RadioButton) findViewById(R.id.TwoPlayers);
        //EditText editText2 = (EditText) findViewById(R.id.aliasEditText2);
        switch (view.getId()) {
            case R.id.startBtn:
                if (!player.getText().toString().isEmpty()) {
                    Intent intent = new Intent(this, ActivityGame.class);
                    intent.putExtra(Variables.USER, player.getText().toString());
                    intent.putExtra(Variables.SIZE, Integer.parseInt(sizeGrid.getText().toString()));
                    intent.putExtra(Variables.TIME, time.isChecked());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Please, fill username field", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        /*
        if (OnePlayer.isChecked()) {
            editText2.setVisibility(View.INVISIBLE);
        } else if (TwoPlayers.isChecked()) {
            editText2.setVisibility(View.VISIBLE);
        } */
    }
}
