package com.example.noobkenneth.cody.Customise;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;

public class Screenshot {

    public static Bitmap takescreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        v.setDrawingCacheBackgroundColor(Color.WHITE);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfView(View v) {
        return takescreenshot(v);
    }

}