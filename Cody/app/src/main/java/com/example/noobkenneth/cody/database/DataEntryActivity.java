package com.example.noobkenneth.cody.database;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.example.noobkenneth.cody.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class DataEntryActivity extends AppCompatActivity implements OnItemSelectedListener {

    ImageView imageViewSelected;
    CharaDbHelper charaDbHelper;
    Bitmap bitmapSelected = null;
    SQLiteDatabase db;
    int REQUEST_CODE_IMAGE = 2000;
    String category = null;
    String formality = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //TODO 8.4 Get a reference to the CharaDbHelper
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);

        //Category spinner stuff here
        final Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        final Spinner spinnerFormality = findViewById(R.id.spinnerFormality);
        spinnerCategory.setOnItemSelectedListener(this);
        spinnerFormality.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>(Arrays.asList("Tops", "Bottoms", "One-piece", "Shoes", "Bags", "Accessories")){};
        List<String> formalities = new ArrayList<String>(Arrays.asList("Casual", "Smart Casual", "Business Formal", "Formal")){};
        ArrayAdapter<String> dataAdapterCategories = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapterFormality = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, formalities);
        dataAdapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterFormality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(dataAdapterCategories);
        spinnerFormality.setAdapter(dataAdapterFormality);

        //TODO 8.5 Get references to the widgets
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);

        //TODO 8.6 when the selectImage button is clicked, set up an Implicit Intent to the gallery
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        //TODO 8.8 when the OK button is clicked, add the data to the db
        Button buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String last_used = formatter.format(date);
                boolean ootd = false;

                if( bitmapSelected == null){
                    Toast.makeText(DataEntryActivity.this,
                            "no image selected",
                            Toast.LENGTH_LONG).show();
                }else{

                    CharaDbHelper.CharaData charaData
                            = new CharaDbHelper.CharaData(category, formality, last_used, ootd, bitmapSelected);

                    charaDbHelper.insertOneRow( charaData );

                    Toast.makeText(DataEntryActivity.this,
                            "inserting to database",
                            Toast.LENGTH_LONG).show();

                    //In MainActivity we started DataEntryActivity using startActivityForResult
                    //Hence,DataEntryActivity must return a result and hence this set of code
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // Toast selected category

        switch (parent.getId()) {
            case R.id.spinnerCategory:
                category = item;
                Toast.makeText(parent.getContext(), "Category: " + item, Toast.LENGTH_LONG).show();
                break;
            case R.id.spinnerFormality:
                formality = item;
                Toast.makeText(parent.getContext(), "Formality: " + item, Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    //TODO 8.7 Complete OnActivityResult so that the selected image is displayed in the imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_CODE_IMAGE
                && resultCode == Activity.RESULT_OK){
            //do stuff here - recipe code follows, don't fret ..
            try{
                Uri uri = data.getData();
                InputStream inputStream = this.getContentResolver()
                        .openInputStream(uri);
                bitmapSelected = Utils.convertStreamToBitmap(inputStream);
                imageViewSelected.setImageBitmap(bitmapSelected);
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }

        }
    }
}
