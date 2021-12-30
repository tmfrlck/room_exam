package com.tmf.room_eaxm;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MyAndroidViewModel extends AndroidViewModel {
    public MyAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    public void print() {
        Log.d("Log", "print: hello world");
    }
}