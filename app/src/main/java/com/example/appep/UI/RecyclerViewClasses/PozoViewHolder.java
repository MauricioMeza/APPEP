package com.example.appep.UI.RecyclerViewClasses;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.R;

//Rlacionar layour de pozocard con la vista dentro del RecyclerView
public class PozoViewHolder extends RecyclerView.ViewHolder {

    TextView nombre, descripcion, fecha;
    ImageView logo;

    public PozoViewHolder(View itemView) {
        super(itemView);

        nombre = (TextView) itemView.findViewById(R.id.pozoNameText);
        descripcion = (TextView) itemView.findViewById(R.id.pozoDescText);
        fecha = (TextView) itemView.findViewById(R.id.pozoDateText);
        logo = (ImageView) itemView.findViewById(R.id.imageViewLogo);

    }
}
