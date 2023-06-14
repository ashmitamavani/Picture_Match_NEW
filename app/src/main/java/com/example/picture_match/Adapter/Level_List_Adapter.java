package com.example.picture_match.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picture_match.Activity.Level_Display_Activity;
import com.example.picture_match.Activity.Level_Listview_Activity;
import com.example.picture_match.R;

public class Level_List_Adapter extends RecyclerView.Adapter<Level_List_Adapter.LevellistHolder>  {
    Level_Listview_Activity level_listview_activity;
    int id;
    public Level_List_Adapter(Level_Listview_Activity level_listview_activity) {
        this.level_listview_activity=level_listview_activity;
    }

    @NonNull
    @Override
    public Level_List_Adapter.LevellistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(level_listview_activity).inflate(R.layout.level_listview_item,parent,false);
        LevellistHolder holder=new LevellistHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Level_List_Adapter.LevellistHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class LevellistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView level[]=new TextView[10];
        public LevellistHolder(@NonNull View itemView) {
            super(itemView);


            for (int i=0;i<level.length;i++)
            {
                id=itemView.getResources().getIdentifier("level"+i,"id", level_listview_activity.getPackageName());
                level[i]=itemView.findViewById(id);
                level[i].setOnClickListener(this);
            }


        }

        @Override
        public void onClick(View v) {
            for (int i=0;i<10;i++)
            {
                Intent intent=new Intent(level_listview_activity, Level_Display_Activity.class);
                intent.putExtra("levelNo", 0);
                System.out.println("level no"+0);
                level_listview_activity.startActivity(intent);
            }
        }
    }
}


/*


 */