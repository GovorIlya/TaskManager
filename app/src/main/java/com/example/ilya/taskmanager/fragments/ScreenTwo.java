package com.example.ilya.taskmanager.fragments;

/**
 * Created by Ilya on 08.07.2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ilya.taskmanager.R;
public class ScreenTwo extends Fragment {
    public ScreenTwo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_two, container,
                false);

        return rootView;
    }
}
