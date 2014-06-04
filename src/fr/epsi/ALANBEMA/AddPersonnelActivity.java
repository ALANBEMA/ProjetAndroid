package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import fr.epsi.BaseDeDonnees.BDD;
import fr.epsi.BaseDeDonnees.Personnel;

/**
 * Created by alexandredouchin on 02/06/2014.
 */
public class AddPersonnelActivity extends Activity implements View.OnClickListener {

    private Button Bvalider;
    private String nom;
    private String prenom;
    private String adresse;
    private String phone;
    private String email;
    private String emploi;
    BDD bdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpersonnel);


        bdd = new BDD();

        bdd.open(this);

        Bvalider = (Button) findViewById(R.id.buttonadd);
        Bvalider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == Bvalider){

            RadioButton rbMedecin = (RadioButton) findViewById(R.id.rbmedecin);
            RadioButton rbInfirmier = (RadioButton) findViewById(R.id.rbinfirmier);
            RadioButton rbAide = (RadioButton) findViewById(R.id.rbaidesoignant);
            RadioButton rbBrancardier = (RadioButton) findViewById(R.id.rbbrancardier);

            EditText EditNom = (EditText)findViewById(R.id.etNom);
            nom = EditNom.getText().toString();

            EditText EditPrenom = (EditText)findViewById(R.id.etPrenom);
            prenom = EditPrenom.getText().toString();

            EditText EditAdresse = (EditText)findViewById(R.id.etadresse);
            adresse = EditAdresse.getText().toString();

            EditText EditNum = (EditText)findViewById(R.id.etphone);
            phone = EditNum.getText().toString();

            EditText EditEmail = (EditText)findViewById(R.id.etemail);
            email = EditEmail.getText().toString();

            if(rbMedecin.isChecked()){
                emploi = "Médecin";
            }
            else if(rbInfirmier.isChecked()){
                emploi = "Infirmier";
            }
            else if(rbAide.isChecked()){
                emploi = "Aide Soignant";
            }
            else if(rbBrancardier.isChecked()){
                emploi = "Brancardier";
            }

            bdd.addPersonnel(nom, prenom, adresse, email, phone, emploi);

            this.finish();


        }

    }

}
