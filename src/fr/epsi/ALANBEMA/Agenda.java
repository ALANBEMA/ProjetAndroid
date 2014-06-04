package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import fr.epsi.BaseDeDonnees.BDD;
import fr.epsi.BaseDeDonnees.Planning;
import fr.epsi.Planning.PlanningEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by benjamin on 15/05/14.
 */
public class Agenda extends Activity {

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

            this.gridLayout = (GridLayout) findViewById(R.id.gridViewPlanning);

            this.updatePlanning();

            this.updateSpinner();

            Button btnValiderChangementDate = (Button) findViewById(R.id.btnValiderDateAgenda);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.btnValiderDateAgenda) {
                        Calendar calendar = Calendar.getInstance();

                        //On vide le calendrier
                        calendar.clear();

                        calendar.set(Calendar.WEEK_OF_YEAR, (Integer) ((Spinner) findViewById(R.id.spWeek)).getSelectedItem());
                        calendar.set(Calendar.YEAR, (Integer) ((Spinner) findViewById(R.id.spYear)).getSelectedItem());

                        Date dateDebut = calendar.getTime();

                        dateDebutSemaine.setTime(dateDebut);

                        // On ajoute un jour pour obtenir Lundi
                        dateDebutSemaine.add(Calendar.DATE,1);

                        updatePlanning();
                    }
                }
            };

            btnValiderChangementDate.setOnClickListener(listener);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met a jour le planning
     */
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
            this.gridLayout.addView(createTextView(String.valueOf(_heure).concat("H"), _heure + 1, 0, R.color.grey_dark, false, false));
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
                        if (_evenement.getDateFin().getDate() - _evenement.getDateDebut().getDate() >= 1) {
                            _duree = 24 - _evenement.getDateDebut().getHours();
                            _colonne = _evenement.getDateDebut().getDate() - this.dateDebutSemaine.getTime().getDate() + 1;
                            this.gridLayout.addView(createButton(_evenement, _evenement.getDateDebut().getHours() + 1, (int) _colonne, _duree, _evenement.getDrawable(), false, false));

                            _duree = _evenement.getDateFin().getHours();
                            _colonne = _evenement.getDateFin().getDate() - this.dateDebutSemaine.getTime().getDate() + 1;
                            this.gridLayout.addView(createButton(_evenement, 1, (int) _colonne, _duree, _evenement.getDrawable(), false, false));
                        } else {
                            _duree = _evenement.getDateFin().getHours() - _evenement.getDateDebut().getHours();
                            _colonne = _evenement.getDateDebut().getDate() - this.dateDebutSemaine.getTime().getDate() + 1;
                            this.gridLayout.addView(createButton(_evenement, _evenement.getDateDebut().getHours() + 1, (int) _colonne, _duree, _evenement.getDrawable(), false, false));
                        }
                    }
                }
                if (nbEvenement == 0) {
                    this.gridLayout.addView(createTextView(" ", row, column, R.color.grey_light, row == 12, column == 2));
                }
            }
        }
    }

    /**
     * Met a jour les spinners
     */
    private void updateSpinner() {
        ArrayList<Integer> listeSemaines = new ArrayList<Integer>();
        ArrayList<Integer> listeAnnees = new ArrayList<Integer>();
        Calendar calendarMax = Calendar.getInstance();
        Calendar aujourdhui = Calendar.getInstance();

        calendarMax.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarMax.set(Calendar.DAY_OF_MONTH, 31);

        int jour = calendarMax.get(Calendar.DAY_OF_YEAR);
        int jourSemaine = calendarMax.get(Calendar.DAY_OF_WEEK);
        int nombreSemaine = (jour - jourSemaine + 10) / 7;

        for (int i = 1; i <= nombreSemaine; i++) {
            listeSemaines.add(i);
        }

        Spinner spinnerNumSemaine = (Spinner) findViewById(R.id.spWeek);
        ArrayAdapter listeSemaineAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listeSemaines);
        spinnerNumSemaine.setAdapter(listeSemaineAdapter);
        spinnerNumSemaine.setSelection(aujourdhui.get(Calendar.WEEK_OF_YEAR) - 1);

        for (int i = aujourdhui.get(Calendar.YEAR); i <= aujourdhui.get(Calendar.YEAR) + 1; i++) {
            listeAnnees.add(i);
        }

        Spinner spinnerAnnees = (Spinner) findViewById(R.id.spYear);
        ArrayAdapter listeAnneesAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listeAnnees);
        spinnerAnnees.setAdapter(listeAnneesAdapter);
    }

    /**
     * Met a jour la liste des evenements
     */
    private void updateEvenements() {
        try {
            BDD base = new BDD();
            Calendar dateFinSemaine = this.dateDebutSemaine;

            dateFinSemaine.add(Calendar.DATE, 6);

            ArrayList<Planning> listePlanning = base.getInfosPlanning(this.dateDebutSemaine.getTime(), dateFinSemaine.getTime());

            this.evenements.clear();

            for(Planning planning :listePlanning)
            {
                this.evenements.add(new PlanningEvent(planning,R.drawable.typeevent_1));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Créer un text view
     *
     * @param label
     * @param row
     * @param column
     * @param color
     * @param borderBottom
     * @param borderRight
     * @return
     */
    private TextView createTextView(String label, int row, int column, int color, boolean borderBottom, boolean borderRight) {
        return this.createTextView(label, row, column, color, borderBottom, borderRight, 200);
    }

    /**
     * Créer un text view
     *
     * @param label
     * @param row
     * @param column
     * @param color
     * @param borderBottom
     * @param borderRight
     * @param width
     * @return
     */
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

    /**
     * Créer un espace
     *
     * @param row
     * @param column
     * @param rowspan
     * @param columnspan
     * @return
     */
    private Space createSpace(int row, int column, int rowspan, int columnspan) {
        Space space = new Space(this);
        space.setLayoutParams(new GridLayout.LayoutParams(
                GridLayout.spec(row, rowspan, GridLayout.FILL),
                GridLayout.spec(column, columnspan, GridLayout.FILL)));
        space.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        return space;
    }

    /**
     * Créer un bouton
     *
     * @param evenement
     * @param row
     * @param column
     * @param rowspan
     * @param drawable
     * @param borderBottom
     * @param borderRight
     * @return
     */
    private Button createButton(final PlanningEvent evenement, int row, int column, int rowspan, int drawable, boolean borderBottom, boolean borderRight) {
        Button button = new Button(this);
        button.setGravity(Gravity.CENTER);
        button.setText(evenement.getLibelle());
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
                Toast.makeText(getBaseContext(), evenement.getLibelle(), Toast.LENGTH_LONG).show();
            }
        });
        return button;
    }

    /**
     * Définis un layout et une bordre
     *
     * @param view
     * @param params
     * @param borderBottom
     * @param borderTop
     * @param borderLeft
     * @param borderRight
     */
    private void setLayoutAndBorder(View view, GridLayout.LayoutParams params, boolean borderBottom, boolean borderTop, boolean borderLeft, boolean borderRight) {
        params.bottomMargin = borderBottom ? 1 : 0;
        params.leftMargin = borderLeft ? 1 : 0;
        params.rightMargin = borderRight ? 1 : 0;
        params.topMargin = borderTop ? 1 : 0;
        view.setLayoutParams(params);
    }
}
