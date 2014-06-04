package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by benjamin on 04/06/14.
 */
public class AccueilAgenda extends Activity {

    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainagenda);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId() == R.id.btnPlanning){
                        Intent intent = new Intent(v.getContext(),Agenda.class);
                        startActivity(intent);
                    }
                }
            };

            Button btnPlanning = (Button)findViewById(R.id.btnPlanning);
            btnPlanning.setOnClickListener(listener);
    }
}
