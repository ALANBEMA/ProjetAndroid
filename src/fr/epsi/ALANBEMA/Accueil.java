package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Accueil extends Activity{
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnAgenda){
                    Intent intent = new Intent(v.getContext(),AccueilAgenda.class);
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
    }
}
