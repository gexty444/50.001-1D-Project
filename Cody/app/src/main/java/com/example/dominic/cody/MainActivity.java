// package com.example.itsmine.cody;
package com.example.dominic.cody;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String[] dresscode = {"Casual", "Business Casual", "Smart Casual", "Business", "Formal"};
//    AutoCompleteTextView autocom;
    Spinner dresscodespinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        autocom = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//        //Creating the instance of ArrayAdapter containing list of dresscode names
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_expandable_list_item_1, dresscode);
//
//        autocom.setAdapter(adapter);
//        autocom.setThreshold(1); //starts working from first char
        dresscodespinner = (Spinner) findViewById(R.id.dresscodespinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,dresscode);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dresscodespinner.setAdapter(adapter);
        dresscodespinner.setOnItemSelectedListener(this);

    }



    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        // on selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // shows selected item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // nothing selected!
        Toast.makeText(parent.getContext(),getString(R.string.emptyinput), Toast.LENGTH_LONG).show();
    }

    public void saveDressCode(View view){
        // dress code selected
        String selected = dresscodespinner.getSelectedItem().toString();

        // set up SharedPreferences for dress code selected
        SharedPreferences shareddresscode = getSharedPreferences("dresscode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shareddresscode.edit();
        editor.putString("code", selected);
        editor.apply();

        // notify that the dress code has been saved
        Toast.makeText(this, "Saved: "+selected, Toast.LENGTH_LONG).show();
    }

}
