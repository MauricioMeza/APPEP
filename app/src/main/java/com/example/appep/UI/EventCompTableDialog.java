package com.example.appep.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.Data.Model.Evento;
import com.example.appep.R;
import com.example.appep.UI.RecyclerViewClasses.EventAdapter;
import com.example.appep.UI.RecyclerViewClasses.Returning;
import com.example.appep.UI.RecyclerViewClasses.TablaAdapter;

import java.util.List;

public class EventCompTableDialog extends DialogFragment {
    RecyclerView.Adapter adapter;
    private Returning returning;
    Button exitButton;
    RecyclerView recyclerView;
    List<double[]> datos;
    List<String[]> nombres;

    public EventCompTableDialog(List<double[]> datos, List<String[]> nombres, RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this.datos = datos;
        this.nombres = nombres;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.references_layout, container, false);
        exitButton = view.findViewById(R.id.buttonRefsBack);
        recyclerView = view.findViewById(R.id.recyclerViewRefs);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TablaAdapter innerAdapter = new TablaAdapter(datos, nombres);
        recyclerView.setAdapter(innerAdapter);
        innerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = recyclerView.getChildAdapterPosition(v);
                double[] info = getInfo(n);
                returning = (Returning) adapter;
                returning.returnInfo(info);
                getDialog().dismiss();
            }
        });

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return view;
    }

    public double[] getInfo(int n){
        double[] data = datos.get(n);
        return  data;
    }
}
