package com.binarycrust.greetall;

/**
 * Created by paras on 20-06-2017.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SingleViewActivity extends AppCompatActivity {

    private RelativeLayout fabContainer; Point p;
    private boolean fabMenuOpen = false;
    private boolean fabSocialOpen = false;
    public int imagepos;
    FloatingActionButton fab,fab1,fab3,fab2;
    TextView viewGreet, popupMainText;
    EditText editGreet , result;
    Button setText;
    final Context context = this;

    private android.widget.RelativeLayout.LayoutParams layoutParams4;

    private View mRootView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            String s = data.getStringExtra("greet message");
            float size = data.getExtras().getFloat("size");
            int color =data.getExtras().getInt("color");
            viewGreet.setText(s);
            float sp = size / getResources().getDisplayMetrics().scaledDensity;
            viewGreet.setTextColor(color);
            viewGreet.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);



    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);

        // Get intent data
        Intent i = getIntent();
        // Selected image id
        int position = i.getExtras().getInt("id");
        String s = i.getExtras().getString("EXTRA_SESSION_ID");
        String str = i.getExtras().getString("str");
        Integer pos = i.getExtras().getInt("Image Int");
        imagepos = pos;

       // ImageAdapter imageAdapter = new ImageAdapter(this,"str");
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
        final ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageResource(pos);

        //imageView.setImageResource(imageAdapter.mThumbIds[position]);

        mRootView = (View) findViewById(R.id.SingleActivityView);

        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);

        viewGreet = (TextView) findViewById(R.id.viewGreet) ;
        popupMainText = (TextView) findViewById(R.id.popupMainText);


        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/IndieFlower.ttf");
        viewGreet.setTypeface(custom_font);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMenu();

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        viewGreet.setText(userInput.getText());
                                        viewGreet.setVisibility(View.VISIBLE);
                                        //editGreet.setVisibility(View.INVISIBLE);
                                        //setText.setVisibility(View.INVISIBLE);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                //editGreet.setVisibility(View.VISIBLE);
                //setText.setVisibility(View.VISIBLE);
                viewGreet.setVisibility(View.INVISIBLE);
            }
        });

        /*setText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGreet.setText(editGreet.getText().toString());
                viewGreet.setVisibility(View.VISIBLE);
                editGreet.setVisibility(View.INVISIBLE);
                setText.setVisibility(View.INVISIBLE);

            }
        });*/

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMenu();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleViewActivity.this, FormaterActivity.class);
                if(viewGreet.getText().toString()!= null){
                    intent.putExtra("greet message",viewGreet.getText().toString());
                    float px = viewGreet.getTextSize();
                    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
                    float size = px/scaledDensity;
                    intent.putExtra("text size",size);
                    intent.putExtra("text style",String.valueOf(viewGreet.getTypeface().getStyle()));
                    intent.putExtra("text style2",String.valueOf(viewGreet.getTypeface()));
                    intent.putExtra("text color",viewGreet.getCurrentTextColor());


                }
                //startActivityForResult(intent,1);
                SingleViewActivity.this.startActivityForResult(intent,1);

            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Handler handler1 = new Handler();
                    toggleFabMenu();
                    for (int a = 1; a<2 ;a++) {
                        handler1.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                saveAndShare(mRootView.getRootView());
                            }
                        }, 1000 * a);



                    // imageView.buildDrawingCache();
                    //Bitmap image= imageView.getDrawingCache();


                    //newIntent.putExtra("image pos",imagepos);
                    //SingleViewActivity.this.startActivity(newIntent);

                }


            }
        });

        viewGreet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //ClipData data = ClipData.newPlainText(viewGreet.getText(),viewGreet.getText());
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(viewGreet);
                    viewGreet.startDrag(null,shadowBuilder,viewGreet,1);

                    return true;
                }
                return false;
            }
        });

        //https://stackoverflow.com/questions/45132190/android-i-want-to-change-textview-position-while-dragging/45151007#45151007
        viewGreet.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                final int action = event.getAction();
                switch(action) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;

                    case DragEvent.ACTION_DROP:{
                        return(true);
                    }

                    case DragEvent.ACTION_DRAG_ENDED:{

                        int x = (int)event.getX();
                        int y = (int)event.getY();

                       // Toast.makeText(context, x+" x" , Toast.LENGTH_SHORT).show();
                       // Toast.makeText(context, y+" y" , Toast.LENGTH_SHORT).show();
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(x, y, 0, 0);
                        viewGreet.setLayoutParams(layoutParams);
                        return(true);

                    }

                    default:
                        break;
                }

                return true;
            }



        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toggleFabMenu(){
        if (!fabMenuOpen) {
            Animation show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);

            FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams1.rightMargin += (int) (fab1.getWidth() * 1.7);
            layoutParams1.bottomMargin += (int) (fab1.getHeight() * 0.25);
            fab1.setLayoutParams(layoutParams1);
            fab1.startAnimation(show_fab_1);
            fab1.setClickable(true);

            Animation show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);

            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();

            layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
            layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
            fab2.setLayoutParams(layoutParams2);
            fab2.startAnimation(show_fab_2);
            fab2.setClickable(true);

            Animation show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);

            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
            layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
            layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);

            fab3.setLayoutParams(layoutParams3);
            fab3.startAnimation(show_fab_3);
            fab3.setClickable(true);

        }else{
            Animation hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);

            FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
            layoutParams1.rightMargin -= (int) (fab1.getWidth() * 1.7);
            layoutParams1.bottomMargin -= (int) (fab1.getHeight() * 0.25);
            fab1.setLayoutParams(layoutParams1);
            fab1.startAnimation(hide_fab_1);
            fab1.setClickable(false);

            Animation hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);

            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
            layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
            layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
            fab2.setLayoutParams(layoutParams2);
            fab2.startAnimation(hide_fab_2);
            fab2.setClickable(false);

            Animation hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);

            FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
            layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
            layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
            fab3.setLayoutParams(layoutParams3);
            fab3.startAnimation(hide_fab_3);
            fab3.setClickable(false);

        }
        fabMenuOpen=!fabMenuOpen;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab_2);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    private void saveAndShare(View mRootView){

        TextView advert =  (TextView) findViewById(R.id.advert);
        advert.setVisibility(View.VISIBLE);

        Date now = new Date();
        android.text.format.DateFormat.format("yyyyMMddhhmmss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "now" + ".jpg";
            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {

            }
            fab.setVisibility(View.INVISIBLE);

            //Create a directory for your PDF
            File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "GreetGallery");
            if (!pdfDir.exists()){
                pdfDir.mkdir();
            }

            // create bitmap screen capture
            RelativeLayout ll;

            ll = (RelativeLayout) findViewById(R.id.SingleActivityView);
            View v1 = getWindow().getDecorView().getRootView();
            ll.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(ll.getDrawingCache());
            ll.setDrawingCacheEnabled(false);

            //File imageFile = new File(mPath);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat d = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            File imageFile = new File(pdfDir, "myimg"+d.format(c.getTime())+".jpg");

            final Uri uri = Uri.parse("file://"+imageFile.getAbsolutePath());

            String fileName = imageFile.getAbsolutePath();
            /*Uri uri = Uri.fromFile(imageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, PICTURE_REQUEST_CODE);*/
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            Intent share = new Intent(Intent.ACTION_SEND);
            // share.setPackage("com.instagram.android");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.putExtra(Intent.EXTRA_TITLE, "Greet Gallery App");
            share.setType("image/*");
            // startActivity(share);
            //share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(share, "Share image File"));
            //toggleFabMenu();
            //https://www.mkyong.com/android/android-custom-dialog-example/
            /*final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.social_layout);
            dialog.setTitle("Perview");
            ImageView image = (ImageView) dialog.findViewById(R.id.image);
            image.setImageURI(uri);

            ImageButton whatsapp = (ImageButton) dialog.findViewById(R.id.whatsapp);
            whatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setPackage("com.whatsapp");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                share.setType("image*//**//*");
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(Intent.createChooser(share, "Share image File"));
                    toggleFabMenu();
                    //Toast.makeText(getApplicationContext(), "Whatsapp", Toast.LENGTH_SHORT).show();
                }
            });

            ImageButton instagram = (ImageButton) dialog.findViewById(R.id.instagram);
            instagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent share = new Intent(Intent.ACTION_SEND);
                   // share.setPackage("com.instagram.android");
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_TITLE, "YOUR TEXT HERE");
                    share.setType("image/*");
                   // startActivity(share);
                    //share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(Intent.createChooser(share, "Share image File"));
                    toggleFabMenu();
                    //Toast.makeText(getApplicationContext(), "Whatsapp", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
            openScreenshot(imageFile);*/


            /*Intent share = new Intent(Intent.ACTION_SEND);
            share.setPackage("com.whatsapp");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.setType("image*//*");
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(share, "Share image File"));*/
            fab.setVisibility(View.VISIBLE);
            advert.setVisibility(View.INVISIBLE);
            //fab2.setVisibility(View.VISIBLE);
            //fab3.setVisibility(View.VISIBLE);
            //fab1.setVisibility(View.VISIBLE);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }
    private void showPopup(final Activity context, Point p) {
        toggleFabMenu();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        SingleViewActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int popupWidth = (int) ((int)displaymetrics.widthPixels * 0.95);
        int popupHeight = (int) ((int)displaymetrics.heightPixels * 0.95);
       /* int popupWidth = 200;
        int popupHeight = 150;*/

        // Inflate the popup_layout.xml
        RelativeLayout viewGroup = (RelativeLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);
        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
       /* int OFFSET_X = -230;
        int OFFSET_Y = -230;
*/
        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());


        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.TOP|Gravity.START, 30,30);
        // Getting a reference to Close button, and close the popup when clicked.


    }

    private static void addImage(Document document,byte[] byteArray)
    {
        Image image = null;
        try
        {
            image = Image.getInstance(byteArray);
        }
        catch (BadElementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try
        {
            document.add(image);
        } catch (DocumentException e) {
            //  TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

}