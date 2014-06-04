package fr.epsi.Planning;

import fr.epsi.BaseDeDonnees.Personnel;
import fr.epsi.BaseDeDonnees.Planning;

import java.util.Date;

/**
 * Created by benjamin on 26/05/14.
 */
public class PlanningEvent {
    private Date dateDebut;
    private Date dateFin;
    private String libelle;
    private int drawable;

    public PlanningEvent(Planning planning,int drawable)
    {
        Personnel personnel = planning.getPersonnel();
        this.dateDebut = planning.getDate_debut();
        this.dateFin = planning.getDate_fin();
        this.libelle = personnel.getNom() +" "+personnel.getPrenom();
        this.drawable = drawable;
    }

    public PlanningEvent(Date dateDebut, Date dateFin, String libelle, int drawable) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.libelle = libelle;
        this.drawable = drawable;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public String getLibelle() {

        return libelle;
    }

    public int getDrawable() {

        return drawable;
    }
}
