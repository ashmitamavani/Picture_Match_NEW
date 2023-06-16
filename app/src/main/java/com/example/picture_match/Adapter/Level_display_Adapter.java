package com.example.picture_match.Adapter;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.picture_match.Activity.Level_Display_Activity;
import com.example.picture_match.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Level_display_Adapter extends BaseAdapter {
    Level_Display_Activity level_display_activity;
    List arrayList=new ArrayList<>();
    TextView timeshow;
    ProgressBar progressBar;

    public Level_display_Adapter(Level_Display_Activity level_display_activity, List arrayList, TextView timeshow, ProgressBar progressBar) {

        this.level_display_activity = level_display_activity;
        this.arrayList = arrayList;
        this.timeshow = timeshow;
        this.progressBar = progressBar;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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

        Log.d("MMM", "getView: List="+arrayList);
        InputStream inputStream = null;
        try {
            inputStream = level_display_activity.getAssets().open("images/" + arrayList.get(position));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
            inputStream.close();
            Log.d("MMM", "getView: Position="+position);

        } catch (IOException e) {
            new RuntimeException(e);
        }
        startCountDown();

        return view;
    }

    private void startCountDown()
    {

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setMax(5);
                int time= (int) (millisUntilFinished/1000);
                timeshow.setText(""+time+" /0");
                progressBar.setProgress(time);
            }

            @Override
            public void onFinish() {
                new CountDownTimer(20000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progressBar.setMax(20);
                        int time= (int) (millisUntilFinished/1000);
                        timeshow.setText(""+(progressBar.getMax()-time)+" /0");
                        progressBar.setProgress((progressBar.getMax()-time));
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        }.start();
    }
}
