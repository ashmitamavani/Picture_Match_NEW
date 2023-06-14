package com.example.picture_match.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.picture_match.Activity.Level_Display_Activity;
import com.example.picture_match.R;

public class Level_display_Adapter extends BaseAdapter {
    Level_Display_Activity level_display_activity;
    public Level_display_Adapter(Level_Display_Activity level_display_activity) {
        this.level_display_activity=level_display_activity;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view= LayoutInflater.from(level_display_activity).inflate(R.layout.level_gridview_item,parent,false);
        ImageView imageView=view.findViewById(R.id.img_disp);
        imageView.setImageResource(Config.imgarr[position]);

        return view;
    }
}
