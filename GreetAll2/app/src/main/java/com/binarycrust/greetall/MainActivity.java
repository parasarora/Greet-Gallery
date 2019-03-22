package com.binarycrust.greetall;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnBrowse;FloatingActionButton fab;
    //Our button
    private Button buttonRequestPermission, birthday, anniversary, festive , daily, occasion;

    //Permision code that will be checked in the method onRequestPermissionsResult
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing button
        buttonRequestPermission = (Button) findViewById(R.id.buttonRequestPermission);

        birthday = (Button) findViewById(R.id.birthdayBtn);
        anniversary = (Button) findViewById(R.id.anniversaryBtn);
        festive = (Button) findViewById(R.id.festiveBtn);
        daily = (Button) findViewById(R.id.everydayBtn);
        occasion = (Button) findViewById(R.id.otherEventsBtn);



        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isReadStorageAllowed()){
                    Intent intent = new Intent(MainActivity.this, GridActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID","birthday");
                    MainActivity.this.startActivity(intent);
                }else {
                    buttonRequestPermission.performClick();
                }
            }
        });

        anniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isReadStorageAllowed()){
                    Intent intent = new Intent(MainActivity.this, GridActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID","anniversary");
                    MainActivity.this.startActivity(intent);
                }else {
                    buttonRequestPermission.performClick();
                }
            }
        });

        festive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isReadStorageAllowed()){
                    Intent intent = new Intent(MainActivity.this, GridActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID","festival");
                    MainActivity.this.startActivity(intent);
                }else {
                    buttonRequestPermission.performClick();
                }
            }
        });

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isReadStorageAllowed()){
                    Intent intent = new Intent(MainActivity.this, GridActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID","everyday");
                    MainActivity.this.startActivity(intent);
                }else {
                    buttonRequestPermission.performClick();
                }
            }
        });

        occasion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isReadStorageAllowed()){
                    Intent intent = new Intent(MainActivity.this, GridActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID", "occation");
                    MainActivity.this.startActivity(intent);

                }else {
                    buttonRequestPermission.performClick();
                }
            }
        });
        //Adding a click listener
        buttonRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //First checking if the app is already having the permission
                if(isReadStorageAllowed()){
                    //If permission is already having then showing the toast
                    //Toast.makeText(MainActivity.this,"You already have the permission", Toast.LENGTH_LONG).show();
                    //Existing the method with return
                    return;
                }

                //If the app has not the permission then asking for the permission
                requestStoragePermission();
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int resultRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int resultWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        //If permission is granted returning true
        if (resultRead == PackageManager.PERMISSION_GRANTED && resultWrite == PackageManager.PERMISSION_GRANTED )
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "No Permissions" , Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                //Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                //Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }


    }
}
