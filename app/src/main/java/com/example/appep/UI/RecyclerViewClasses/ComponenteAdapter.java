package com.example.appep.UI.RecyclerViewClasses;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.Data.Model.CompTipo;
import com.example.appep.Data.Model.Componente;
import com.example.appep.R;
import com.example.appep.UI.AddPozoActivity;
import com.example.appep.UI.AddPozoFragment2;
import com.example.appep.UI.EventCompTableDialog;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponenteAdapter extends RecyclerView.Adapter<ComponenteViewHolder> implements Returning{
    private RecyclerView.Adapter adapter = this;
    private RecyclerView recyclerVier;
    private Fragment currentFragment;
    private Context context;
    private ArrayList<Componente> componentes;
    private static DecimalFormat df = new DecimalFormat("#.#####");

    //Variables used for selection of reference component information in dialog Fragment
    private int currentSelectedReference;
    private ComponenteViewHolder currentSelectedHolder;


    public ComponenteAdapter(Context context, ArrayList<Componente> componentes, Fragment currentFragment, RecyclerView recyclerView){
        this.context = context;
        this.componentes = componentes;
        this.currentFragment = currentFragment;
        this.recyclerVier = recyclerView;
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

        //Anular components will not have External components
        if(AddPozoActivity.currentFragment == 3){
            holder.compEditOD.setEnabled(false);
        }

        if(componente.isTable()){
            holder.compRefs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double[][] doubles = componente.getTablas().getTableValues();
                    String[][] strings = componente.getTablas().getTableNames();
                    List<double[]> valores = Arrays.asList(doubles);
                    List<String[]> nombres = Arrays.asList(strings);

                    currentSelectedReference = position;
                    currentSelectedHolder = holder;

                    EventCompTableDialog eventCompTableDialog = new EventCompTableDialog(valores, nombres, adapter);
                    eventCompTableDialog.setTargetFragment(currentFragment, 1);
                    eventCompTableDialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "TABLE_DIALOG");
                }
            });
        }else{
            holder.compRefs.setVisibility(View.GONE);
        }

        holder.compResVol.setText(df.format(componente.getVolumen()));
        holder.compResCap.setText(df.format(componente.getCapacidad()));


        //set listener on text change for all editText listeners
        holder.compEditLong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double longitud = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    longitud = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    longitud = 0;
                }
                componentes.get(position).setLongitud(longitud);
                componentCalculations(holder, position);
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
                double id = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    id = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    id = 0;
                }
                componentes.get(position).setDiamID(id);
                componentCalculations(holder, position);
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
                double od = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    od = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    od = 0;
                }
                componentes.get(position).setDiamOD(od);
                componentCalculations(holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return componentes.size();
    }

    //Calculate Capacity and Volume from the info of the Componente and show it in the textViews of each componente
    public void componentCalculations(ComponenteViewHolder holder, int position){
        double cap;
        double vol;

        if(context instanceof AddPozoActivity){
            int fragment = ((AddPozoActivity) context).getCurrentFragment();
            switch (fragment){
                case 2:
                    cap = componentes.get(position).calcCapacidad();
                    vol = componentes.get(position).calcVolumen();

                    holder.compResCap.setText(df.format(cap));
                    holder.compResVol.setText(df.format(vol));

                    ((AddPozoActivity) context).fragment2.sumTotales();
                    break;
                case 3:
                    this.componentes = componentes.get(position).calculosAnulares(componentes);
                    cap = componentes.get(position).getCapacidad();
                    vol = componentes.get(position).getVolumen();
                    holder.compResCap.setText(df.format(cap));
                    holder.compResVol.setText(df.format(vol));

                    ((AddPozoActivity) context).fragment3.sumTotales();
                    break;
            }
        }

    }

    //Actions performed on the dataset, and recyclerview after a component is sleceted from the dialog
    @Override
    public void returnInfo(double[] info) {
        componentes.get(currentSelectedReference).setDiamOD(info[0]);
        componentes.get(currentSelectedReference).setDiamID(info[2]);
        componentCalculations(currentSelectedHolder, currentSelectedReference);
        this.notifyDataSetChanged();
    }
}
