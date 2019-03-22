package com.binarycrust.greetall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by paras on 30-08-2017.
 */

public class ShareActivity extends AppCompatActivity {
    ImageView imageView;
    ImageButton whatsapp,facebook,instagram,twitter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = (ImageView) findViewById(R.id.imgfinal);
        whatsapp = (ImageButton) findViewById(R.id.whatsapp);
        instagram = (ImageButton) findViewById(R.id.instagram);
        facebook = (ImageButton) findViewById(R.id.facebook);
        twitter = (ImageButton) findViewById(R.id.twitter);
        Intent i = getIntent();
       // int pos = i.getExtras().getInt("image pos");
        setContentView(R.layout.share_layout);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent share = new Intent(Intent.ACTION_SEND);
                share.setPackage("com.whatsapp");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.setType("image*//**//*");
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(Intent.createChooser(share, "Share image File"));*/
                Toast.makeText(getApplicationContext(), "Whatsapp", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
