package com.example.appep.UI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
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
    private ImageView imgAn1, imgAn2, imgIntD, imgIntB, imgIntC1, imgIntC2;
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

        imgAn1 = findViewById(R.id.imageViewAn1);
        imgAn2 = findViewById(R.id.imageViewAn2);
        imgIntD = findViewById(R.id.imageViewIn);
        imgIntC1 = findViewById(R.id.imageViewIn2_1);
        imgIntC2 = findViewById(R.id.imageViewIn2_2);
        imgIntB = findViewById(R.id.imageViewIn3);

        int n = getIntent().getIntExtra("indexTask", -1);

        broc.setEnabled(false);

        //On each checkbox action change color of figure on the bottom
        rev1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rev1.isChecked()){
                    rev2.setEnabled(true);
                    imgAn1.setImageResource(R.drawable.an_2);
                    imgAn2.setImageResource(R.drawable.an_2);
                }else{
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
                    imgAn1.setImageResource(R.drawable.an_3);
                    imgAn2.setImageResource(R.drawable.an_3);
                }else{
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
                    imgIntD.setImageResource(R.drawable.in_d2);
                }else{
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
                    imgIntD.setImageResource(R.drawable.in_d3);
                }else{
                    imgIntD.setImageResource(R.drawable.in_d2);
                }
            }
        });
        hwdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(hwdp.isChecked()){
                    imgIntC1.setImageResource(R.drawable.in_c2);
                }else{
                    imgIntC1.setImageResource(R.drawable.in_c1);
                }
            }
        });
        dc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(dc.isChecked()){
                    imgIntC2.setImageResource(R.drawable.in_c4);
                }else{
                    imgIntC2.setImageResource(R.drawable.in_c3);
                }
            }
        });
        estb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(estb.isChecked()){
                    imgIntB.setImageResource(R.drawable.in_b2);
                }else{
                    imgIntB.setImageResource(R.drawable.in_b1);
                }
            }
        });


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

        //Set rev1->rev2 and drill1->drill2 checklist dependency
        if(!rev1.isChecked()){
            rev2.setEnabled(false);
        }
        if(!dp1.isChecked()){
            dp2.setEnabled(false);
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