package com.example.appep.UI;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Fragment;

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;


public class AddPozoActivity extends AppCompatActivity{

    FragmentManager fragmentManager;

    AddPozoFragment1 fragment1;
    AddPozoFragment2 fragment2;
    AddPozoFragment3 fragment3;
    AddPozoFragment4 fragment4;

    Button buttonNxt, buttonBfr;

    private int currentFragment;


    //

    private Pozo pozo;
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
        buttonConfiguration();



        /*
        int n = getIntent().getIntExtra("indexTask", -1);


        //if an index is passed as extra change layout to accomodate Update Rather than Add
        if(n != -1){
            pozo = onUpdateGetInfoPozo(n);

            textTitle.setText(R.string.updateTitle);
            addButton.setText(R.string.todo_upd_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePozo();
                }
            });
        }else{

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPozo();
                }
            });
        }
        */
    }

    private void buttonConfiguration() {
        buttonNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (currentFragment){
                    case 1:
                        transaction.replace(R.id.addFragmentContainer, fragment2).commit();
                        currentFragment++;
                        break;
                    case 2:
                        transaction.replace(R.id.addFragmentContainer, fragment3).commit();
                        currentFragment++;
                        break;
                    case 3:
                        transaction.replace(R.id.addFragmentContainer, fragment4).commit();
                        currentFragment++;
                        break;
                    case 4:
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
                        currentFragment--;
                        break;
                }
            }
        });
    }





/*
    //Create new Pozo object and set information in DB, after that start a new main activity
    public void addPozo(){
        MainActivity.mainActivity.finish();

        String nombre = nameText.getText().toString();
        String descripcion = descText.getText().toString();

        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();

        Pozo pozoNuevo = new Pozo(nombre, descripcion);
        ContentValues values = new ContentValues();
        values.put(DBUtilities.TABLA_NOMBRE, pozoNuevo.getNombre());
        values.put(DBUtilities.TABLA_CAMPO, pozoNuevo.getCampo());
        values.put(DBUtilities.TABLA_FECHA_AP, pozoNuevo.getFecha_creacion().toString());
        values.put(DBUtilities.TABLA_ABIERTO, pozoNuevo.isAbierto());

        Long confirmationNum = db.insert(DBUtilities.TABLA_POZO, null, values);
        db.close();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

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

    //Get info on pozo from DB by the index given
    public Pozo onUpdateGetInfoPozo(int n){
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getReadableDatabase();
        String[] whereId = {String.valueOf(n)};
        String[] campos = {DBUtilities.TABLA_NOMBRE, DBUtilities.TABLA_CAMPO, DBUtilities.TABLA_FECHA_AP};

        Cursor cursor = db.query(DBUtilities.TABLA_POZO, campos, DBUtilities.TABLA_ID + "=?" , whereId , null, null, null );
        cursor.moveToFirst();

        final Pozo pozo = new Pozo(n);
        pozo.setNombre(cursor.getString(0));
        pozo.setCampo(cursor.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            Date fecha = dateFormat.parse(cursor.getString(2));
            pozo.setFecha_creacion(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        nameText.setText(pozo.getNombre());
        nameText.setEnabled(false);
        descText.setText(pozo.getCampo());
        descText.setEnabled(false);


        return pozo;
    }
 */


}