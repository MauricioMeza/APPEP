package com.example.appep.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.appep.Data.Model.EventoAmago;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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

    private EventoAmago eventoAmago;
    private EditText prsCrrTbo, prsCrrRev, gncSpr,    prsRedBmb, dspBmb,   psoOrgLdo, prfVrtVrd, prfTtlMed, prFr, prPr;
    private LinearLayout koLayout, ebLayout, kopLayoutDiv, eobLayoutDiv;
    private TextView estrKo, estrEb;
    private TableRow extraTRow1, extraTRow2;
    private TextView estrFndArrba, estrHstBrca, circPrMtrPozo, pesoLodoAprox, pesoLodoTotal;
    private int[][] names = {{R.id.textViewEst1,R.id.textViewPrs1},{R.id.textViewEst2,R.id.textViewPrs2},{R.id.textViewEst3,R.id.textViewPrs3},{R.id.textViewEst4,R.id.textViewPrs4},
                             {R.id.textViewEst5,R.id.textViewPrs5},{R.id.textViewEst6,R.id.textViewPrs6},{R.id.textViewEst7,R.id.textViewPrs7},{R.id.textViewEst8,R.id.textViewPrs8},
                             {R.id.textViewEst9,R.id.textViewPrs9},{R.id.textViewEst10,R.id.textViewPrs10},{R.id.textViewEst11,R.id.textViewPrs11},{R.id.textViewEst12,R.id.textViewPrs12},{R.id.textViewEst13,R.id.textViewPrs13}};
    private TextView[][] estroques = new TextView[13][2];

    private static DecimalFormat nf = new DecimalFormat("#");
    private static DecimalFormat af = new DecimalFormat("#.#");
    private static DecimalFormat df = new DecimalFormat("#.#####");



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

        eventoAmago = AddPozoActivity.pozo.getEventos().get(0).getEventoAmago();

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

        koLayout = v.findViewById(R.id.kickOffEstrLayout);
        ebLayout = v.findViewById(R.id.endBuildLayout);
        estrKo = v.findViewById(R.id.textViewEsKoBlank);
        estrEb = v.findViewById(R.id.textViewEsEbBlank);
        kopLayoutDiv = v.findViewById(R.id.layoutDivKOP);
        eobLayoutDiv = v.findViewById(R.id.layoutDivEOB);

        extraTRow1 = v.findViewById(R.id.extraTableRowHztl1);
        extraTRow2 = v.findViewById(R.id.extraTableRowHztl2);

        estrFndArrba = v.findViewById(R.id.textViewEsFa);
        estrHstBrca = v.findViewById(R.id.textViewEsBr);
        circPrMtrPozo = v.findViewById(R.id.textViewMtrPzo);

        pesoLodoAprox = v.findViewById(R.id.textViewPesoLodoAprx);
        pesoLodoTotal = v.findViewById(R.id.textViewPesoLodoTotal);

        for(int i=0; i<names.length; i++){
            estroques[i][0] = v.findViewById(names[i][0]);
            estroques[i][1] = v.findViewById(names[i][1]);
        }

        //Actions for when the Pozo is Vertical or Horizontal
        Pozo pozo = AddPozoActivity.pozo;
        if(pozo.isVertical()){
            double longitud = eventoAmago.getEvento().getLongTotal();
            prfVrtVrd.setText(df.format(longitud));
            prfTtlMed.setText(df.format(longitud));
            eventoAmago.setProfVertical(longitud);
            eventoAmago.setProfTotal(longitud);
            prfTtlMed.setEnabled(false);
            prfVrtVrd.setEnabled(false);

            koLayout.setVisibility(View.GONE);
            ebLayout.setVisibility(View.GONE);
            kopLayoutDiv.setVisibility(View.GONE);
            eobLayoutDiv.setVisibility(View.GONE);
            extraTRow1.setVisibility(View.GONE);
            extraTRow2.setVisibility(View.GONE);
        }

        configureEdits();


        return  v;
    }

    //Configuration in case of the pozo being horizontal
    private void configureHztl() {
    }

    //TODO: Use formated textWatchers to refactor the textChanged Listeners
    //Configure the EditTexts that calculate PesoDLodoPaMatar
    private void configureEdits() {

        prsCrrTbo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double psoCrr = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    psoCrr = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    psoCrr = 0;
                }
                eventoAmago.setPresCierreTubo(psoCrr);
                pesoLodoCalculations();
                programaMatrixCalculations();
            }
        });

        prsCrrRev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double psoRev = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    psoRev = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    psoRev = 0;
                }
                eventoAmago.setPresCierreRev(psoRev);
            }
        });

        gncSpr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double gncSpr = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    gncSpr = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    gncSpr = 0;
                }
                eventoAmago.setGananciaSuperficie(gncSpr);
            }
        });


        prsRedBmb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double prsBmb = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    prsBmb = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    prsBmb = 0;
                }
                eventoAmago.setPresReducidaBomba(prsBmb);
                programaMatrixCalculations();
            }
        });

        dspBmb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double dsplBm = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    dsplBm = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    dsplBm = 0;
                }
                eventoAmago.setDesplBomba(dsplBm);
                estroquesHastaCalculations();
                programaMatrixCalculations();
            }
        });


        psoOrgLdo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override public void afterTextChanged(Editable s) {
                String num = s.toString();
                double psoOrg = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    psoOrg = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    psoOrg = 0;
                }
                eventoAmago.setPesoOrglLodo(psoOrg);
                pesoLodoCalculations();
                programaMatrixCalculations();
            }
        });

        prfVrtVrd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double profVrt = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    profVrt = Double.parseDouble(num);
                    eventoAmago.setProfVertical(profVrt);
                    pesoLodoCalculations();
                    programaMatrixCalculations();
                }else if(num.isEmpty()){
                    profVrt = 0;
                }
            }
        });

        prfTtlMed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double prfTtlMed = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    prfTtlMed = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    prfTtlMed = 0;
                }
                eventoAmago.setProfTotal(prfTtlMed);
            }
        });

        prFr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double prFrac = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    prFrac = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    prFrac = 0;
                }
                eventoAmago.setPrFractura(prFrac);
            }
        });

        prPr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String num = s.toString();
                double prPoro = 0;
                if(num.matches("[+-]?([0-9]*[.])?[0-9]+")){
                    prPoro = Double.parseDouble(num);
                }else if(num.isEmpty()){
                    prPoro = 0;
                }
                eventoAmago.setPrPoro(prPoro);
            }
        });



    }

    //Calculations of programa matrix
    private void programaMatrixCalculations() {
        double[][] matrix = eventoAmago.calcPrgrmCircMtrix();


        for(int i=0; i<matrix.length; i++) {
            estroques[i][0].setText(nf.format(matrix[i][0]));
            estroques[i][1].setText(af.format(matrix[i][1]));
        }
    }

    //Calculations of EstroquesHastaBroca/FondoArriba and CirculacionTotal
    private void estroquesHastaCalculations() {
        double hstaBroca = eventoAmago.calcEstroquesABroca();
        double fndoArrba = eventoAmago.calcEstroquesFndArriba();
        double circlTotl = eventoAmago.calcCircTotalPaMatarPozo();

        estrHstBrca.setText(nf.format(hstaBroca));
        estrFndArrba.setText(nf.format(fndoArrba));
        circPrMtrPozo.setText(nf.format(circlTotl));
    }

    //Calculation of aproximate and full version of PesoDLodoPaMatar
    private void pesoLodoCalculations() {
        double psoLodoT = eventoAmago.calcPesoLodoPaMatar();
        af.setRoundingMode(RoundingMode.CEILING);

        pesoLodoAprox.setText(af.format(psoLodoT));
        pesoLodoTotal.setText("(" + df.format(psoLodoT) + ")");
        programaMatrixCalculations();
    }



}