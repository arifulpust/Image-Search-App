package com.pluang.imagesearchapp.ui.main.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;

import com.pluang.imagesearchapp.R;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        ListView list=findViewById(R.id.list);
        outState.putLong("",list.getSelectedItemId());
        super.onSaveInstanceState(outState);

    }
}