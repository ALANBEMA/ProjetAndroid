package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Accueil extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button Bajouter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnAgenda){
                    Intent intent = new Intent(v.getContext(),Agenda.class);
                    startActivity(intent);
                }
                else if(v.getId() == R.id.btnPersonnel){

                    Intent intent = new Intent(v.getContext(),PersonnelActivity.class);
                    startActivity(intent);
                }
            }
        };



        ImageButton btnAgenda = (ImageButton)findViewById(R.id.btnAgenda);
        ImageButton btnPersonnel = (ImageButton)findViewById(R.id.btnPersonnel);
        btnAgenda.setOnClickListener(listener);
        btnPersonnel.setOnClickListener(listener);

        Bajouter = (Button) findViewById(R.id.bajoutplanning);
        Bajouter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == Bajouter){


            Intent intent = new Intent(Accueil.this, AddPlanning.class);
            startActivity(intent);
        }

    }
}
