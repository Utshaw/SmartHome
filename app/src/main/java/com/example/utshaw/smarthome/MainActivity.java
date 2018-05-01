package com.example.utshaw.smarthome;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Toolbar toolbar;
    TextView textView;
    SharedPreferences preferences;

    // Key for particular preference
    private static final String PREF_KEY_EDIT_TEXT = "pref_key_edit_text";

    private static final String PREF_KEY_LIST = "pref_key_list";

    private static final String PREF_KEY_LIST_2 = "pref_key_list_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        textView = (TextView) findViewById(R.id.mainscreen_text_view);
        setSupportActionBar(toolbar);

        //Getting a reference of the SharedPreference
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Setting Default values and "painting" the screens
        onCraetePreference();


        //Setting Event Listener
        preferences.registerOnSharedPreferenceChangeListener(this);


    }

    private void onCraetePreference()
    {
        //Sets the default values from an XML preference file by reading the values
        // defined by each Preference item's android:defaultValue attribute.
        // This should be called by the application's main activity.
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

        textView.setText(preferences.getString(PREF_KEY_EDIT_TEXT,"No Value"));

        toolbar.setBackgroundColor(Color.parseColor(preferences.getString(PREF_KEY_LIST,getResources().getString(R.string.red_color_colorPrimary))));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor(preferences.getString(PREF_KEY_LIST_2,getResources().getString(R.string.red_color_colorPrimaryDark))));
        }
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
            case PREF_KEY_EDIT_TEXT:
                textView.setText(preferences.getString(PREF_KEY_EDIT_TEXT,""));
                break;
            case PREF_KEY_LIST:
                toolbar.setBackgroundColor(Color.parseColor(preferences.getString(PREF_KEY_LIST,getResources().getString(R.string.red_color_colorPrimary))));
                Toast.makeText(this, preferences.getString(PREF_KEY_LIST,""), Toast.LENGTH_SHORT).show();
                break;
            case PREF_KEY_LIST_2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(Color.parseColor(preferences.getString(PREF_KEY_LIST_2,getResources().getString(R.string.red_color_colorPrimaryDark))));
                }
                break;

        }
    }
}
