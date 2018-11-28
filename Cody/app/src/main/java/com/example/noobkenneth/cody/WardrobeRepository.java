package com.example.noobkenneth.cody;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


public class WardrobeRepository {

    //initialize the dao and the list of ids using a method from WardrobeDao
    private WardrobeDao mWardrobeDao;
    private LiveData<List<WardrobeEntity>> mAllCothes;

    //Add a constructor that gets a handle to the database and initializes the member variables
    WardrobeRepository(Application application) {
        WardrobeDb db = WardrobeDb.getDatabase(application);
        mWardrobeDao = db.wardrobeDao();
        mAllCothes = mWardrobeDao.getAllClothes();
    }

    //Add a wrapper for getAllClothes(). Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed
    LiveData<List<WardrobeEntity>> getAllWords() {
        return mAllCothes;
    }

    //Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
    //Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
    public void insert (WardrobeEntity wardrobeEntity) {
        new insertAsyncTask(mWardrobeDao).execute(wardrobeEntity);
    }

    //There is nothing magical about the AsyncTask, so here it is for you to copy.
    private static class insertAsyncTask extends AsyncTask<WardrobeEntity, Void, Void> {
        private WardrobeDao mAsyncTaskDao;
        insertAsyncTask(WardrobeDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final WardrobeEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}