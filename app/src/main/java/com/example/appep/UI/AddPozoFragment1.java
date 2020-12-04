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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appep.Data.Model.Pozo;
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
    private CheckBox rev1, rev2, rev3, dp1, dp2, dp3, hwdp, dc, dc2, broc, estb, hrrA;
    private RadioGroup type;
    private ImageView imgAn1, imgAn2, imgIntD, imgIntB, imgIntC1, imgIntC2;
    private EditText nameText, descText;
    private TextView textTitle;
    private boolean componentSelected[], vertical;


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
        componentSelected = new boolean[11];
        vertical = true;


        textTitle = vista.findViewById(R.id.textTitleAdd);
        nameText = vista.findViewById(R.id.editTextToDoName);
        descText = vista.findViewById(R.id.editTextToDoDesc);
        type = vista.findViewById(R.id.radioGroupType);

        dp1 = vista.findViewById(R.id.checkBoxDP1);
        dp2 = vista.findViewById(R.id.checkBoxDP2);
        dp3 = vista.findViewById(R.id.checkBoxDP3);
        dc = vista.findViewById(R.id.checkBoxDC);
        dc2 = vista.findViewById(R.id.checkBoxDC2);
        hwdp = vista.findViewById(R.id.checkBoxHWDP);
        broc = vista.findViewById(R.id.checkBoxBroca);
        estb = vista.findViewById(R.id.checkBoxEstabilizador);
        hrrA = vista.findViewById(R.id.checkBoxHrrAdc);
        rev1 = vista.findViewById(R.id.checkBoxRev1);
        rev2 = vista.findViewById(R.id.checkBoxRev2);
        rev3 = vista.findViewById(R.id.checkBoxRev3);

        imgAn1 = vista.findViewById(R.id.imageViewAn1);
        imgAn2 = vista.findViewById(R.id.imageViewAn2);
        imgIntD = vista.findViewById(R.id.imageViewIn);
        imgIntC1 = vista.findViewById(R.id.imageViewIn2_1);
        imgIntC2 = vista.findViewById(R.id.imageViewIn2_2);
        imgIntB = vista.findViewById(R.id.imageViewIn3);

        configureSelectionGraphic();
        configureTypeSelection();

        if(!AddPozoActivity.addNew){
            Pozo thisPozo = AddPozoActivity.pozo;
            textTitle.setText(R.string.updateTitle);
            nameText.setText(thisPozo.getNombre());
            nameText.setEnabled(false);
            descText.setText(thisPozo.getCampo());
            descText.setEnabled(false);
            if(thisPozo.isVertical()){
                type.check(R.id.radioButtonVert);
            }else{
                type.check(R.id.radioButtonHztl);
                for (int i = 0; i < type.getChildCount(); i++) {
                    type.getChildAt(i).setEnabled(false);
                }
            }

        }

        return vista;

    }

    //Change type propertie from radiobuttons
    private void configureTypeSelection() {
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonVert:
                        vertical = true;
                        break;
                    case R.id.radioButtonHztl:
                        vertical = false;
                        break;
                }

            }
        });
    }

    //On each checkbox action change color of figure on the bottom
    private void configureSelectionGraphic() {
        rev1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rev1.isChecked()){
                    componentSelected[0] = true;
                    rev2.setEnabled(true);
                    imgAn1.setImageResource(R.drawable.an_2);
                    imgAn2.setImageResource(R.drawable.an_2);
                }else{
                    componentSelected[0] = false;
                    componentSelected[1] = false;
                    componentSelected[2] = false;
                    rev2.setChecked(false);
                    rev2.setEnabled(false);
                    rev3.setEnabled(false);
                    rev3.setChecked(false);
                    imgAn1.setImageResource(R.drawable.an_1);
                    imgAn2.setImageResource(R.drawable.an_1);

                }
            }
        });
        rev2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rev2.isChecked()){
                    componentSelected[1] = true;
                    rev3.setEnabled(true);
                    imgAn1.setImageResource(R.drawable.an_3);
                    imgAn2.setImageResource(R.drawable.an_3);
                }else{
                    componentSelected[1] = false;
                    componentSelected[2] = false;
                    rev3.setEnabled(false);
                    rev3.setChecked(false);
                    imgAn1.setImageResource(R.drawable.an_2);
                    imgAn2.setImageResource(R.drawable.an_2);
                }
            }
        });
        rev3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rev3.isChecked()){
                    componentSelected[2] = true;
                    imgAn1.setImageResource(R.drawable.an_4);
                    imgAn2.setImageResource(R.drawable.an_4);
                }else{
                    componentSelected[2] = false;
                    imgAn1.setImageResource(R.drawable.an_3);
                    imgAn2.setImageResource(R.drawable.an_3);
                }
            }
        });
        dp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dp1.isChecked()){
                    dp2.setEnabled(true);
                    componentSelected[3] = true;
                    imgIntD.setImageResource(R.drawable.in_d2);
                }else{
                    componentSelected[3] = false;
                    componentSelected[4] = false;
                    componentSelected[5] = false;
                    dp2.setEnabled(false);
                    dp2.setChecked(false);
                    dp3.setEnabled(false);
                    dp3.setChecked(false);
                    imgIntD.setImageResource(R.drawable.in_d1);
                }
            }
        });
        dp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dp2.isChecked()){
                    dp3.setEnabled(true);
                    componentSelected[4] = true;
                    imgIntD.setImageResource(R.drawable.in_d3);
                }else{
                    componentSelected[4] = false;
                    componentSelected[5] = false;
                    dp3.setEnabled(false);
                    dp3.setChecked(false);
                    imgIntD.setImageResource(R.drawable.in_d2);
                }
            }
        });
        dp3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dp3.isChecked()){
                    componentSelected[5] = true;
                    imgIntD.setImageResource(R.drawable.in_d4);
                }else{
                    componentSelected[5] = false;
                    imgIntD.setImageResource(R.drawable.in_d3);
                }
            }
        });
        hwdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(hwdp.isChecked()){
                    componentSelected[6] = true;
                    imgIntC1.setImageResource(R.drawable.in_c2);
                }else{
                    componentSelected[6] = false;
                    imgIntC1.setImageResource(R.drawable.in_c1);
                }
            }
        });
        dc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dc.isChecked()){
                    dc2.setEnabled(true);
                    componentSelected[7] = true;
                    imgIntC2.setImageResource(R.drawable.in_c4);
                }else{
                    componentSelected[7] = false;
                    componentSelected[8] = false;
                    dc2.setEnabled(false);
                    dc2.setChecked(false);
                    imgIntC2.setImageResource(R.drawable.in_c3);
                }
            }
        });
        dc2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dc2.isChecked()){
                    componentSelected[8] = true;
                    imgIntC2.setImageResource(R.drawable.in_c5);
                }else{
                    componentSelected[8] = false;
                    imgIntC2.setImageResource(R.drawable.in_c4);
                }
            }
        });
        hrrA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(hrrA.isChecked()){
                    componentSelected[9] = true;
                }else{
                    componentSelected[9] = false;
                }
            }
        });
        estb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(estb.isChecked()){
                    componentSelected[10] = true;
                    imgIntB.setImageResource(R.drawable.in_b2);
                }else{
                    componentSelected[10] = false;
                    imgIntB.setImageResource(R.drawable.in_b1);
                }
            }
        });

        //Set rev1->rev2->rev3, drill1->drill2->drill3 and dc1->dc2 checklist dependency
        if(!rev1.isChecked()){
            rev2.setEnabled(false);
            rev3.setEnabled(false);
        }
        if(!dp1.isChecked()){
            dp2.setEnabled(false);
            dp3.setEnabled(false);
        }
        if(!dc.isChecked()){
            dc2.setEnabled(false);
        }
        broc.setEnabled(false);
    }

    //Get the information of the EditTexts in the pozo as a String Array
    public String[] getInfoPozo(){
        String[] infoPozo = {nameText.getText().toString(), descText.getText().toString(), String.valueOf(vertical)};
        return infoPozo;
    }

    public boolean[] getComponentSelected() { return componentSelected; }
}