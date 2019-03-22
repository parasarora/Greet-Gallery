package com.binarycrust.greetall;

/**
 * Created by paras on 20-06-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    protected Integer[] mThumbIds;

    public Integer[] birthday = {
            R.drawable.b1,R.drawable.b2,R.drawable.b3,
            R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9,
            R.drawable.b10, R.drawable.b11, R.drawable.b12,
    };
    public Integer[] anniversary = {
            R.drawable.al1,R.drawable.al2,R.drawable.al3,
            R.drawable.al4, R.drawable.al5, R.drawable.al6,
            R.drawable.al7, R.drawable.al8,
    };
    public Integer[] everyday = {
            R.drawable.g1,R.drawable.g2,R.drawable.g3,

    };
    public Integer[] festival = {
            R.drawable.f1,R.drawable.f2,R.drawable.f3,
            R.drawable.f4, R.drawable.f5,
    };

    public Integer[] occation = {
            R.drawable.g10, R.drawable.g11, R.drawable.g12,
            R.drawable.g20, R.drawable.g21, R.drawable.g22,
            R.drawable.g23, R.drawable.g24,
    };

    //Intent intent2 = ((Activity) mContext).getIntent();

    //String first = intent2.getStringExtra("EXTRA_SESSION_ID");
    // Constructor
    public ImageAdapter(Context c, String s) {

        mContext = c;
        if (s.equals("birthday")) {
            this.mThumbIds = birthday;
        } else if (s.equals("anniversary")) {
            this.mThumbIds = anniversary;
        } else if (s.equals("everyday")) {
            this.mThumbIds = everyday;
        } else if (s.equals("festival")) {
            this.mThumbIds = festival;
        } else if (s.equals("occation")) {
            this.mThumbIds = occation;
        } else {
            this. mThumbIds = occation;
        }

    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        /*if (convertView == null) {*/
        imageView = new ImageView(mContext);
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;


        //Toast.makeText(mContext,""+width,Toast.LENGTH_SHORT).show();

        int x= (int)width/3;
        imageView.setLayoutParams(new GridView.LayoutParams(x, x));
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        imageView.setPadding( 2 , 2 , 2 , 2 );
        /*}
        else
        {
            imageView = (ImageView) convertView;
        }*/
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    // Keep all Images in array

}