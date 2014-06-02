package fr.epsi.BaseDeDonnees;

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
                "SELECT _id, nom, prenom, adresse, email, numTel, emploi FROM personnel", null
        );
    }

    public Cursor getInfosPlanning() {
        return base.rawQuery(
                "SELECT _id, id_personnel, date_debut, date_fin, type", null
        );
    }

    public void addPersonnel(String nom, String prenom, String adresse, String email, String numTel, String emploi) {
        String requete = "INSERT INTO personnel (nom, prenom, adresse, email, numTel, emploi) VALUES ( '" +
                "" + nom + "', '" + prenom + "', '" + adresse + "', '" + email + "', '" + numTel + "', '" + emploi + "');";
        base.execSQL(requete);
    }

    public void addAgenda(Planning planning) {
        String requete = "INSERT INTO agenda (id_personnel, date_debut, date_fin, type) VALUES ( '" + planning.getId_personnel() + "', '" + planning.getDate_debut() + "', '" + planning.getDate_fin() + "', '" + planning.getType() + "');";
        base.execSQL(requete);
    }
}
