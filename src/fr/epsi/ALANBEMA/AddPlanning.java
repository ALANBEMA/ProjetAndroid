package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import fr.epsi.BaseDeDonnees.BDD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alexandredouchin on 04/06/2014.
 */
public class AddPlanning extends Activity implements View.OnClickListener  {

    BDD bdd;
    Cursor ClistPersonnel;
    private Date dateDebut;
    private Date dateFin;
    private int id;
    private Button Bvalider;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplanning);

        bdd = new BDD();

        bdd.open(this);

        ClistPersonnel = bdd.getInfosPersonnel();
        startManagingCursor(ClistPersonnel);

        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, ClistPersonnel,
                new String[]{"nom" , "emploi"}, new int[]{ android.R.id.text1, android.R.id.text2});
        Spinner spnPersonel = (Spinner) findViewById(R.id.spinner);
        spnPersonel.setAdapter(mAdapter);

        Bvalider = (Button) findViewById(R.id.buttonValiderPlanning);
        Bvalider.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == Bvalider)  {
            Spinner spinnerNom = (Spinner)findViewById(R.id.spinner);
            int id = spinnerNom.getSelectedItemPosition();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss", Locale.FRENCH);

            type = "Travail";

            DatePicker datePickerdebut = (DatePicker)findViewById(R.id.datePickerDebut);
            datePickerdebut.init(datePickerdebut.getYear(), datePickerdebut.getMonth(), datePickerdebut.getDayOfMonth(), null);



            String dayDebut = Integer.toString(datePickerdebut.getDayOfMonth());
            String monthDebut = Integer.toString(datePickerdebut.getMonth());
            String yearDebut = Integer.toString(datePickerdebut.getYear());

            TimePicker timePickerDebut = (TimePicker)findViewById(R.id.timePickerDebut);
            String heureDebut = Integer.toString(timePickerDebut.getCurrentHour());
            String minuteDebut = Integer.toString(timePickerDebut.getCurrentMinute());

            String debut = dayDebut + "/" + monthDebut + "/" + yearDebut + " " + heureDebut + ":" + minuteDebut;

            DatePicker datePickerfin = (DatePicker)findViewById(R.id.datePickerFin);
            int dayFin = datePickerfin.getDayOfMonth();
            int monthFin = datePickerfin.getMonth();
            int yearFin = datePickerfin.getYear();

            TimePicker timePickerFin = (TimePicker)findViewById(R.id.timePickerFin);
            int heureFin = timePickerFin.getCurrentHour();
            int minuteFin = timePickerFin.getCurrentMinute();

            String fin = dayFin + "/" + monthFin + "/" + yearFin + " " + heureFin + ":" + minuteFin;

            ClistPersonnel.moveToPosition(id);
            id = Integer.parseInt(ClistPersonnel.getString(0));

            dateDebut = sdf.parse(debut, null);
            dateFin = sdf.parse(fin, null);

            bdd.addAgenda(dateDebut, dateFin, type, id);
        }

    }
}
