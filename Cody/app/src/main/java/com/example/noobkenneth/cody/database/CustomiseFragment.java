package com.example.noobkenneth.cody.database;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.noobkenneth.cody.Customise.Screenshot;
import com.example.noobkenneth.cody.Customise.Screenshotpage;
import com.example.noobkenneth.cody.R;
import com.example.noobkenneth.cody.database.CharaDbHelper;
import com.example.noobkenneth.cody.database.CustomiseActivity;
import com.example.noobkenneth.cody.database.RecommendationsActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomiseFragment extends Fragment {

    ImageButton imagebutton_cus1;
    ImageButton imagebutton_cus2;
    ImageButton imagebutton_cus3;
    ImageButton imagebutton_cus4;
    ImageButton imagebutton_cus5;
    ImageButton imagebutton_cus6;

    Button button_addtofavourites;

    //    Button button_top;
//    ImageView image_top;
//    Button button_bottom;
//    ImageView image_bottom;
//    Button button_shoes;
//    ImageView image_shoes;
//    Button button_acc;
//    ImageView image_acc;
//    Button button_bag;
//    ImageView image_bag;
    GridLayout layoutcustomise;
    View view;

    Button button_capture;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customise_fragment, container, false);

        imagebutton_cus1 = (ImageButton) view.findViewById(R.id.imagebutton_cus1);
        imagebutton_cus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked cus1", Toast.LENGTH_LONG).show();
//                imagebutton_cus1.setImageResource(R.drawable.example_top);
                imagebutton_cus1.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Tops'").getBitmap());
            }
        });


        imagebutton_cus2 = (ImageButton) view.findViewById(R.id.imagebutton_cus2);
        imagebutton_cus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked cus2", Toast.LENGTH_LONG).show();
//                imagebutton_cus2.setImageResource(R.drawable.example_bag);
                imagebutton_cus2.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Bottoms'").getBitmap());
            }
        });


        imagebutton_cus3 = (ImageButton) view.findViewById(R.id.imagebutton_cus3);
        imagebutton_cus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked cus3", Toast.LENGTH_LONG).show();
//                imagebutton_cus3.setImageResource(R.drawable.example_bottom);
                imagebutton_cus3.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'One-piece'").getBitmap());
            }
        });



        imagebutton_cus4 = (ImageButton) view.findViewById(R.id.imagebutton_cus4);
        imagebutton_cus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked cus4", Toast.LENGTH_LONG).show();
//                imagebutton_cus4.setImageResource(R.drawable.transparent);
                imagebutton_cus4.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Shoes'").getBitmap());
            }
        });


        imagebutton_cus5 = (ImageButton) view.findViewById(R.id.imagebutton_cus5);
        imagebutton_cus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked cus5", Toast.LENGTH_LONG).show();
//                imagebutton_cus5.setImageResource(R.drawable.example_shoes);
                imagebutton_cus5.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Bags'").getBitmap());
            }
        });

        imagebutton_cus6 = (ImageButton) view.findViewById(R.id.imagebutton_cus6);
        imagebutton_cus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked cus6", Toast.LENGTH_LONG).show();
//                imagebutton_cus6.setImageResource(R.drawable.transparent);
                imagebutton_cus6.setImageBitmap(CustomiseActivity.charaDbHelper.queryOneRowRandom("'Accessories'").getBitmap());
            }
        });



        button_capture = (Button) view.findViewById(R.id.button_capture);
        layoutcustomise = (GridLayout) view.findViewById(R.id.layout_customise);
        button_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap screen = Screenshot.takescreenshotOfView(v.getRootView().findViewById(R.id.layout_customise));

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                screen.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                String category = "Tops";
                String formality = "Casual";
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String last_used = formatter.format(date); //populates the database with default date
                boolean ootd = true; // when adding clothing into the database, it is an ootd

                CharaDbHelper.CharaData charaData = new CharaDbHelper.CharaData(category,
                        formality, last_used, ootd, bitmap);
                CustomiseActivity.charaDbHelper.insertOneRow( charaData );


//                Intent intent = new Intent(v.getContext(), Screenshotpage.class);
//                intent.putExtra("screenshot", byteArray);
//                startActivity(intent);

            }
        });


        button_addtofavourites = (Button) view.findViewById(R.id.button_addtofavourites);
        button_addtofavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "clicked addtofav", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
