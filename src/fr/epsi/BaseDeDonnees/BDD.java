package fr.epsi.BaseDeDonnees;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    /**
     * Retourne les donnees sous forme d'objet
     *
     * Benjamin
     *
     * @param dateDebut
     * @param dateFin
     * @return
     * @throws ParseException
     */
    public ArrayList<Planning> getInfosPlanning(Date dateDebut, Date dateFin) throws ParseException {
        ArrayList<Planning> listePlanning = new ArrayList<Planning>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Cursor donnees = base.rawQuery("SELECT _id, id_personnel, date_debut, date_fin, type WHERE date_debut>? AND date_debut<?",new String[]{dateFormat.format(dateDebut),dateFormat.format(dateFin)});

        if (donnees.moveToFirst()){
            do{
                Planning newPlanning = new Planning();
                newPlanning.setId_planning(donnees.getInt(donnees.getColumnIndex("_id")));
                newPlanning.setId_personnel(donnees.getInt(donnees.getColumnIndex("id_personnel")));
                newPlanning.setDate_debut(dateFormat.parse(donnees.getString(donnees.getColumnIndex("date_debut"))));
                newPlanning.setDate_fin(dateFormat.parse(donnees.getString(donnees.getColumnIndex("date_fin"))));
                newPlanning.setType(donnees.getString(donnees.getColumnIndex("type")));
                listePlanning.add(newPlanning);

            }while(donnees.moveToNext());
        }
        donnees.close();

        return listePlanning;
    }

    /**
     * Retourne les infos d'une personne
     *
     * Benjamin
     * @param id
     * @return
     */
    public Personnel getInfosPersonnel(int id)
    {
        Personnel personnel = new Personnel();

        Cursor donnees = base.rawQuery("SELECT _id, nom, prenom, adresse, email, numTel, emploi FROM personnel where _id = ?",new String[]{String.valueOf(id)});

        if(donnees.moveToFirst())
        {
            personnel.setId_personnel(donnees.getInt(donnees.getColumnIndex("_id")));
            personnel.setNom(donnees.getString(donnees.getColumnIndex("nom")));
            personnel.setPrenom(donnees.getString(donnees.getColumnIndex("prenom")));
            personnel.setAdresse(donnees.getString(donnees.getColumnIndex("adresse")));
            personnel.setEmail(donnees.getString(donnees.getColumnIndex("email")));
            personnel.setNumTelephone(donnees.getString(donnees.getColumnIndex("emploi")));
        }

        return personnel;
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
        //Benjamin : il faut définir le format de la date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String requete = "INSERT INTO agenda (id_personnel, date_debut, date_fin, type) VALUES ( " + id + ", '" + dateFormat.format(dateDebut) + "', '" + dateFormat.format(dateFin) + "', '" + type + "');";
        base.execSQL(requete);
    }
}
