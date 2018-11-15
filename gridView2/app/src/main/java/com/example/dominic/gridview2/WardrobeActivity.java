package com.example.dominic.gridview2;

import android.content.Intent;
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
        gridview.setAdapter(new ImageAdapter(this)); // without this, there was no images on this activity
        gridview.setOnItemClickListener(this);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // detect if correct gridview is clicked, in this case, we only have 1 gridview
        if(parent.getId() == R.id.gridview)
        {
            switch (position)
            {
//                case 0:
//                {
//                      // go to particular activity
//                }
                case 4:
                {
                    Toast.makeText(getApplicationContext(), "This toast is for shoes!!", Toast.LENGTH_LONG).show();
                    // Switch intent to next activity
                    Intent intent = new Intent(WardrobeActivity.this, ShoeActivity.class);
                    startActivity(intent);
                    break;
                }
                default:{
                    Toast.makeText(getApplicationContext(), "No action associated with this item", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }

    }
}
