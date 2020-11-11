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

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Componente;
import com.example.appep.Data.Model.Evento;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private void buttonConfiguration() {
        //TODO: Make sure that information from Pozo in the activity is retrieved when a form fragment is created (Specially in fragment 2 and 3)
        buttonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (currentFragment){
                    case 1:
                        String[] infoPozo = fragment1.getInfoPozo();
                        Evento evento = new Evento(fragment1.getComponentSelected());
                        pozo = new Pozo(infoPozo[0], infoPozo[1], infoPozo[2]);
                        pozo.setNewEvento(evento);
                        transaction.replace(R.id.addFragmentContainer, fragment2).commit();
                        currentFragment++;
                        break;
                    case 2:
                        transaction.replace(R.id.addFragmentContainer, fragment3).commit();
                        ArrayList<Componente> internalComponents = fragment2.getComponentList();
                        pozo.getEventos().get(0).getEventoInterno().fillComponentesInterno(internalComponents);
                        currentFragment++;
                        break;
                    case 3:
                        transaction.replace(R.id.addFragmentContainer, fragment4).commit();
                        ArrayList<Componente> anularComponents = fragment3.getComponentList();
                        pozo.getEventos().get(0).getEventoAnular().fillComponentesAnular(anularComponents);
                        buttonNxt.setText(R.string.out);
                        currentFragment++;
                        break;
                    case 4:
                        if(addNew){
                            addPozo();
                        }else{
                            //updatePozo();
                        }

                        finish();
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

    //Create new Pozo object and set information in DB, after that start a new main activity
    public void addPozo(){
        MainActivity.mainActivity.finish();

        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(DBUtilities.TABLA_NOMBRE, pozo.getNombre());
        values.put(DBUtilities.TABLA_CAMPO, pozo.getCampo());
        values.put(DBUtilities.TABLA_FECHA_AP, pozo.getFecha_creacion().toString());
        values.put(DBUtilities.TABLA_ABIERTO, pozo.isAbierto());

        Long confirmationNum = db.insert(DBUtilities.TABLA_POZO, null, values);
        db.close();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //Get info on pozo from DB by the index given
    public Pozo onUpdateGetInfoPozo(int n){
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getReadableDatabase();
        String[] whereId = {String.valueOf(n)};
        String[] campos = {DBUtilities.TABLA_NOMBRE, DBUtilities.TABLA_CAMPO, DBUtilities.TABLA_FECHA_AP};

        Cursor cursor = db.query(DBUtilities.TABLA_POZO, campos, DBUtilities.TABLA_ID + "=?" , whereId , null, null, null );
        cursor.moveToFirst();

        pozo.setNombre(cursor.getString(0));
        pozo.setCampo(cursor.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            Date fecha = dateFormat.parse(cursor.getString(2));
            pozo.setFecha_creacion(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return pozo;
    }

    public int getCurrentFragment() { return currentFragment; }

    /*
    //Update DB register that matches current Id with new pozo information
    public void updatePozo(){
        MainActivity.mainActivity.finish();
        ViewPozoActivity.viewActivity.finish();
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();


        ContentValues values = new ContentValues();
        String[] parametros = {String.valueOf(pozo.getId())};
        values.put(DBUtilities.TABLA_NOMBRE, pozo.getNombre());
        values.put(DBUtilities.TABLA_CAMPO, pozo.getCampo());

        int confirmationNum = db.update(DBUtilities.TABLA_POZO, values, DBUtilities.TABLA_ID + "=?", parametros );
        db.close();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
 */


}