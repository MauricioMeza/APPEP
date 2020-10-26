package com.example.appep.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appep.Data.Model.Componente;
import com.example.appep.R;
import com.example.appep.UI.RecyclerViewClasses.ComponenteAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPozoFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPozoFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView volInt, capInt;
    private RecyclerView intComponentRecyclerView;

    public AddPozoFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPozoFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPozoFragment2 newInstance(String param1, String param2) {
        AddPozoFragment2 fragment = new AddPozoFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<Componente> comps = AddPozoActivity.pozo.getEventos().get(0).getEventoInterno().getComponentesInterno();

        View view = inflater.inflate(R.layout.fragment_add_pozo2, container, false);

        intComponentRecyclerView = view.findViewById(R.id.recyclerViewCompIntr);
        intComponentRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        intComponentRecyclerView.setAdapter(new ComponenteAdapter(view.getContext(), comps));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}