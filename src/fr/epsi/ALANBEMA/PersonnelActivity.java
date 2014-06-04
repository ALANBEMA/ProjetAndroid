package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import fr.epsi.BaseDeDonnees.BDD;

import java.util.HashMap;

/**
 * Created by alexandredouchin on 02/06/2014.
 */
public class PersonnelActivity extends ListActivity implements View.OnClickListener {
    BDD bdd;
    Cursor ClistPersonnel;
    private Button Bajouter;
    final int id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnel);

        bdd = new BDD();

        bdd.open(this);

        ClistPersonnel = bdd.getInfosPersonnel();
        startManagingCursor(ClistPersonnel);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, ClistPersonnel,
                new String[]{"nom" , "emploi"}, new int[]{ android.R.id.text1, android.R.id.text2}
        );
        setListAdapter(adapter);

        Bajouter = (Button) findViewById(R.id.buttonAddPersonnel);
        Bajouter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == Bajouter){
            Intent intent = new Intent(PersonnelActivity.this, AddPersonnelActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long idInfo){
       super.onListItemClick(l, v, position, idInfo);

        Log.i("Tuto", " " + position);



        Intent intent = new Intent(PersonnelActivity.this, AddPersonnelActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
