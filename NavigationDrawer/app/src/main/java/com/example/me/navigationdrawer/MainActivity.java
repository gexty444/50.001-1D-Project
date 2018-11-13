package com.example.me.navigationdrawer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Uri fileUri;
    public String photofilename="photo.jpg";
    File photofile;
    private TextView mTextMessage;
    public final String APP_TAG = "BottomNav";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button cameraButton=findViewById(R.id.Camera);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }
    private boolean checkCameraHardware(Context context){
        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) return true; //has camera
        else return false; //no camera
    }
    /*
    If you have determined that the device on which your application is running has a camera,
    you must request to access it by getting an instance of Camera unless using an intent
     */

    //safe way to get camera:

    private void takePicture(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        photofile=getPhotoFileUri(photofilename);
        Uri fileProvider=FileProvider.getUriForFile(MainActivity.this, "com.example.fileprovider",photofile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider); //putExtra(String, parceable)
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }




    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                //photo is on disk so we need to decode it
                Bitmap imageRec=BitmapFactory.decodeFile(photofile.getAbsolutePath());
                ImageView picture= findViewById(R.id.photo);
                //decode the image and show it on the screen for double checking?
                picture.setImageBitmap(imageRec);
            }
            else{
                Toast.makeText(MainActivity.this, "no pic la dey", Toast.LENGTH_LONG).show();
            }
        }

    }
    public File getPhotoFileUri(String name){
        File devicestorage= new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);
        if(!devicestorage.exists() && !devicestorage.mkdirs()){
            Log.i(APP_TAG, "no dir lol");
        }
        File file=new File(devicestorage.getPath() + File.pathSeparator + name); //create the file name with correct path

        return file;
    }

}
