package com.example.appep.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Evento;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ViewPozoActivity extends AppCompatActivity {

    private TextView textViewNombre, textViewDesc, textViewFecha, textViewNumr, textViewAct, textViewVer;
    private TextView textViewEventPsoldo1, textViewEventPsoldo2, textViewEventVol, textViewEventLng;
    private int[][] names = {{R.id.textViewEst1,R.id.textViewPrs1},{R.id.textViewEst2,R.id.textViewPrs2},{R.id.textViewEst3,R.id.textViewPrs3},{R.id.textViewEst4,R.id.textViewPrs4},{R.id.textViewEst5,R.id.textViewPrs5},
                             {R.id.textViewEst6,R.id.textViewPrs6},{R.id.textViewEst7,R.id.textViewPrs7},{R.id.textViewEst8,R.id.textViewPrs8},{R.id.textViewEst9,R.id.textViewPrs9},{R.id.textViewEst10,R.id.textViewPrs10},{R.id.textViewEst11,R.id.textViewPrs11}};
    private TextView[][] estroques = new TextView[11][2];
    private Button  buttonDelete, buttonUpdate, buttonComplete;
    public static Activity viewActivity;
    private Pozo pozoInfo;
    DBSQLiteHelper connect;
    private static DecimalFormat df = new DecimalFormat("#.#####");
    private static DecimalFormat af = new DecimalFormat("#.#");
    private static DecimalFormat nf = new DecimalFormat("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pozo);
        connect = new DBSQLiteHelper(this, null);


        //Referencing Variables with layoutItems
        textViewNombre = findViewById(R.id.textViewNombreBlank);
        textViewDesc = findViewById(R.id.textViewDescripcionBlank);
        textViewFecha = findViewById(R.id.textViewFechaBlank);
        textViewNumr = findViewById(R.id.textViewIdBlank);
        textViewAct = findViewById(R.id.textViewAbiertoBlank);
        textViewVer = findViewById(R.id.textViewTipoBlank);

        textViewEventPsoldo1 = findViewById(R.id.textViewPesolodoBlank1);
        textViewEventPsoldo2 = findViewById(R.id.textViewPesolodoBlank2);
        textViewEventVol = findViewById(R.id.textViewVolBlank);
        textViewEventLng = findViewById(R.id.textViewLngBlank);

        for(int i=0; i<names.length; i++){
            estroques[i][0] = findViewById(names[i][0]);
            estroques[i][1] = findViewById(names[i][1]);
        }

        buttonDelete = findViewById(R.id.buttonViewDel);
        buttonUpdate = findViewById(R.id.buttonViewUpd);
        buttonComplete = findViewById(R.id.buttonViewCmp);

        //Get info from selectedPozo and present it in layout TextViews
        pozoInfo = (Pozo) getIntent().getSerializableExtra("pozo");
        try {
            pozoInfo = getEventsFromPozo(pozoInfo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        textViewNombre.setText(pozoInfo.getNombre());
        textViewDesc.setText(pozoInfo.getCampo());
        textViewFecha.setText(pozoInfo.getFecha_creacion().toString());
        textViewNumr.setText(String.valueOf(pozoInfo.getId()));
        if(pozoInfo.isVertical()){
            textViewVer.setText(R.string.todo_tpvr);
        }else{
            textViewVer.setText(R.string.todo_tphz);
        }

        // Change button options to disabled if pozo is not open
        if(pozoInfo.isAbierto()){
            textViewAct.setText(R.string.yes);
            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { updatePozo(pozoInfo.getId());
                }
            });
            buttonComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { closePozo(pozoInfo.getId());
                }
            });
        }else{
            textViewAct.setText(R.string.no);
            buttonUpdate.setEnabled(false);
            buttonComplete.setEnabled(false);
        }

        Evento ultimoEvento = pozoInfo.getEventos().get(0);
        af.setRoundingMode(RoundingMode.CEILING);
        textViewEventPsoldo1.setText(af.format(ultimoEvento.getPesoLodo()));
        textViewEventPsoldo2.setText("(" + df.format(ultimoEvento.getPesoLodo()) + ")");
        textViewEventVol.setText(df.format(ultimoEvento.getVolTotal()));
        textViewEventLng.setText(df.format(ultimoEvento.getLongTotal()));

        double[][] matrix = ultimoEvento.getTablaEstr();
        for(int i=0; i<matrix.length; i++) {
            estroques[i][0].setText(nf.format(matrix[i][0]));
            estroques[i][1].setText(af.format(matrix[i][1]));
        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { deletePozo(pozoInfo.getId());
            }
        });
    }

    //Delete register from DB, finish and open MainActivity
    public void deletePozo(int n){
        SQLiteDatabase db = connect.getWritableDatabase();
        String[] paramts = {String.valueOf(n)};
        db.delete(DBUtilities.TABLA_POZO, DBUtilities.TABLA_ID + "=?", paramts);
        db.delete(DBUtilities.TABLA_EVENTO, DBUtilities.EVENTO_POZO + "=?", paramts);

        MainActivity.mainActivity.finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    //Open the Add_Pozo activity with current Pozo Information sent
    public void updatePozo(int n){
        Intent i = new Intent(this, AddPozoActivity.class);
        i.putExtra("indexTask", n);
        startActivity(i);
    }

    //Set current pozo as closed and update DB with new info and go back to MainActivity
    public void closePozo(int n){
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();

        pozoInfo.setAbierto(false);

        ContentValues values = new ContentValues();
        String[] parametros = {String.valueOf(n)};
        values.put(DBUtilities.TABLA_ABIERTO, pozoInfo.isAbierto());

        int confirmationNum = db.update(DBUtilities.TABLA_POZO, values, DBUtilities.TABLA_ID + "=?", parametros );
        db.close();

        MainActivity.mainActivity.finish();
        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public Pozo getEventsFromPozo(Pozo pozo) throws ParseException {
        SQLiteDatabase db = connect.getReadableDatabase();
        Cursor c = db.rawQuery(DBUtilities.getEventosFromPozo(pozo.getId()), null);
        while (c.moveToNext()){
            Evento evento = new Evento(c.getInt(0));
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
            Date fecha = dateFormat.parse(c.getString(2));
            evento.setFechaCreacion(fecha);
            evento.setTablaEstr(getMatrixFromToString(c.getString(3), 10));
            evento.setPesoLodo(c.getDouble(4));
            evento.setLongTotal(c.getDouble(5));
            evento.setVolTotal(c.getDouble(6));
            pozo.setNewEvento(evento);
        }

        return pozo;
    }

    public double[][] getMatrixFromToString(String matrixString, int rows){
        int cols = 2;
        double[][] matrix = new double[rows][cols];
        matrixString = matrixString.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] filaString = matrixString.split(",");

        int iterator = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                matrix[i][j] = Double.parseDouble(filaString[iterator]);
                iterator++;
            }
        }

        return matrix;
    }
}
