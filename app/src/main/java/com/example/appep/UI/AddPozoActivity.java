package com.example.appep.UI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPozoActivity extends AppCompatActivity {

    private Button addButton;
    private CheckBox dp1, dp2, hwdp, dc,broc, estb, rev1, rev2;
    private EditText nameText, descText;
    private TextView textTitle;
    DBSQLiteHelper connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pozo);

        textTitle = findViewById(R.id.textTitleAdd);
        addButton = findViewById(R.id.todoAddButton);
        nameText = findViewById(R.id.editTextToDoName);
        descText = findViewById(R.id.editTextToDoDesc);
        dp1 = findViewById(R.id.checkBoxDP1);
        dp2 = findViewById(R.id.checkBoxDP2);
        dc = findViewById(R.id.checkBoxDC);
        hwdp = findViewById(R.id.checkBoxHWDP);
        broc = findViewById(R.id.checkBoxBroca);
        estb = findViewById(R.id.checkBoxEstabilizador);
        rev1 = findViewById(R.id.checkBoxRev1);
        rev2 = findViewById(R.id.checkBoxRev2);

        int n = getIntent().getIntExtra("indexTask", -1);

        broc.setEnabled(false);

        //if an index is passed as extra change layout to accomodate Update Rather than Add while gettig info on the Pozo in DB with that index
        if(n != -1){
            addButton.setText(R.string.todo_upd_button);
            textTitle.setText(R.string.updateTitle);

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

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePozo(nameText.getText().toString(), descText.getText().toString(), pozo);
                }
            });
        }else{

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPozo(nameText.getText().toString(), descText.getText().toString());
                }
            });
        }
    }

    //Create new Pozo object and set information in DB, after that start a new main activity
    public void addPozo(String nombre, String descripcion){
        MainActivity.mainActivity.finish();
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
    public void updatePozo(String nombre, String campo, Pozo currentPozo){
        MainActivity.mainActivity.finish();
        ViewPozoActivity.viewActivity.finish();
        connect = new DBSQLiteHelper(this, null);
        SQLiteDatabase db = connect.getWritableDatabase();

        currentPozo.setNombre(nombre);
        currentPozo.setCampo(campo);

        ContentValues values = new ContentValues();
        String[] parametros = {String.valueOf(currentPozo.getId())};
        values.put(DBUtilities.TABLA_NOMBRE, currentPozo.getNombre());
        values.put(DBUtilities.TABLA_CAMPO, currentPozo.getCampo());

        int confirmationNum = db.update(DBUtilities.TABLA_POZO, values, DBUtilities.TABLA_ID + "=?", parametros );
        db.close();

        final Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}