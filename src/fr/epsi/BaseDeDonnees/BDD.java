package fr.epsi.BaseDeDonnees;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

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

    public void updatePersonnel(int id, String nom, String prenom, String adresse, String email, String numTel, String emploi) {
        String requete = "UPDATE personnel SET nom = '" + nom + "', prenom = '" + prenom + "', adresse = '" + adresse + "', email = '" + email + "', numTel = '" + numTel + "', emploi = '" + emploi + "' WHERE _id = " + id + ";";
        base.execSQL(requete);
    }

    public void deletePersonnel(int id) {
        String requete = "DELETE FROM personnel WHERE _id = " + id + ";";
        base.execSQL(requete);
    }

    public void addAgenda(Date dateDebut, Date dateFin, String type, int id) {
        String requete = "INSERT INTO agenda (id_personnel, date_debut, date_fin, type) VALUES ( " + id + ", '" + dateDebut + "', '" + dateFin + "', " + type + ");";
        base.execSQL(requete);
    }
}
