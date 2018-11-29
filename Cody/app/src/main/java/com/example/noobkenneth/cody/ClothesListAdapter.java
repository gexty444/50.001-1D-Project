package com.example.noobkenneth.cody;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ClothesListAdapter extends RecyclerView.Adapter<ClothesListAdapter.ClothesViewHolder> {
    private final LayoutInflater mInflater;
    private List<WardrobeEntity> mWardrobeEntity; // Cached copy of clothes

    ClothesListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ClothesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClothesViewHolder holder, int position) {
        if (mWardrobeEntity != null) {
            WardrobeEntity current = mWardrobeEntity.get(position);
            holder.clothesItemView.setText(current.getId());
        } else {
            // Covers the case of data not being ready yet.
            holder.clothesItemView.setText("No clothes");
        }
    }

    void setWardrobe(List<WardrobeEntity> wardrobeEntity){
        mWardrobeEntity = wardrobeEntity;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWardrobeEntity has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWardrobeEntity != null)
            return mWardrobeEntity.size();
        else return 0;
    }

    class ClothesViewHolder extends RecyclerView.ViewHolder {
        private final TextView clothesItemView;

        private ClothesViewHolder(View itemView) {
            super(itemView);
            clothesItemView = itemView.findViewById(R.id.textView);
        }
    }
}
