package com.example.appep.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.appep.Data.Local.DBSQLiteHelper;
import com.example.appep.Data.Local.DBUtilities;
import com.example.appep.Data.Model.Pozo;
import com.example.appep.R;
import com.example.appep.UI.RecyclerViewClasses.PozoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingAddToDoButton;
    private static RecyclerView recyclerToDoView;
    public static Activity mainActivity;
    DBSQLiteHelper conect;
    ArrayList<Pozo> listaPozos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get list of Tasks for DB and show them in RecyclerView
        listaPozos = new ArrayList<>();
        conect = new DBSQLiteHelper(this, null);
        try {
            getPozosFromDB();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        recyclerToDoView = findViewById(R.id.recyclerToDoView);
        recyclerToDoView.setLayoutManager(new LinearLayoutManager(this));
        PozoAdapter adapter = new PozoAdapter(listaPozos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n =recyclerToDoView.getChildAdapterPosition(v);
                selectPozo(n);
            }
        });
        recyclerToDoView.setAdapter(adapter);

        // Add Task button
        floatingAddToDoButton = findViewById(R.id.floatingButtonAddTodo);
        floatingAddToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPozo(v);
            }
        });

    }

    //Start Activity to add a new Pozo
    public void addPozo(View view){
        Intent i = new Intent(this, AddPozoActivity.class);
        startActivity(i);
    }

    //Get a full list of Pozos from DB
    public void getPozosFromDB() throws ParseException {
        SQLiteDatabase db = conect.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBUtilities.TABLE_GET_SQL, null);

        //Put all Pozo Info into a Pozo object and add it into an ArrayList
        while(cursor.moveToNext()){
            Pozo pozo = new Pozo(cursor.getInt(0));
            pozo.setNombre(cursor.getString(1));
            pozo.setCampo(cursor.getString(2));
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
            Date fecha = dateFormat.parse(cursor.getString(3));
            pozo.setFecha_creacion(fecha);
            boolean active = (cursor.getInt(4) != 0);
            pozo.setAbierto(active);

            listaPozos.add(pozo);
        }
        db.close();
    }

    //Start Activity to View information of selected Pozo
    public void selectPozo(int n){
        Pozo pozoSelected = listaPozos.get(n);
        Intent i = new Intent(this, ViewPozoActivity.class);
        i.putExtra("pozo", pozoSelected);
        startActivity(i);
    }

}