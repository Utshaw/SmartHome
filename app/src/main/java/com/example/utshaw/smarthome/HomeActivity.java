package com.example.utshaw.smarthome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HomeActivity extends AppCompatActivity {

    EditText ipAddress;

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ipAddress = findViewById(R.id.edit_text);


        findViewById(R.id.enter_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ipAddress.getText().toString().equals(""))
                    Toast.makeText(HomeActivity.this, "Please enter the ip address...", Toast.LENGTH_SHORT).show();
                else{
                    String serverAdress = ipAddress.getText().toString() + ":" + "80";

                    Intent i = new Intent(HomeActivity.this, MainActivity.class);
                    i.putExtra("IPAddress", serverAdress);
                    startActivity(i);

                }

            }
        });
    }



}
