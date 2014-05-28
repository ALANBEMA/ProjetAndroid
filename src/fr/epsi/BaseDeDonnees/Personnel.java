package fr.epsi.BaseDeDonnees;

/**
 * Created by alexandredouchin on 26/05/2014.
 */
public class Personnel {
    private int id_personnel;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String numTelephone;
    private String emploi;

    public Personnel(int id_personnel, String nom, String prenom, String adresse, String email, String numTelephone, String emploi) {
        this.id_personnel = id_personnel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.numTelephone = numTelephone;
        this.emploi = emploi;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public int getId_personnel() {
        return id_personnel;
    }

    public void setId_personnel(int id_personnel) {
        this.id_personnel = id_personnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmploi() {
        return emploi;
    }

    public void setEmploi(String emploi) {
        this.emploi = emploi;
    }
}
