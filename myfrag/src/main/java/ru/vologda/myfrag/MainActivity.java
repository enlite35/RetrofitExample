package ru.vologda.myfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExampleFragments fragment = ExampleFragments.newInstance("mytext",123434);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
