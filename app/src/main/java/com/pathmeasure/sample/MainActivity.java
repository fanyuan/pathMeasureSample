package com.pathmeasure.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toPath1(View view) {
        startActivity(new Intent(this,Path1Activity.class));
    }

    public void toPath2(View view) {
        startActivity(new Intent(this,Path2Activity.class));
    }

    public void toPath3(View view) {
        startActivity(new Intent(this,Path3Activity.class));
    }

    public void toComplex(View view) {
        startActivity(new Intent(this,PathComplexActivity.class));
    }

    public void toPathCircle(View view) {
        startActivity(new Intent(this,PathCircleActivity.class));
    }

    public void toPath5(View view) {
        startActivity(new Intent(this,Path5Activity.class));
    }
    public void toPath6(View view) {
        startActivity(new Intent(this,Path6Activity.class));
    }
    public void toPathDrawable(View view) {
        startActivity(new Intent(this,PathDrawableActivity.class));
    }
}