package com.pathmeasure.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Path6Activity extends AppCompatActivity {
    private PathView6 pathView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path6);
        pathView = findViewById(R.id.pathView);
        //pathView.setOrientation(PathView6.ORIENTATION_RIGHT);
    }

    public void left(View view) {
        pathView.setOrientation(PathView6.ORIENTATION_LEFT);
        pathView.setOrientation(PathView6.ORIENTATION_LEFT);
    }

    public void right(View view) {
        pathView.setOrientation(PathView6.ORIENTATION_RIGHT);
        pathView.setOrientation(PathView6.ORIENTATION_RIGHT);
    }

    public void start(View view) {
        pathView.start();
    }

    public void stop(View view) {
        pathView.stop();
    }
}