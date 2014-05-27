package fr.epsi.ALANBEMA;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alexandredouchin on 26/05/2014.
 */
public class BDD {
    BDDHelper helper;

    SQLiteDatabase base;

    public void open(Context activity) {
        //Créer ou ouvrir la BDD
        helper = new BDDHelper(activity);
        base = helper.getWritableDatabase();
    }

    public Cursor getInfosPersonnel() {
        return base.rawQuery(
                "SELECT _id, nom, prenom, adresse, email, numTel FROM personnel", null
        );
    }

    public Cursor getInfosAgenda() {
        return base.rawQuery(
                "SELECT _id, id_personnel, date_debut, date_fin", null
        );
    }

    public void addPersonnel(personnel personnel) {
        String requete = "INSERT INTO personnel (nom, prenom, adresse, email, numTel) VALUES ( '" +
                "" + personnel.getNom() + "', '" + personnel.getPrenom() + "', '" + personnel.getAdresse() + "', '" + personnel.getEmail() + "', '" + personnel.getNumTelephone() + "');";
        base.execSQL(requete);
    }

    public void addAgenda(planning planning) {
        String requete = "INSERT INTO agenda (id_personnel, date_debut, date_fin) VALUES ( '" + agenda.getId_personnel() + "', '" + agenda.getDate_debut() + "', '" + agenda.getDate_fin() + "');";
        base.execSQL(requete);
    }
}
