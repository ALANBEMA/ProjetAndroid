package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import fr.epsi.Planning.PlanningEvent;

import java.util.Date;
import java.util.List;

/**
 * Created by benjamin on 15/05/14.
 */
public class Agenda extends Activity{

    private Date dateDebutSemaine;
    private Date dateFinSemaine;
    private GridLayout gridLayout;
    private List<PlanningEvent> evenements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);

        this.gridLayout = (GridLayout)findViewById(R.id.gridViewPlanning);
        this.updatePlanning();
    }

    private void updatePlanning(){
        this.gridLayout.removeAllViews();

        this.gridLayout.addView(createTextView("Lundi", 0, 1, R.color.grey_dark, false, false));
        this.gridLayout.addView(createTextView("Mardi", 0, 2, R.color.grey_dark, false, false));
        this.gridLayout.addView(createTextView("Mercredi", 0, 3, R.color.grey_dark, false, false));
        this.gridLayout.addView(createTextView("Jeudi", 0, 4, R.color.grey_dark, false, false));
        this.gridLayout.addView(createTextView("Vendredi", 0, 5, R.color.grey_dark, false, false));
        this.gridLayout.addView(createTextView("Samedi", 0, 6, R.color.grey_dark, false, false));
        this.gridLayout.addView(createTextView("Dimanche", 0, 7, R.color.grey_dark, false, false));

        // On créer chaque ligne
        for(int _heure = 0;_heure<24;_heure++)
        {
            this.gridLayout.addView(createTextView(String.valueOf(_heure).concat("H"),_heure+1,0,R.color.grey_dark,_heure == 12,false));
        }

        for(PlanningEvent _evenement:this.evenements)
        {
            int _duree = 0;
            float _colonne;

            if(_evenement.getDateFin().after(this.dateDebutSemaine) && _evenement.getDateDebut().before(this.dateFinSemaine))
            {
                _duree = _evenement.getDateDebut().getHours() - _evenement.getDateFin().getHours();
                _colonne = _evenement.getDateDebut().getDate() - this.dateDebutSemaine.getDate();
            }
        }

        /*for (int column = 1; column < 7; column++) {
            for (int row = 0; row < 24; row++) {
                PlanningEvent eventfound = null;
                int rowspan = 1;
                for (PlanningEvent event : evenements) {
                    if (row == event.getHeureDebut()-1) {
                        eventfound = event;
                        rowspan = event.getHeureDebut() - event.getHeureDebut();
                    }
                }
                if (eventfound == null) {
                    this.gridLayout.addView(createTextView(" ", row, column,R.color.grey_light, row == 12, column == 2));
                } else {
                    this.gridLayout.addView(createButton(eventfound.getLibelle(), row, column, rowspan, eventfound.getDrawable(), row == 11, column == 2));
                    if (rowspan > 1) {
                        row = row + rowspan - 1;
                    }
                }
            }
        }*/
    }

    private TextView createTextView(String label, int row, int column, int color, boolean borderBottom, boolean borderRight) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(label);
        textView.setWidth(200);
        textView.setBackgroundColor(getResources().getColor(color));
        textView.setPadding(5, 5, 5, 5);
        setLayoutAndBorder(textView,
                new GridLayout.LayoutParams(
                        GridLayout.spec(row, GridLayout.FILL),
                        GridLayout.spec(column, GridLayout.FILL)),
                borderBottom, true, true, borderRight);
        return textView;
    }

    private Space createSpace(int row, int column, int rowspan, int columnspan) {
        Space space = new Space(this);
        space.setLayoutParams(new GridLayout.LayoutParams(
                GridLayout.spec(row,rowspan, GridLayout.FILL),
                GridLayout.spec(column,columnspan, GridLayout.FILL)));
        space.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        return space;
    }
    private Button createButton(final String label, int row, int column, int rowspan, int drawable, boolean borderBottom, boolean borderRight) {
        Button button = new Button(this);
        button.setGravity(Gravity.CENTER);
        button.setText(label);
        button.setBackgroundResource(drawable);
        button.setPadding(5, 5, 5, 5);
        setLayoutAndBorder(button,
                new GridLayout.LayoutParams(
                        GridLayout.spec(row, rowspan, GridLayout.FILL),
                        GridLayout.spec(column, GridLayout.FILL)),
                borderBottom, true, true, borderRight);
        button.setClickable(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), label, Toast.LENGTH_LONG).show();
            }
        });
        return button;
    }

    private void setLayoutAndBorder(View view, GridLayout.LayoutParams params,
                                    boolean borderBottom, boolean borderTop, boolean borderLeft, boolean borderRight) {
        params.bottomMargin = borderBottom ? 1 : 0;
        params.leftMargin = borderLeft ? 1 : 0;
        params.rightMargin = borderRight ? 1 : 0;
        params.topMargin = borderTop ? 1 : 0;
        view.setLayoutParams(params);
    }
}
