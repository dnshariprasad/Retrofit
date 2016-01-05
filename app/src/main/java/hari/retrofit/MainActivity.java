package hari.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText name_text, phone_number_text;
    private RadioGroup genderGroup;
    private Button submit;
    private CheckBox terms_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pull views
        name_text = (EditText) findViewById(R.id.name_text);
        phone_number_text = (EditText) findViewById(R.id.phone_number_text);
        genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
        submit = (Button) findViewById(R.id.submit);
        terms_cb = (CheckBox) findViewById(R.id.terms_cb);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validations
                if (name_text.getText().toString().length() == 0) {
                    name_text.setError("Name should nat be empty.");
                    return;
                }
                if (phone_number_text.getText().toString().length() == 0) {
                    phone_number_text.setError("Phone Number should nat be empty.");
                    return;
                }

                if (genderGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Please select gender.", Toast.LENGTH_LONG).show();
                    return;
                }
                //to check is checked or not
                if (!terms_cb.isChecked()) {
                    Toast.makeText(MainActivity.this, "Please agree Terms and Conditions.", Toast.LENGTH_LONG).show();
                    return;
                }

                //create Json
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("name", name_text.getText().toString());
                    jsonObject.put("phone", phone_number_text.getText().toString());

                    //ask radio group to find selected radio button
                    int selectedGender = genderGroup.getCheckedRadioButtonId();

                    //get view based on the id
                    RadioButton radioSexButton = (RadioButton) findViewById(selectedGender);

                    //get text from id
                    jsonObject.put("gender", radioSexButton.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
