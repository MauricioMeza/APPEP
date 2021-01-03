package com.example.appep.UI.RecyclerViewClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.R;

import java.util.List;

public class TablaAdapter extends RecyclerView.Adapter<TablaViewHolder> {
    List<double[]> referencias;

    public TablaAdapter(List<double[]> referencias){
        this.referencias = referencias;
    }

    @NonNull
    @Override
    public TablaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabla_layout, parent, false);
        return new TablaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TablaViewHolder holder, int position) {
        holder.od.setText(String.valueOf(referencias.get(position)[0]));
        holder.peso.setText(String.valueOf(referencias.get(position)[1]));
        holder.id.setText(String.valueOf(referencias.get(position)[2]));
    }

    @Override
    public int getItemCount() {
        return referencias.size();
    }
}
