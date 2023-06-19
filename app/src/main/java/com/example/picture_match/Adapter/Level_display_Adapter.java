package com.example.picture_match.Adapter;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.picture_match.Activity.Level_Display_Activity;
import com.example.picture_match.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Level_display_Adapter extends BaseAdapter {
    Level_Display_Activity level_display_activity;
    List arrayList = new ArrayList<>();
    TextView timeshow;
    ProgressBar progressBar;
    //View mask1;
    int click = 1, pos1, pos2;
    Runnable runnable;
    View mask2;

    //RelativeLayout relativeLayout;

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
        view = LayoutInflater.from(level_display_activity).inflate(R.layout.level_gridview_item, parent, false);
        ImageView imageView = view.findViewById(R.id.img_disp);
        View mask1 = view.findViewById(R.id.mask1);
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);

        Log.d("MMM", "getView: List=" + arrayList);
        InputStream inputStream = null;
        try {
            inputStream = level_display_activity.getAssets().open("images/" + arrayList.get(position));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
            inputStream.close();
            Log.d("MMM", "getView: Position=" + position);

        } catch (IOException e) {
            new RuntimeException(e);
        }
        startCountDown(mask1, relativeLayout, position);

        return view;
    }

    private void startCountDown(View mask1, RelativeLayout relativeLayout, int position) {

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setMax(5);
                int time = (int) (millisUntilFinished / 1000);
                timeshow.setText("" + time + " /0");
                progressBar.setProgress(time);

            }

            @Override
            public void onFinish() {
                playGame(mask1, relativeLayout, position);
                new CountDownTimer(20000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        progressBar.setMax(20);
                        int time = (int) (millisUntilFinished / 1000);
                        timeshow.setText("" + (progressBar.getMax() - time) + " /0");
                        progressBar.setProgress((progressBar.getMax() - time));
                    }

                    @Override
                    public void onFinish() {
                        relativeLayout.setEnabled(false);
                    }
                }.start();
            }
        }.start();
    }

    private void playGame(View mask1, RelativeLayout relativeLayout, int position) {

        mask1.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        if (click == 1) {
            relativeLayout.setOnClickListener(v -> {
                if (click == 1) {
                    mask1.setVisibility(View.INVISIBLE);
                    pos1 = position;
                    mask2 = mask1;
                    click = 3;
                    Log.d("RRR", "playGame: FirstClick="+click);
                    runnable = new Runnable() {
                        public void run() {
                            click = 2;
                            Log.d("RRR", "playGame: Click="+click);
                        }
                    };

                    handler.postDelayed(runnable, 500);

                }
                if (click == 2) {
                   mask1.setVisibility(View.INVISIBLE);
                    pos2 = position;
                    click = 3;
                    Log.d("RRR", "playGame: SecondClick="+click);
                    if (arrayList.get(pos1).equals(arrayList.get(pos2))) {
                        Log.d("RRR", "playGame: Matched");
                        runnable = new Runnable() {
                            public void run() {
                                click = 1;
                                Log.d("RRR", "playGame: Click="+click);
                            }
                        };

                        handler.postDelayed(runnable, 500);
                    }
                    else
                    {
                        Log.d("RRR", "playGame: NotMatched");
                        runnable = new Runnable() {
                            public void run() {
                               mask1.setVisibility(View.VISIBLE);
                               mask2.setVisibility(View.VISIBLE);
                                click = 1;
                            }
                        };

                        handler.postDelayed(runnable, 500);
                    }
                }
            });

        }

    }
}
