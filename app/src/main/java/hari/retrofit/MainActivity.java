package hari.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private EditText name_text, phone_number_text;
    private RadioGroup genderGroup;
    private Button submit;
    private CheckBox terms_cb;
    private JSONObject infoObject;

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
                infoObject = new JSONObject();

                try {

                    infoObject.put("name", name_text.getText().toString());
                    infoObject.put("phone", phone_number_text.getText().toString());

                    //ask radio group to find selected radio button
                    int selectedGender = genderGroup.getCheckedRadioButtonId();

                    //get view based on the id
                    RadioButton radioSexButton = (RadioButton) findViewById(selectedGender);

                    //get text from id
                    infoObject.put("gender", radioSexButton.getText());

                    //json string to bytes
                    byte[] data = infoObject.toString().getBytes("UTF-8");

                    //bytes to base64
                    String base64String = Base64.encodeToString(data, Base64.DEFAULT);

                    //add internet permissions to manifest

                    //add retrofit,gson dependency to build.gradle

                    //create retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://ip-api.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    //create service
                    IpApiService ipApiService = retrofit.create(IpApiService.class);

                    //enqueue call
                    ipApiService.getLocationInfor().enqueue(new Callback<IpApiResponseModel>() {

                        @Override
                        public void onResponse(Response<IpApiResponseModel> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                IpApiResponseModel ipApiResponseModel = response.body();

                                JSONObject geo = new JSONObject();
                                try {
                                    geo.put("lat", ipApiResponseModel.getLat());
                                    geo.put("lon", ipApiResponseModel.getLon());

                                    //add geo object to infoJson
                                    infoObject.put("geo", geo);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
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
