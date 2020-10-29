package com.example.appep.UI.RecyclerViewClasses;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    public void onBindViewHolder(ComponenteViewHolder holder, final int position) {
        //Set component title and image depending on type
        holder.compTitle.setText(componentes.get(position).getType());
        holder.compImage.setImageResource(componentes.get(position).getImg());

        //set listener on text change for all editText listeners
        holder.compEditLong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    double longitud = Double.parseDouble(num);
                    componentes.get(position).setLongitud(longitud);
                }
            }
        });
        holder.compEditID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    double id = Double.parseDouble(num);
                    componentes.get(position).setDiamID(id);
                }

            }
        });
        holder.compEditOD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    double od = Double.parseDouble(num);
                    componentes.get(position).setDiamOD(od);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return componentes.size();
    }
}
