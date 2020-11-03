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
import com.example.appep.UI.AddPozoActivity;
import com.example.appep.UI.AddPozoFragment2;

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
    public void onBindViewHolder(final ComponenteViewHolder holder, final int position) {
        Componente componente = componentes.get(position);

        //Set component title and image depending on type
        holder.compTitle.setText(componente.getType());
        holder.compImage.setImageResource(componente.getImg());

        //If there is information from components set it into text inside the forms
        if(componente.getLongitud() != 0.0){
            holder.compEditLong.setText(String.valueOf(componente.getLongitud()));
        }
        if(componente.getDiamOD() != 0.0){
            holder.compEditOD.setText(String.valueOf(componente.getDiamOD()));
        }
        if(componente.getDiamID() != 0.0){
            holder.compEditID.setText(String.valueOf(componente.getDiamID()));
        }


        holder.compResVol.setText(String.valueOf(componente.getVolumen()));
        holder.compResCap.setText(String.valueOf(componente.getCapacidad()));


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
                    componentCalculations(holder, position);
                }else if(num.isEmpty()){
                    double longitud = 0;
                    componentes.get(position).setLongitud(longitud);
                    componentCalculations(holder, position);
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
                    componentCalculations(holder, position);
                }else if(num.isEmpty()){
                    double id = 0;
                    componentes.get(position).setDiamID(id);
                    componentCalculations(holder, position);
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
                    componentCalculations(holder, position);

                }else if(num.isEmpty()){
                    double od = 0;
                    componentes.get(position).setDiamOD(od);
                    componentCalculations(holder, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return componentes.size();
    }

    //Calculate Capacity and Volume from the info of the Componente and show it in the textViews of each componente
    public void componentCalculations(ComponenteViewHolder holder, int position){
        double cap = componentes.get(position).calcCapacidad();
        double vol = componentes.get(position).calcVolumen();

        holder.compResCap.setText(String.valueOf(cap));
        holder.compResVol.setText(String.valueOf(vol));

        if(context instanceof AddPozoActivity){
            int fragment = ((AddPozoActivity) context).getCurrentFragment();
            switch (fragment){
                case 2:
                    ((AddPozoActivity) context).fragment2.sumTotales();
                    break;
                case 3:
                    ((AddPozoActivity) context).fragment3.sumTotales();
                    break;
            }
        }

    }

}
