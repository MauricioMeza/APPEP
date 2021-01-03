package com.example.appep.UI;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.Data.Model.Evento;
import com.example.appep.R;
import com.example.appep.UI.RecyclerViewClasses.EventAdapter;
import com.example.appep.UI.RecyclerViewClasses.TablaAdapter;

import java.util.List;

public class EventCompTableDialog extends DialogFragment {
    Button exitButton;
    RecyclerView recyclerView;
    List<double[]> datos;

    public EventCompTableDialog(List<double[]> datos) {
        this.datos = datos;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_event_layout, container, false);
        exitButton = view.findViewById(R.id.buttonHistoryBack);
        recyclerView = view.findViewById(R.id.recyclerViewHistory);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TablaAdapter(datos));

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return view;
    }
}
