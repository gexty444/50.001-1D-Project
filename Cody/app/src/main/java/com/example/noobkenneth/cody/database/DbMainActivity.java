package com.example.noobkenneth.cody.database;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noobkenneth.cody.Home.MainActivity;
import com.example.noobkenneth.cody.R;

public class DbMainActivity extends AppCompatActivity {

    private CharaDbHelper charaDbHelper;
    private SQLiteDatabase charaDb;

    public static String PACKAGE_NAME;

    public static int REQUEST_CODE_FAB = 1000;

    TextView textViewName;
    ImageView imageViewFile;
    Button buttonGetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO 7.12 get an instance of charaDbHelper
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);

        //TODO 7.13 test the methods you wrote
        TestCharaDbHelper.testQueryOneRowRandom(charaDbHelper);
        TestCharaDbHelper.testTable(charaDbHelper);
        /*TestCharaDbHelper.testInsertOneRow(charaDbHelper,
                "Alan Turing",
                "WW2 Computer Scientist",
                R.drawable.alanturing);
        TestCharaDbHelper.testInsertOneRow(charaDbHelper,
                "Miku",
                "Vocaloid",
                R.drawable.hatsunemiku); */
        /*TestCharaDbHelper.testDeleteOneRow(charaDbHelper,
                "Alan Turing");*/
        //TODO run your app and see result in the logcat

        //TODO 8.1 Get References to the widgets
        textViewName = findViewById(R.id.textViewName);
        imageViewFile = findViewById(R.id.imageViewFile);
        buttonGetImage = findViewById(R.id.buttonGetImage);


        //TODO 8.2 when the getImage button is clicked, get a random image from the SQLite Database
        buttonGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharaDbHelper.CharaData charaData
                        = charaDbHelper.queryOneRowRandom();
                textViewName.setText(charaData.getName());
                imageViewFile.setImageBitmap(charaData.getBitmap());

            }
        });

        //TODO 8.3 When the fab is clicked, launch DataEntryActivity and invoke startActivityForResult
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                Intent intent = new Intent(DbMainActivity.this,
                        DataEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FAB);

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
        if (id == R.id.action_settings) {
            return true;
        }

        //TODO 9.1 Complete this to bring users to the RecyclerView activity
        //TODO I'm in MainActivity
        if( id == R.id.go_recycler_view){
            Intent intent = new Intent(this, RecylerViewActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO 8.9 Complete on activity result to decide what happens after the data is uploaded
    //TODO 8.10 [on your own] Add a cancel button to DataEntryActivity and complete the result-cancelled portion
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_FAB){

            if(resultCode == Activity.RESULT_OK) {

                //My choice is to display a Toast that says how many rows our database has
                long numberOfRows = charaDbHelper.queryNumRows();
                Toast.makeText(DbMainActivity.this,
                        "Number of rows: " + numberOfRows,
                        Toast.LENGTH_LONG).show();
                }

                if(resultCode == Activity.RESULT_CANCELED){
                    //complete it yourself if you'd like
                }

        }


    }//onActivityResult
}
