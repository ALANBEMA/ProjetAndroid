package fr.epsi.ALANBEMA;

import java.util.Date;

/**
 * Created by alexandredouchin on 26/05/2014.
 */
public class agenda {
    private int id_planning;
    private int id_personnel;
    private Date date_debut;
    private Date date_fin;


    public agenda(int id_planning, int id_personnel, Date date_debut, Date date_fin) {
        this.id_planning = id_planning;
        this.id_personnel = id_personnel;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_planning() {
        return id_planning;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public int getId_personnel() {
        return id_personnel;
    }

    public void setId_personnel(int id_personnel) {
        this.id_personnel = id_personnel;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
}