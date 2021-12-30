package com.tmf.room_eaxm;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class NormalViewModel extends ViewModel {
    public void print() {
        Log.d("Log", "print: hello world");
    }
}