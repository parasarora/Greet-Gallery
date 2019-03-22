package com.binarycrust.greetall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by paras on 10-07-2017.
 */

public class FormaterActivity extends AppCompatActivity {

    private Context context;
    EditText popupMainText;
    SeekBar seekBar;
    Button font, color, submit;
    public int randomNum, minimum, maximum;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        String s = i.getExtras().getString("greet message");
        setContentView(R.layout.popup_layout);
        popupMainText = (EditText) findViewById(R.id.popupMainText);
        String string1="Type a message";
        if(!string1.equals(s)){
            popupMainText.setText(s);
        }else{
            popupMainText.setText("");
            popupMainText.setHint("Type a message");
        }

        float fSize = i.getExtras().getFloat("text size");
        int fStyle = i.getExtras().getInt("text style");
        int fColor = i.getExtras().getInt("text color");
        popupMainText.setBackgroundResource(android.R.color.transparent);



        if(fColor != 0 ){
            popupMainText.setTextColor(fColor);
        }

        popupMainText.setTextSize(TypedValue.COMPLEX_UNIT_SP,fSize);
        //popupMainText.setTypeface(Typeface.create(fStyle, Typeface.NORMAL));
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        font= (Button) findViewById(R.id.font1);

        color= (Button) findViewById(R.id.colorPink);
        //color.setBackgroundResource(android.R.color.transparent);

        submit= (Button) findViewById(R.id.submit);

        final int height = popupMainText.getMeasuredHeight();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                popupMainText.setTextSize(progress);

                popupMainText.setHeight(height);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
       color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface face ;
                int random = random();


                switch (randomNum){
                    case 1:
                        popupMainText.setTextColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case 2:
                        popupMainText.setTextColor(getResources().getColor(R.color.redText));
                        break;
                    case 3:
                        popupMainText.setTextColor(getResources().getColor(R.color.whiteText));
                        break;
                    case 4:
                        popupMainText.setTextColor(getResources().getColor(R.color.blackText));
                        break;
                    case 5:
                        popupMainText.setTextColor(getResources().getColor(R.color.blueText));
                        break;
                    case 6:
                        popupMainText.setTextColor(getResources().getColor(R.color.greenText));
                        break;
                    case 7:
                        popupMainText.setTextColor(getResources().getColor(R.color.orangeText));
                        break;
                    case 8:
                        popupMainText.setTextColor(getResources().getColor(R.color.violetText));
                        break;
                    case 9:
                        popupMainText.setTextColor(getResources().getColor(R.color.yellowText));
                        break;
                    default:
                        break;
                }
            }
        });

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = random();

                // Typeface face ;		        popupMainText.setTextColor(getResources().getColor(fColor));
                Typeface custom_font;

               // Toast.makeText(getApplicationContext(),random+"",Toast.LENGTH_SHORT).show();
                switch (randomNum){
                //switch (3){
                    case 1:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/AbrilFatface-Regular.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 2:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Bold.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 3:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/GloriaHallelujah.ttf");
                        font.setTypeface(custom_font);
                        font.setTextSize(22);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 4:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Regular.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 5:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/IndieFlower.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 6:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/LibreBaskerville-Italic.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 7:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 8:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
                        font.setTypeface(custom_font);
                        font.setTextSize(25);
                        popupMainText.setTypeface(custom_font);
                        break;
                    case 9:
                        custom_font = Typeface.createFromAsset(getAssets(), "fonts/BalooBhaijaan-Regular.ttf");
                        font.setTypeface(custom_font);
                        popupMainText.setTypeface(custom_font);
                        break;
                    default:
                        break;
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent myIntent = new Intent(FormaterActivity.this, SingleViewActivity.class);
                /**/
              // FormaterActivity.this.startActivity(myIntent);

                Object c;
                Intent intent = new Intent();
                String fontFamily = String.valueOf(popupMainText.getTypeface());
                intent.putExtra("greet message",popupMainText.getText().toString());
                int color=popupMainText.getCurrentTextColor();
                float px = popupMainText.getTextSize();
                popupMainText.getTypeface();
                    intent.putExtra("fontFamily",fontFamily);
                    intent.putExtra("color",color);
                    intent.putExtra("size",px);
                    setResult(RESULT_OK, intent);
                    finish();


                /*if(popupMainText.getText().toString()!= null){
                    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
                    float size = px/scaledDensity;
                    intent.putExtra("fontedit","success");
                    intent.putExtra("text size",size);
                    intent.putExtra("text style",String.valueOf(popupMainText.getTypeface().getStyle()));
                    intent.putExtra("text style2",String.valueOf(popupMainText.getTypeface()));
                    intent.putExtra("text color",popupMainText.getCurrentTextColor());
                }*/
                //intent.putExtra("keyName", stringToPassBack);
               // setResult(RESULT_OK, intent);
              //  finish();

            }
        });

    }
    //private FormaterActivity(Context c){ context = c;  }
    public int random(){
        minimum = 1; maximum = 9;
        randomNum = minimum + (int)(Math.random() * maximum);
        return randomNum;
    }

}
