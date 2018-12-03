package com.example.noobkenneth.cody.database;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.noobkenneth.cody.R;

public class RecylerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CharaAdapter charaAdapter;
    CharaDbHelper charaDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);

        //TODO 9.7 The standard code to fill the recyclerview with data
        recyclerView = findViewById(R.id.charaRecyclerView);
        charaDbHelper = CharaDbHelper.createCharaDbHelper(this);
        charaAdapter = new CharaAdapter(this, charaDbHelper);
        recyclerView.setAdapter(charaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //TODO 9.8 Put in code to allow each recyclerview item to be deleted when swiped
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback( 0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                //TODO ATTENTION SVP - to write this code we had to make the CharaViewHolder class static
                CharaAdapter.CharaViewHolder charaViewHolder
                        = (CharaAdapter.CharaViewHolder) viewHolder;

//                String name = charaViewHolder.textViewName.getText().toString();
//                charaDbHelper.deleteOneRow(name);
                Toast.makeText(RecylerViewActivity.this, "Deleting Row", Toast.LENGTH_LONG).show();
                charaAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }
        };


        //TODO 9.9 attach the recyclerView to helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper( simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }
}
