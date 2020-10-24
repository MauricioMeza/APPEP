package com.example.appep.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appep.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPozoFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPozoFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View vista;

    //UI Declarations
    private CheckBox rev1, rev2, dp1, dp2, hwdp, dc,broc, estb;
    private ImageView imgAn1, imgAn2, imgIntD, imgIntB, imgIntC1, imgIntC2;
    private EditText nameText, descText;
    private TextView textTitle;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPozoFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPozoFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPozoFragment1 newInstance(String param1, String param2) {
        AddPozoFragment1 fragment = new AddPozoFragment1();
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
        vista = inflater.inflate(R.layout.fragment_add_pozo1, container, false);

        textTitle = vista.findViewById(R.id.textTitleAdd);
        nameText = vista.findViewById(R.id.editTextToDoName);
        descText = vista.findViewById(R.id.editTextToDoDesc);

        dp1 = vista.findViewById(R.id.checkBoxDP1);
        dp2 = vista.findViewById(R.id.checkBoxDP2);
        dc = vista.findViewById(R.id.checkBoxDC);
        hwdp = vista.findViewById(R.id.checkBoxHWDP);
        broc = vista.findViewById(R.id.checkBoxBroca);
        estb = vista.findViewById(R.id.checkBoxEstabilizador);
        rev1 = vista.findViewById(R.id.checkBoxRev1);
        rev2 = vista.findViewById(R.id.checkBoxRev2);

        imgAn1 = vista.findViewById(R.id.imageViewAn1);
        imgAn2 = vista.findViewById(R.id.imageViewAn2);
        imgIntD = vista.findViewById(R.id.imageViewIn);
        imgIntC1 = vista.findViewById(R.id.imageViewIn2_1);
        imgIntC2 = vista.findViewById(R.id.imageViewIn2_2);
        imgIntB = vista.findViewById(R.id.imageViewIn3);

        configureSelectionGraphic();


        return vista;

    }

    //On each checkbox action change color of figure on the bottom
    private void configureSelectionGraphic() {
        rev1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rev1.isChecked()){
                    AddPozoActivity.componentSelected[0] = true;
                    rev2.setEnabled(true);
                    imgAn1.setImageResource(R.drawable.an_2);
                    imgAn2.setImageResource(R.drawable.an_2);
                }else{
                    AddPozoActivity.componentSelected[0] = false;
                    AddPozoActivity.componentSelected[1] = false;
                    rev2.setChecked(false);
                    rev2.setEnabled(false);
                    imgAn1.setImageResource(R.drawable.an_1);
                    imgAn2.setImageResource(R.drawable.an_1);

                }
            }
        });
        rev2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rev2.isChecked()){
                    AddPozoActivity.componentSelected[1] = true;
                    imgAn1.setImageResource(R.drawable.an_3);
                    imgAn2.setImageResource(R.drawable.an_3);
                }else{
                    AddPozoActivity.componentSelected[1] = false;
                    imgAn1.setImageResource(R.drawable.an_2);
                    imgAn2.setImageResource(R.drawable.an_2);
                }
            }
        });
        dp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dp1.isChecked()){
                    dp2.setEnabled(true);
                    AddPozoActivity.componentSelected[2] = true;
                    imgIntD.setImageResource(R.drawable.in_d2);
                }else{
                    AddPozoActivity.componentSelected[2] = false;
                    AddPozoActivity.componentSelected[3] = false;
                    dp2.setEnabled(false);
                    dp2.setChecked(false);
                    imgIntD.setImageResource(R.drawable.in_d1);
                }
            }
        });
        dp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dp2.isChecked()){
                    AddPozoActivity.componentSelected[3] = true;
                    imgIntD.setImageResource(R.drawable.in_d3);
                }else{
                    AddPozoActivity.componentSelected[3] = false;
                    imgIntD.setImageResource(R.drawable.in_d2);
                }
            }
        });
        hwdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(hwdp.isChecked()){
                    AddPozoActivity.componentSelected[4] = true;
                    imgIntC1.setImageResource(R.drawable.in_c2);
                }else{
                    AddPozoActivity.componentSelected[4] = false;
                    imgIntC1.setImageResource(R.drawable.in_c1);
                }
            }
        });
        dc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dc.isChecked()){
                    AddPozoActivity.componentSelected[5] = true;
                    imgIntC2.setImageResource(R.drawable.in_c4);
                }else{
                    AddPozoActivity.componentSelected[5] = false;
                    imgIntC2.setImageResource(R.drawable.in_c3);
                }
            }
        });
        estb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(estb.isChecked()){
                    AddPozoActivity.componentSelected[6] = true;
                    imgIntB.setImageResource(R.drawable.in_b2);
                }else{
                    AddPozoActivity.componentSelected[6] = false;
                    imgIntB.setImageResource(R.drawable.in_b1);
                }
            }
        });

        //Set rev1->rev2 and drill1->drill2 checklist dependency
        if(!rev1.isChecked()){
            rev2.setEnabled(false);
        }
        if(!dp1.isChecked()){
            dp2.setEnabled(false);
        }
        broc.setEnabled(false);
    }
}