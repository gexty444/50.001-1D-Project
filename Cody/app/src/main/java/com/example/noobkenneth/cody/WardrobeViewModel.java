package com.example.noobkenneth.cody;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Warning: Never pass context into ViewModel instances. Do not store Activity, Fragment, or View
 * instances or their Context in the ViewModel.
 *
 * For example, an Activity can be destroyed and created many times during the lifecycle of a
 * ViewModel as the device is rotated. If you store a reference to the Activity in the ViewModel,
 * you end up with references that point to the destroyed Activity. This is a memory leak.
 *
 * If you need the application context, use AndroidViewModel, as shown in this codelab.
 *
 * Important: ViewModel is not a replacement for the onSaveInstanceState() method, because the
 * ViewModel does not survive a process shutdown. Learn more here.
 */

//Create a class called WordViewModel that extends AndroidViewModel
public class WardrobeViewModel extends AndroidViewModel {

    //Add a private member variable to hold a reference to the repository.
    private WardrobeRepository mRepository;
    //Add a private LiveData member variable to cache the list of clothes
    private LiveData<List<WardrobeEntity>> mAllWords;

    //Add a constructor that gets a reference to the repository and gets the list of clothes from the repository.
    public WardrobeViewModel (Application application) {
        super(application);
        mRepository = new WardrobeRepository(application);
        mAllWords = mRepository.repositoryGetAllCothes();
    }

    //Add a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<WardrobeEntity>> getAllWords() { return mAllWords; }

    //Create a wrapper insert() method that calls the Repository's insert() method.
    //In this way, the implementation of insert() is completely hidden from the UI.
    public void insert(WardrobeEntity wardrobeEntity) { mRepository.insert(wardrobeEntity); }
}