package com.example.noobkenneth.cody.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noobkenneth.cody.R;


public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder>{

    LayoutInflater mInflater;
    Context context;
    CharaDbHelper charaDbHelper;


    //TODO 9.3 Constructor takes in a context object and a CharaDbHelper object
    //TODO 9.3 assign the inputs to instance variables
    public CharaAdapter(Context context, CharaDbHelper charaDbHelper) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.charaDbHelper = charaDbHelper;
    }

    //TODO 9.4 onCreateViewHolder inflates each CardView layout (no coding)
    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.layout, viewGroup, false);
        return new CharaViewHolder(itemView);
    }

    //TODO 9.5 onBindViewHolder binds the data to each card according to its position
    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder charaViewHolder, int i) {
        // i is the position in the recyclerview
        CharaDbHelper.CharaData charaData = charaDbHelper.queryOneRow(i);
        charaViewHolder.textViewCategory.setText(charaData.getCategory());
        Log.i("Logcat", "CharaAdapter formality called: " + charaData.getFormality());
        charaViewHolder.textViewFormality.setText(String.valueOf(charaData.getFormality()));
        charaViewHolder.textViewLastUsed.setText(charaData.getLastUsed());
        charaViewHolder.textViewOotd.setText(String.valueOf(charaData.getOotd()));
        charaViewHolder.textViewPosition.setText(Integer.toString(i));
        charaViewHolder.imageViewChara.setImageBitmap(charaData.getBitmap());
    }

    //TODO 9.6 this method controls the number of cardviews in the recyclerview
    @Override
    public int getItemCount() {

        int numberOfRows = (int) charaDbHelper.queryNumRows();
        return numberOfRows;
    }

    //TODO 9.2 Complete the constructor to initialize the widgets
    //TODO ATTENTION SVP we had to make this class static
    static class CharaViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewPosition;
        public TextView textViewCategory;
        public TextView textViewFormality;
        public TextView textViewLastUsed;
        public TextView textViewOotd;
        public ImageView imageViewChara;

        public CharaViewHolder(View view){
            super(view);
            textViewPosition = view.findViewById(R.id.cardViewTextCount);
            imageViewChara = view.findViewById(R.id.cardViewImage);
            textViewCategory = view.findViewById(R.id.cardViewTextCategory);
            textViewFormality = view.findViewById(R.id.cardViewTextFormality);
            textViewLastUsed = view.findViewById(R.id.cardViewTextLastUsed);
            textViewOotd = view.findViewById(R.id.cardViewTextOotd);
        }
    }
}
