package com.example.appep.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appep.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPozoFragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPozoFragment4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText prsCrrTbo, prsCrrRev, gncSpr, prsRedBmb, dspBmb, psoOrgLdo, prfVrtVrd, prfTtlMed, prFr, prPr;
    private TextView estrFndArrba, estrHstBrca, circPrMtrPozo;


    public AddPozoFragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPozoFragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPozoFragment4 newInstance(String param1, String param2) {
        AddPozoFragment4 fragment = new AddPozoFragment4();
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
        View v = inflater.inflate(R.layout.fragment_add_pozo4, container, false);

        prsCrrTbo = v.findViewById(R.id.editTextPrsCrrTbo);
        prsCrrRev = v.findViewById(R.id.editTextPrsCrrRev);
        gncSpr = v.findViewById(R.id.editTextGncSup);

        prsRedBmb = v.findViewById(R.id.editTextPrsRedBmb);
        dspBmb = v.findViewById(R.id.editTextDspBmb);

        psoOrgLdo = v.findViewById(R.id.editTextPsoOrgLdo);
        prfVrtVrd = v.findViewById(R.id.editTextPrfVrtVrd);
        prfTtlMed = v.findViewById(R.id.editTextPrfTtlMdi);

        prFr = v.findViewById(R.id.editTextPrFr);
        prPr = v.findViewById(R.id.editTextPrPr);

        estrFndArrba = v.findViewById(R.id.textViewEs);
        estrHstBrca = v.findViewById(R.id.textViewEsB);
        circPrMtrPozo = v.findViewById(R.id.textViewMtrPzo);

        return  v;
    }

}