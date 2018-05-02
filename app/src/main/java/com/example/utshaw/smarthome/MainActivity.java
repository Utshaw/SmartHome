package com.example.utshaw.smarthome;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    final Context context = this;

    Toolbar toolbar;
    TextView textView;
    SharedPreferences preferences;

    // Key for particular preference


    private static final String PREF_KEY_LIGHTS = "pref_key_ligts";


    private static final String PREF_KEY_FANS = "pref_key_fans";

    LinearLayout lightLayout1;
    LinearLayout lightLayout2;
    LinearLayout lightLayout3;
    LinearLayout lightLayout4;
    LinearLayout lightLayout5;

    Switch lightSwitch1;
    Switch lightSwitch2;
    Switch lightSwitch3;
    Switch lightSwitch4;
    Switch lightSwitch5;

    Switch fanSwitch1;
    Switch fanSwitch2;
    Switch fanSwitch3;
    Switch fanSwitch4;
    Switch fanSwitch5;

    SeekBar lightSeek1;
    SeekBar lightSeek2;
    SeekBar lightSeek3;
    SeekBar lightSeek4;
    SeekBar lightSeek5;


    Button lightCancel1;
    Button lightCancel2;
    Button lightCancel3;
    Button lightCancel4;
    Button lightCancel5;

    String serverAdress;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        serverAdress = getIntent().getExtras().getString("IPAddress");

        lightLayout1 = findViewById(R.id.light_1);
        lightLayout2 = findViewById(R.id.light_2);
        lightLayout3 = findViewById(R.id.light_3);
        lightLayout4 = findViewById(R.id.light_4);
        lightLayout5 = findViewById(R.id.light_5);
        lightLayout5 = findViewById(R.id.light_5);

        lightSwitch1 = findViewById(R.id.light_btn1);
        lightSwitch2 = findViewById(R.id.light_btn2);
        lightSwitch3 = findViewById(R.id.light_btn3);
        lightSwitch4 = findViewById(R.id.light_btn4);
        lightSwitch5 = findViewById(R.id.light_btn5);


        lightSeek1 = findViewById(R.id.light_seek1);
        lightSeek2 = findViewById(R.id.light_seek2);
        lightSeek3 = findViewById(R.id.light_seek3);
        lightSeek4 = findViewById(R.id.light_seek4);
        lightSeek5 = findViewById(R.id.light_seek5);

        lightCancel1 = findViewById(R.id.light_c1);
        lightCancel2 = findViewById(R.id.light_c2);
        lightCancel3 = findViewById(R.id.light_c3);
        lightCancel4 = findViewById(R.id.light_c4);
        lightCancel5 = findViewById(R.id.light_c5);


        fanSwitch1 = findViewById(R.id.fan_btn1);
        fanSwitch2 = findViewById(R.id.fan_btn2);
        fanSwitch3 = findViewById(R.id.fan_btn3);
        fanSwitch4 = findViewById(R.id.fan_btn4);
        fanSwitch5 = findViewById(R.id.fan_btn5);

        handleLightOn();
        handleLightCancel();

        //Getting a reference of the SharedPreference
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Setting Default values and "painting" the screens
        onCraetePreference();


        //Setting Event Listener
        preferences.registerOnSharedPreferenceChangeListener(this);

    }




    private void handleLightCancel(){
        lightCancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightLayout1.setVisibility(View.GONE);
            }
        });

        lightCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightLayout2.setVisibility(View.GONE);
            }
        });

        lightCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightLayout3.setVisibility(View.GONE);
            }
        });

        lightCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightLayout4.setVisibility(View.GONE);
            }
        });

        lightCancel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightLayout5.setVisibility(View.GONE);
            }
        });
    }





    private void handleLightOn(){
        lightSwitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ledStatus;
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                if(lightSwitch1.isChecked()){
                    ledStatus = "1";
                }else{
                    ledStatus = "0";
                }
                requestTask.execute(ledStatus);



            }
        });

        lightSwitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        lightSwitch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        lightSwitch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        lightSwitch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void onCraetePreference()
    {
        //Sets the default values from an XML preference file by reading the values
        // defined by each Preference item's android:defaultValue attribute.
        // This should be called by the application's main activity.
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        switch (s)
        {
            case PREF_KEY_LIGHTS:
                int countLight = Integer.parseInt(preferences.getString(PREF_KEY_LIGHTS,"5"));
                switch (countLight){
                    case 1:
                        lightLayout1.setVisibility(View.VISIBLE);
                        lightLayout2.setVisibility(View.GONE);
                        lightLayout3.setVisibility(View.GONE);
                        lightLayout4.setVisibility(View.GONE);
                        lightLayout5.setVisibility(View.GONE);
                        break;
                    case 2:
                        lightLayout1.setVisibility(View.VISIBLE);
                        lightLayout2.setVisibility(View.VISIBLE);
                        lightLayout3.setVisibility(View.GONE);
                        lightLayout4.setVisibility(View.GONE);
                        lightLayout5.setVisibility(View.GONE);
                        break;
                    case 3:
                        lightLayout1.setVisibility(View.VISIBLE);
                        lightLayout2.setVisibility(View.VISIBLE);
                        lightLayout3.setVisibility(View.VISIBLE);
                        lightLayout4.setVisibility(View.GONE);
                        lightLayout5.setVisibility(View.GONE);
                        break;
                    case 4:
                        lightLayout1.setVisibility(View.VISIBLE);
                        lightLayout2.setVisibility(View.VISIBLE);
                        lightLayout3.setVisibility(View.VISIBLE);
                        lightLayout4.setVisibility(View.VISIBLE);
                        lightLayout5.setVisibility(View.GONE);
                        break;
                    case 5:
                        lightLayout1.setVisibility(View.VISIBLE);
                        lightLayout2.setVisibility(View.VISIBLE);
                        lightLayout3.setVisibility(View.VISIBLE);
                        lightLayout4.setVisibility(View.VISIBLE);
                        lightLayout5.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case PREF_KEY_FANS:
                int countFans = Integer.parseInt(preferences.getString(PREF_KEY_FANS,"5"));
                break;

        }
    }


    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        private String serverAdress;
        private String serverResponse = "";
        private AlertDialog dialog;

        public HttpRequestTask(String serverAdress) {
            this.serverAdress = serverAdress;

            dialog = new AlertDialog.Builder(context)
                    .setTitle("HTTP Response from Ip Address:")
                    .setCancelable(true)
                    .create();
        }

        @Override
        protected String doInBackground(String... params) {
            dialog.setMessage("Data sent , waiting response from server...");

            if (!dialog.isShowing())
                dialog.show();

            String val = params[0];
            final String url = "http://" + serverAdress + "/led/" + val;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);

                InputStream inputStream = null;
                inputStream = response.getEntity().getContent();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));

                serverResponse = bufferedReader.readLine();
                inputStream.close();

            } catch (URISyntaxException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.setMessage(serverResponse);

            if (!dialog.isShowing())
                dialog.show();
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Sending data to server, please wait...");

            if (!dialog.isShowing())
                dialog.show();
        }
    }


}
