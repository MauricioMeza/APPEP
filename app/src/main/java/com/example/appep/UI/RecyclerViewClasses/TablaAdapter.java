package com.example.appep.UI.RecyclerViewClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.R;

import java.util.List;

public class TablaAdapter extends RecyclerView.Adapter<TablaViewHolder> implements View.OnClickListener {
    private List<String[]> refNombres;
    private List<double[]> referencias;
    private View.OnClickListener onClickListener;

    public TablaAdapter(List<double[]> referencias, List<String[]> refNombres){
        this.refNombres = refNombres;
        this.referencias = referencias;
    }

    @NonNull
    @Override
    public TablaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabla_layout, parent, false);
        v.setOnClickListener(this);
        return new TablaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TablaViewHolder holder, int position) {
        holder.od.setText(String.valueOf(refNombres.get(position)[0]));
        holder.peso.setText(String.valueOf(referencias.get(position)[1]));
        holder.id.setText(String.valueOf(refNombres.get(position)[1]));
    }

    @Override
    public int getItemCount() {
        return referencias.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if(onClickListener != null){
            onClickListener.onClick(v);
        }
    }
}
