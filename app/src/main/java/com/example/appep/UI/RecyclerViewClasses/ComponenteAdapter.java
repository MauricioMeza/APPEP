package com.example.appep.UI.RecyclerViewClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.Data.Model.Componente;
import com.example.appep.R;

import java.util.ArrayList;

public class ComponenteAdapter extends RecyclerView.Adapter<ComponenteViewHolder> {

    private Context context;
    private  ArrayList<Componente> componentes;

    public ComponenteAdapter(Context context, ArrayList<Componente> componentes){
        this.context = context;
        this.componentes = componentes;
    }

    @Override
    public ComponenteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.componentcard_layout, parent, false);
        return new ComponenteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComponenteViewHolder holder, int position) {
        holder.compTitle.setText(componentes.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return componentes.size();
    }
}
