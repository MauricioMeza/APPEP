package com.example.appep.UI.RecyclerViewClasses;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.R;

public class TablaViewHolder extends RecyclerView.ViewHolder {

    TextView od, peso, id;

    public TablaViewHolder(@NonNull View itemView) {
        super(itemView);

        od = (TextView) itemView.findViewById(R.id.textViewTablaOD);
        peso = (TextView) itemView.findViewById(R.id.textViewTablaPeso);
        id = (TextView) itemView.findViewById(R.id.textViewTablaID);
    }
}
