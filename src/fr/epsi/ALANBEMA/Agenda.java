package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import fr.epsi.Planning.PlanningEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by benjamin on 15/05/14.
 */
public class Agenda extends Activity{

    private Calendar dateDebutSemaine;
    private GridLayout gridLayout;
    private List<PlanningEvent> evenements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.agenda);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            this.dateDebutSemaine = Calendar.getInstance(TimeZone.getDefault());

            this.dateDebutSemaine.setTime(dateFormat.parse("26/05/2014 00:00"));

            this.evenements = new ArrayList<PlanningEvent>();

            this.evenements.add(new PlanningEvent(dateFormat.parse("27/05/2014 12:00"), dateFormat.parse("27/05/2014 20:00"), "Test", R.drawable.typeevent_1));

            this.gridLayout = (GridLayout) findViewById(R.id.gridViewPlanning);

            this.updatePlanning();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void updatePlanning() {

        DateFormat dateFormat = new SimpleDateFormat("cccc dd/MM", Locale.FRANCE);

        Calendar l_date = Calendar.getInstance(TimeZone.getDefault());
        Calendar l_dateFinSemaine = Calendar.getInstance(TimeZone.getDefault());

        l_date.setTime(this.dateDebutSemaine.getTime());
        l_date.add(Calendar.DATE, -1);

        l_dateFinSemaine.setTime(this.dateDebutSemaine.getTime());
        l_dateFinSemaine.add(Calendar.DATE, 6);

        this.gridLayout.removeAllViews();

        // On créer chaque ligne
        for (int _heure = 0; _heure < 24; _heure++) {
            this.gridLayout.addView(createTextView(String.valueOf(_heure).concat("H"), _heure + 1, 0, R.color.grey_dark, _heure == 12, false));
        }

        for (int column = 1; column < 8; column++) {
            l_date.add(Calendar.DATE, 1);
            this.gridLayout.addView(createTextView(dateFormat.format(l_date.getTime()), 0, column, R.color.grey_dark, false, false, 250));

            for (int row = 1; row < 25; row++) {

                int _duree = 0;
                float _colonne;
                int nbEvenement = 0;

                for (PlanningEvent _evenement : this.evenements) {
                    if (_evenement.getDateDebut().getHours() - 1 == row) {
                        _duree = _evenement.getDateFin().getHours() - _evenement.getDateDebut().getHours();
                        _colonne = _evenement.getDateDebut().getDate() - this.dateDebutSemaine.getTime().getDate() + 1;
                        this.gridLayout.addView(createButton(_evenement.getLibelle(), _evenement.getDateDebut().getHours() + 1, (int) _colonne, _duree, _evenement.getDrawable(), false, false));
                    }
                }
                if (nbEvenement == 0) {
                    this.gridLayout.addView(createTextView(" ", row, column, R.color.grey_light, row == 12, column == 2));
                }
            }
        }
    }

    private TextView createTextView(String label, int row, int column, int color, boolean borderBottom, boolean borderRight) {
        return this.createTextView(label, row, column, color, borderBottom, borderRight, 200);
    }

    private TextView createTextView(String label, int row, int column, int color, boolean borderBottom, boolean borderRight, int width) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(label);
        textView.setWidth(width);
        textView.setBackgroundColor(getResources().getColor(color));
        textView.setPadding(5, 5, 5, 5);
        setLayoutAndBorder(textView,
                new GridLayout.LayoutParams(
                        GridLayout.spec(row, GridLayout.FILL),
                        GridLayout.spec(column, GridLayout.FILL)),
                borderBottom, true, true, borderRight
        );
        return textView;
    }

    private Space createSpace(int row, int column, int rowspan, int columnspan) {
        Space space = new Space(this);
        space.setLayoutParams(new GridLayout.LayoutParams(
                GridLayout.spec(row, rowspan, GridLayout.FILL),
                GridLayout.spec(column, columnspan, GridLayout.FILL)));
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
                borderBottom, true, true, borderRight
        );
        button.setClickable(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), label, Toast.LENGTH_LONG).show();
            }
        });
        return button;
    }

    private void setLayoutAndBorder(View view, GridLayout.LayoutParams params,boolean borderBottom, boolean borderTop, boolean borderLeft, boolean borderRight) {
        params.bottomMargin = borderBottom ? 1 : 0;
        params.leftMargin = borderLeft ? 1 : 0;
        params.rightMargin = borderRight ? 1 : 0;
        params.topMargin = borderTop ? 1 : 0;
        view.setLayoutParams(params);
    }
}
