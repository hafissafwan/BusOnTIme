package com.project.busontime;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DriverViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<DriverO> driverOS = new ArrayList<>();
    DriverViewAdapter(Context context){
        this.context = context;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView route;

        MyHolder(View view){
            super(view);
            route = view.findViewById(R.id.route);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_driver_view, viewGroup, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final MyHolder myHolder = (MyHolder) viewHolder;

        if (driverOS.get(i).getRouteName()!=null&&!driverOS.get(i).getRouteName().equals("")){
            myHolder.route.setText(driverOS.get(i).getRouteName());
            myHolder.route.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,MapsActivity.class);
                    Gson gson = new Gson();

                    intent.putExtra("driverO",gson.toJson(driverOS.get(viewHolder.getAdapterPosition())));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return driverOS.size();
    }

    public void update(List<DriverO> driverOS){
        this.driverOS = driverOS;
        notifyDataSetChanged();
    }
}
