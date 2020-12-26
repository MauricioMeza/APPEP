package com.example.appep.UI;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Fragment;
import android.widget.Toast;

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Componente;
import com.example.appep.Data.Model.Evento;
import com.example.appep.Data.Model.EventoAmago;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Arrays.*;
import java.util.Date;
import java.util.Locale;


public class AddPozoActivity extends AppCompatActivity{

    FragmentManager fragmentManager;

    public AddPozoFragment1 fragment1;
    public AddPozoFragment2 fragment2;
    public AddPozoFragment3 fragment3;
    public AddPozoFragment4 fragment4;

    Button buttonNxt, buttonBfr;

    public static int currentFragment;
    public static boolean addNew;
    public static Pozo pozo;


    private DBSQLiteHelper connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pozo);

        buttonNxt = findViewById(R.id.buttonAddNavNxt);
        buttonBfr = findViewById(R.id.buttonAddNavBfr);

        fragment1 = new AddPozoFragment1();
        fragment2 = new AddPozoFragment2();
        fragment3 = new AddPozoFragment3();
        fragment4 = new AddPozoFragment4();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.addFragmentContainer, fragment1).commit();
        currentFragment = 1;
        addNew = true;
        buttonConfiguration();

        int n = getIntent().getIntExtra("indexTask", -1);

        //if an index is passed as extra change layout to accomodate Update Rather than Add
        if(n != -1){
            addNew = false;
            pozo = onUpdateGetInfoPozo(n);
        }

    }

    //Configure ->Next and <-Before buttons so that different fragments are presented depending on their actions
    //Fill information in Pozo object from the event information filled in each fragment
    //Different cases from AddNew wich involve adding a new event to an already existing Pozo
    private void buttonConfiguration() {
        buttonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (currentFragment){
                    case 1:
                        String[] infoPozo = fragment1.getInfoPozo();

                        //Make sure there is no mistakes in tha information gathering
                        if(infoPozo.length == 1){
                            Toast.makeText(getApplicationContext(), infoPozo[0], Toast.LENGTH_LONG).show();
                            break;
                        }

                        Evento evento = new Evento(fragment1.getComponentSelected());

                        if(addNew){
                            pozo = new Pozo(infoPozo[0], infoPozo[1], Boolean.parseBoolean(infoPozo[2]));

                        }else{
                           pozo.setVertical(Boolean.parseBoolean(infoPozo[2]));
                        }

                        if(!pozo.isVertical()){
                            String str = infoPozo[3];
                            String[] items = str.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                            double[] results = new double[items.length];

                            for (int i = 0; i < items.length; i++) {
                                results[i] = Double.parseDouble(items[i]);
                            }
                            evento.setInfoHztl(results);
                        }

                        pozo.setNewEvento(evento);
                        transaction.replace(R.id.addFragmentContainer, fragment2).commit();
                        currentFragment++;
                        break;
                    case 2:
                        ArrayList<Componente> internalComponents = fragment2.getComponentList();
                        pozo.getEventos().get(0).getEventoInterno().fillComponentesInterno(internalComponents);
                        String val = pozo.getEventos().get(0).getEventoInterno().internalEventValidation();

                        if(val.equals("OK")){
                            transaction.replace(R.id.addFragmentContainer, fragment3).commit();
                            currentFragment++;
                        }else{
                            Toast.makeText(getApplicationContext(), val, Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3:
                        ArrayList<Componente> anularComponents = fragment3.getComponentList();
                        pozo.getEventos().get(0).getEventoAnular().fillComponentesAnular(anularComponents);
                        String valA = pozo.getEventos().get(0).getEventoAnular().anularEventValidation();

                        if(valA.equals("OK")){
                            pozo.getEventos().get(0).calcsTotales();
                            transaction.replace(R.id.addFragmentContainer, fragment4).commit();
                            buttonNxt.setText(R.string.out);
                            currentFragment++;
                        }else{
                            Toast.makeText(getApplicationContext(), valA, Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4:
                        String valB = pozo.getEventos().get(0).getEventoAmago().amagoEventValidation();

                        if(valB.equals("OK")){
                            if(addNew){
                                addPozo();
                            }else{
                                updatePozo();
                            }
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), valB, Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
        buttonBfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (currentFragment){
                    case 1:
                        finish();
                        break;
                    case 2:
                        transaction.replace(R.id.addFragmentContainer, fragment1).commit();
                        currentFragment--;
                        break;
                    case 3:
                        transaction.replace(R.id.addFragmentContainer, fragment2).commit();
                        currentFragment--;
                        break;
                    case 4:
                        transaction.replace(R.id.addFragmentContainer, fragment3).commit();
                        buttonNxt.setText(R.string.nxt);
                        currentFragment--;
                        break;
                }
            }
        });
    }

    //TODO: Refactor the databse writting operation into one for addPozo and addEvent
    //Create new Pozo object and set information in DB, after that start a new main activity
    public void addPozo(){
        MainActivity.mainActivity.finish();

        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();

        //Set info of new Pozo in BD table
        ContentValues values = new ContentValues();
        values.put(DBUtilities.TABLA_NOMBRE, pozo.getNombre());
        values.put(DBUtilities.TABLA_CAMPO, pozo.getCampo());
        values.put(DBUtilities.TABLA_FECHA_AP, pozo.getFecha_creacion().toString());
        values.put(DBUtilities.TABLA_ABIERTO, pozo.isAbierto());
        values.put(DBUtilities.TABLA_VERTICAL, pozo.isVertical());

        Long confirmationNum = db.insert(DBUtilities.TABLA_POZO, null, values);

        //Set info of new event in BD table
        ContentValues valuesEvent = new ContentValues();
        Evento evento = pozo.getEventos().get(0);
        EventoAmago amago = evento.getEventoAmago();
        valuesEvent.put(DBUtilities.EVENTO_POZO, confirmationNum.intValue());
        valuesEvent.put(DBUtilities.EVENTO_FECHA_CR, evento.getFechaCreacion().toString());
        valuesEvent.put(DBUtilities.EVENTO_PESO_LODO, evento.getPesoLodo());
        valuesEvent.put(DBUtilities.EVENTO_TABLA_ESTR, Arrays.deepToString(evento.getTablaEstr()));
        valuesEvent.put(DBUtilities.EVENTO_VOL_TOTAL, evento.getVolTotal());
        valuesEvent.put(DBUtilities.EVENTO_LNG_TOTAL, evento.getLongTotal());
        valuesEvent.put(DBUtilities.AMAGO_PRS_TBO, amago.getPresCierreTubo());
        valuesEvent.put(DBUtilities.AMAGO_PRS_REV, amago.getPresCierreRev());
        valuesEvent.put(DBUtilities.AMAGO_GNC_SUP, amago.getGananciaSuperficie());
        valuesEvent.put(DBUtilities.AMAGO_EST_BRC, amago.getEstrHastaBroca());
        valuesEvent.put(DBUtilities.AMAGO_EST_FNA, amago.getEstrFondoArrb());
        valuesEvent.put(DBUtilities.AMAGO_CRC_TOT, amago.getCircTotalMatarPozo());
        valuesEvent.put(DBUtilities.AMAGO_PR_FRAC, amago.getPrFractura());
        valuesEvent.put(DBUtilities.AMAGO_PR_PORO, amago.getPrPoro());

        Long confirmationNum2 = db.insert(DBUtilities.TABLA_EVENTO, null, valuesEvent);

        db.close();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Get info on pozo from DB by the index given
    public Pozo onUpdateGetInfoPozo(int n){
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getReadableDatabase();

        Cursor cursor = db.rawQuery(DBUtilities.getPozoFromId(n), null);
        cursor.moveToFirst();

        pozo = new Pozo(n);
        pozo.setNombre(cursor.getString(1));
        pozo.setCampo(cursor.getString(2));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            Date fecha = dateFormat.parse(cursor.getString(3));
            pozo.setFecha_creacion(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        pozo.setAbierto(cursor.getInt(4) != 0);
        pozo.setVertical(cursor.getInt(5) != 0);

        return pozo;
    }

    public int getCurrentFragment() { return currentFragment; }


    //Update DB register that matches current Id with new pozo information
    public void updatePozo(){
        MainActivity.mainActivity.finish();
        ViewPozoActivity.viewActivity.finish();
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();


        ContentValues values = new ContentValues();
        String[] parametros = {String.valueOf(pozo.getId())};

        values.put(DBUtilities.TABLA_VERTICAL, pozo.isVertical());

        int confirmationNum = db.update(DBUtilities.TABLA_POZO, values, DBUtilities.TABLA_ID + "=?", parametros );

        ContentValues valuesEvent = new ContentValues();
        Evento evento = pozo.getEventos().get(0);
        EventoAmago amago = evento.getEventoAmago();
        valuesEvent.put(DBUtilities.EVENTO_POZO, pozo.getId());
        valuesEvent.put(DBUtilities.EVENTO_FECHA_CR, evento.getFechaCreacion().toString());
        valuesEvent.put(DBUtilities.EVENTO_PESO_LODO, evento.getPesoLodo());
        valuesEvent.put(DBUtilities.EVENTO_TABLA_ESTR, Arrays.deepToString(evento.getTablaEstr()));
        valuesEvent.put(DBUtilities.EVENTO_VOL_TOTAL, evento.getVolTotal());
        valuesEvent.put(DBUtilities.EVENTO_LNG_TOTAL, evento.getLongTotal());

        valuesEvent.put(DBUtilities.AMAGO_PRS_TBO, amago.getPresCierreTubo());
        valuesEvent.put(DBUtilities.AMAGO_PRS_REV, amago.getPresCierreRev());
        valuesEvent.put(DBUtilities.AMAGO_GNC_SUP, amago.getGananciaSuperficie());
        valuesEvent.put(DBUtilities.AMAGO_EST_BRC, amago.getEstrHastaBroca());
        valuesEvent.put(DBUtilities.AMAGO_EST_FNA, amago.getEstrFondoArrb());
        valuesEvent.put(DBUtilities.AMAGO_CRC_TOT, amago.getCircTotalMatarPozo());
        valuesEvent.put(DBUtilities.AMAGO_PR_FRAC, amago.getPrFractura());
        valuesEvent.put(DBUtilities.AMAGO_PR_PORO, amago.getPrPoro());

        Long confirmationNum2 = db.insert(DBUtilities.TABLA_EVENTO, null, valuesEvent);

        db.close();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }



}