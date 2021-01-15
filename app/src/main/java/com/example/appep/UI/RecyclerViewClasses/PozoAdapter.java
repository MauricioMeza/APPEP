package com.example.appep.UI.RecyclerViewClasses;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;

import java.util.ArrayList;

public class PozoAdapter extends RecyclerView.Adapter<PozoViewHolder> implements View.OnClickListener {

    private ArrayList<Pozo> listaToDos;
    private View.OnClickListener onClickListener;

    //The adapter receivers an ArrayList of wells
    public PozoAdapter(ArrayList<Pozo> listaPozos) {
        this.listaToDos = listaPozos;
    }

    //In every viewholder place a pozocard layout and set it to recyclerview as parent
    //Set a new onClickListenerOnIt
    @Override
    public PozoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pozocard_layout, parent, false);
        v.setOnClickListener(this);
        return new PozoViewHolder(v);
    }

    //Set the text in every pozoCard as the info in the object iterated from ArrayList listaPozos
    @Override
    public void onBindViewHolder(PozoViewHolder holder, int position) {
        holder.nombre.setText(listaToDos.get(position).getNombre());
        holder.descripcion.setText(listaToDos.get(position).getCampo());
        holder.fecha.setText(listaToDos.get(position).getFechaCreacion().toString());

        if(!listaToDos.get(position).isAbierto()){
            holder.nombre.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.logo.setImageResource(R.drawable.logoperwh);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    //Create onClickListener for the whole view generated as a Layout
    @Override
    public void onClick(View v) {
        if(onClickListener != null){
            onClickListener.onClick(v);
        }
    }

    @Override
    public int getItemCount() {
        return listaToDos.size();
    }
}
