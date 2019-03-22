package com.binarycrust.greetall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by paras on 21-06-2017.
 */

public class GridActivity extends AppCompatActivity {

    protected Integer[] mThumbIds;



    public Integer[] birthday = {
            R.drawable.b1,R.drawable.b2,R.drawable.b3,
            R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9,
            R.drawable.b10, R.drawable.b11, R.drawable.b12,
    };
    public Integer[] anniversary = {
            R.drawable.b1,R.drawable.b2,R.drawable.b3,
            R.drawable.b10, R.drawable.b11, R.drawable.b12,
    };
    public Integer[] everyday = {
            R.drawable.b1,R.drawable.b2,R.drawable.b3,
            R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b12,
    };
    public Integer[] festival = {
            R.drawable.b1,R.drawable.b2,R.drawable.b3,
            R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b12,
    };

    public Integer[] occation = {
            R.drawable.b1,R.drawable.b2,R.drawable.b3,
            R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8,  R.drawable.b12,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_grid);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        String i = getIntent().getExtras().getString("EXTRA_SESSION_ID");

        if (i.equals("birthday")) {
            this.mThumbIds = birthday;
            //Toast.makeText(getApplicationContext(),"birthday",Toast.LENGTH_SHORT).show();

        } else if (i.equals("anniversary")) {
            //Toast.makeText(getApplicationContext(),"anniversary",Toast.LENGTH_SHORT).show();
           // Log.i("this is my array", "arr: " + Arrays.toString(mThumbIds));
            this.mThumbIds = anniversary;
        } else if (i.equals("everyday")) {
            //Toast.makeText(getApplicationContext(),"everyday",Toast.LENGTH_SHORT).show();
            this.mThumbIds = everyday;
        } else if (i.equals("festival")) {
            //Toast.makeText(getApplicationContext(),"festival",Toast.LENGTH_SHORT).show();
            this.mThumbIds = festival;
        } else if (i.equals("occation")) {
            //Toast.makeText(getApplicationContext(),"occation",Toast.LENGTH_SHORT).show();
            this.mThumbIds = occation;
        } else {
            //Toast.makeText(getApplicationContext(),"default",Toast.LENGTH_SHORT).show();
            this. mThumbIds = occation;
        }
        gridview.setAdapter(new ImageAdapter(this, i));


        /**
         * On Click event for Single Gridview Item
         * */

        /*gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), SingleViewActivity.class);
                // passing array index
                String j = getIntent().getExtras().getString("EXTRA_SESSION_ID");
                i.putExtra("id", position);

                i.putExtra("str", j);
                Toast.makeText(getApplicationContext(),j,Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });*/

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id){
                // Send intent to SingleViewActivity
                String j = getIntent().getExtras().getString("EXTRA_SESSION_ID");
                // Toast.makeText(getApplicationContext(),j,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), SingleViewActivity.class);
                // Pass image index

                i.putExtra("id", position);
                Integer[] mThumbIds1;
                if (j.equals("birthday")) {
                    mThumbIds1 = birthday;
                } else if (j.equals("anniversary")) {
                    mThumbIds1 = anniversary;
                } else if (j.equals("everyday")) {
                    mThumbIds1 = everyday;
                } else if (j.equals("festival")) {
                    mThumbIds1 = festival;
                } else if (j.equals("occation")) {
                    mThumbIds1 = occation;
                } else {
                    mThumbIds1 = occation;
                }
                //
                // Toast.makeText(getApplicationContext(),""+mThumbIds[position],Toast.LENGTH_SHORT).show();
                i.putExtra("Image Int", mThumbIds1[position]);
                //i.putExtra("Image array", mThumbIds);
                startActivity(i);
            }
        });
    }

}
