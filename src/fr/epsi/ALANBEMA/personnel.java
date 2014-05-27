package fr.epsi.ALANBEMA;

/**
 * Created by alexandredouchin on 26/05/2014.
 */
public class personnel {
    private int id_personnel;
    private String Nom;
    private String Prenom;
    private String adresse;
    private String email;
    private String numTelephone;

    public personnel(int id_personnel, String nom, String prenom, String adresse, String email, String numTelephone) {
        this.id_personnel = id_personnel;
        this.Nom = nom;
        this.Prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.numTelephone = numTelephone;
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
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
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
}
