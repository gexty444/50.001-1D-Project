package com.example.noobkenneth.cody.Recommendations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noobkenneth.cody.R;

import java.util.List;

public class RecAdapter extends PagerAdapter {

    private List<Recommendations> recommendationsList;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecAdapter(List<Recommendations> recommendationsList, Context context) {
        this.recommendationsList = recommendationsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return recommendationsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull //denotes that a parameter/field/method can never return null
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rec_item, container, false);

        ImageView imageView;
        TextView title;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);

        imageView.setImageResource(recommendationsList.get(position).getImage());
        title.setText(recommendationsList.get(position).getTitle());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
