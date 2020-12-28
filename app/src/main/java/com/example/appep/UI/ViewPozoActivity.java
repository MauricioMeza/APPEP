package com.example.appep.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Evento;
import com.example.appep.Data.Model.EventoAmago;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;
import com.example.appep.UI.RecyclerViewClasses.EventAdapter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ViewPozoActivity extends AppCompatActivity {

    private TextView textViewNombre, textViewDesc, textViewFecha, textViewNumr, textViewAct, textViewVer;
    private RecyclerView eventRecyclerView;
    private Button  buttonDelete, buttonUpdate, buttonComplete, buttonHistory;
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
        eventRecyclerView = findViewById(R.id.recyclerViewEvents);

        buttonDelete = findViewById(R.id.buttonViewDel);
        buttonUpdate = findViewById(R.id.buttonViewUpd);
        buttonComplete = findViewById(R.id.buttonViewCmp);
        buttonHistory = findViewById(R.id.buttonViewHis);

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

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { deletePozo(pozoInfo.getId());
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventHistoryDialog eventHistoryDialog = new EventHistoryDialog(pozoInfo.getEventos());
                eventHistoryDialog.show(getSupportFragmentManager(), "HISTORY_DIALOG");
            }
        });

        ArrayList ultimoEvento = new ArrayList<Evento>();
        ultimoEvento.add(pozoInfo.getEventos().get(0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        eventRecyclerView.setLayoutManager(linearLayoutManager);
        eventRecyclerView.setAdapter(new EventAdapter(ultimoEvento));
        eventRecyclerView.setFocusable(false);
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

    //Run a SQL query that looks for every event in the DB with th ID of the Pozo and adds them to the pozo object
    public Pozo getEventsFromPozo(Pozo pozo) throws ParseException {
        SQLiteDatabase db = connect.getReadableDatabase();
        Cursor c = db.rawQuery(DBUtilities.getEventosFromPozo(pozo.getId()), null);
        while (c.moveToNext()){
            Evento evento = new Evento(c.getInt(0));
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
            Date fecha = dateFormat.parse(c.getString(2));
            evento.setFechaCreacion(fecha);
            evento.setTablaEstr(getMatrixFromToString(c.getString(3)));
            evento.setPesoLodo(c.getDouble(4));
            evento.setLongTotal(c.getDouble(5));
            evento.setVolTotal(c.getDouble(6));

            EventoAmago eventoAmago = new EventoAmago(evento);
            eventoAmago.setPresCierreTubo(c.getDouble(7));
            eventoAmago.setPresCierreRev(c.getDouble(8));
            eventoAmago.setGananciaSuperficie(c.getDouble(9));
            eventoAmago.setEstrHastaBroca(c.getDouble(10));
            eventoAmago.setEstrFondoArrb(c.getDouble(11));
            eventoAmago.setCircTotalMatarPozo(c.getDouble(12));
            eventoAmago.setPrFractura(c.getDouble(13));
            eventoAmago.setPrPoro(c.getDouble(14));

            evento.setEventoAmago(eventoAmago);

            pozo.setNewEvento(evento);
        }

        return pozo;
    }

    //Receives a String version of a 2 dimension array and transforms it into a 2 dimension array
    public double[][] getMatrixFromToString(String matrixString){
        int cols = 2;
        matrixString = matrixString.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] filaString = matrixString.split(",");
        int rows = filaString.length/2;

        double[][] matrix = new double[rows][cols];

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
