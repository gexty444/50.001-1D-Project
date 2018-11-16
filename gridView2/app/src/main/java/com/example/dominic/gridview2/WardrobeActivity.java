package com.example.dominic.gridview2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class WardrobeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);

        GridView gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this)); // Image adapter to put images into WardrobeActivity
        gridview.setOnItemClickListener(this); // Make gridView clickable by user

        // floating action button NOTE: remember to put items into onCreate! previously failed to complete a toolbar perhaps because i forgot to put it here.
        // to add a floating action button, add implementation 'com.android.support:design:28.0.0' to gradle dependencies
        FloatingActionButton addItemfab = findViewById(R.id.addItemfab); // add item floating action button
        addItemfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Add new category when clicked?", Toast.LENGTH_LONG).show();
            }
        });

    }




    @Override
    public void onClick(View v) {

    }

    /*
    * This part of the code is for the image adapter needed to create the gridView
    * It actually generates and destroys imageViews as the user scrolls up and down
    * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // detect if correct gridview is clicked, in this case, we only have 1 gridview
        if(parent.getId() == R.id.gridview)
        {

            // each imageView item in gridView has a position. The numbering corresponds to the numbering in the image adapter thumbnail array(in ImageAdapter.java), starting from index 0.
            switch (position)
            {
                // each case for a different item on gridView, where case number is item index in thumbnail array (in ImageAdapter.java)
                case 4:
                {
                    // onclick, start new intent to go to another activity
                    Toast.makeText(getApplicationContext(), "This toast is for shoes!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(WardrobeActivity.this, ShoeActivity.class);
                    startActivity(intent);
                    break;
                }
                default:
                {
                    Toast.makeText(getApplicationContext(), "No action associated with this item", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }

    }
}
