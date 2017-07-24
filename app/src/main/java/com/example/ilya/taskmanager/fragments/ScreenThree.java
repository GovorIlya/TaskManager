package com.example.ilya.taskmanager.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ilya.taskmanager.MainActivity;
import com.example.ilya.taskmanager.R;
import com.example.ilya.taskmanager.TestNotification;

public class ScreenThree extends Fragment {
    Button btnNotification;
    public ScreenThree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_three, container,
                false);
            Intent intent=new Intent(getActivity(), TestNotification.class);
        startActivity(intent);


        return rootView;
    }


}
