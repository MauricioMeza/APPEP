package com.example.appep.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;


public class ViewPozoActivity extends AppCompatActivity {

    private TextView textViewNombre, textViewDesc, textViewFecha, textViewNumr, textViewAct;
    private Button  buttonDelete, buttonUpdate, buttonComplete;
    public static Activity viewActivity;
    private Pozo pozoInfo;
    DBSQLiteHelper connect;

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

        buttonDelete = findViewById(R.id.buttonViewDel);
        buttonUpdate = findViewById(R.id.buttonViewUpd);
        buttonComplete = findViewById(R.id.buttonViewCmp);

        //Get info from selectedPozo and present it in layout TextViews
        pozoInfo = (Pozo) getIntent().getSerializableExtra("pozo");
        textViewNombre.setText(pozoInfo.getNombre());
        textViewDesc.setText(pozoInfo.getCampo());
        textViewFecha.setText(pozoInfo.getFecha_creacion().toString());
        textViewNumr.setText(String.valueOf(pozoInfo.getId()));

        // Change button options to disabled if pozo is not open
        if(pozoInfo.isAbierto()){
            textViewAct.setText("Si");
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
            textViewAct.setText("No");
            buttonUpdate.setEnabled(false);
            buttonComplete.setEnabled(false);
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
}
