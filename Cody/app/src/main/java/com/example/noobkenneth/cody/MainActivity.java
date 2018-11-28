package com.example.noobkenneth.cody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noobkenneth.cody.api.ApiClient;
import com.example.noobkenneth.cody.api.ApiInterface;
import com.example.noobkenneth.cody.models.Article;
import com.example.noobkenneth.cody.models.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String API_KEY = "a2f02240b01c4e25a1377b872c016b93";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private Adapter adapter;
    private String TAG = MainActivity.class.getSimpleName();

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_wardrobe:
                    mTextMessage.setText(R.string.title_wardrobe);
                    return true;
                case R.id.navigation_photo:
                    mTextMessage.setText(R.string.title_photo);
                    return true;
                case R.id.navigation_customize:
                    mTextMessage.setText(R.string.title_customize);
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        LinearLayout gallery = findViewById(R.id.gallery);

        LayoutInflater galleryInflater = LayoutInflater.from(this);

        for (int i = 0; i < 6; i++) {

            View view = galleryInflater.inflate(R.layout.item, gallery, false);

            TextView textView = view.findViewById(R.id.textView);
            textView.setText("Item" + i);

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.pink_shirt);

            gallery.addView(view);
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);



        LoadJson();

        //created new activity to test Database
        Intent startDbTest = new Intent(MainActivity.this, TestDb.class);
        startActivity(startDbTest);
     }

    public void LoadJson() {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String country = Utils.getCountry();

        Call<News> call;
        call = apiInterface.getNews(country, API_KEY);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null) {
                    if (!articles.isEmpty()) {
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new Adapter(articles, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

}
