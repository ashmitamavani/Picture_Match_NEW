package com.example.picture_match.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.picture_match.Adapter.Config;
import com.example.picture_match.Adapter.Level_display_Adapter;
import com.example.picture_match.R;

public class Level_Display_Activity extends AppCompatActivity {
    GridView level_gridview;
    ImageView back;
    TextView timeshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_display);
        level_gridview=findViewById(R.id.level_gridview);
        back=findViewById(R.id.back);
        timeshow=findViewById(R.id.timeshow);

        Level_display_Adapter adapter=new Level_display_Adapter(Level_Display_Activity.this);
        level_gridview.setAdapter(adapter);
    }
}