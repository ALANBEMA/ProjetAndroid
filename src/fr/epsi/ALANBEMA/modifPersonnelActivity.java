package fr.epsi.ALANBEMA;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import fr.epsi.BaseDeDonnees.BDD;

import java.util.ArrayList;

/**
 * Created by alexandredouchin on 02/06/2014.
 */
public class modifPersonnelActivity extends Activity implements View.OnClickListener {

    private Button Bvalider;
    private Button Bdelete;
    private String nom;
    private String prenom;
    private String adresse;
    private String phone;
    private String email;
    private String emploi;
    private int id;
    BDD bdd;
    Cursor personnelList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifpersonnel);

        bdd = new BDD();

        bdd.open(this);

        Bvalider = (Button) findViewById(R.id.buttonadd);
        Bvalider.setOnClickListener(this);

        Bdelete = (Button) findViewById(R.id.bdelete);
        Bdelete.setOnClickListener(this);

        Bundle extra = getIntent().getExtras();
        int position = extra.getInt("position");

        personnelList = bdd.getInfosPersonnel();

        personnelList.moveToPosition(position);

        id = personnelList.getInt(0);

        nom = personnelList.getString(1);
        prenom = personnelList.getString(2);
        adresse = personnelList.getString(3);
        phone = personnelList.getString(4);
        email = personnelList.getString(5);
        emploi = personnelList.getString(6);

        RadioButton rbMedecin = (RadioButton) findViewById(R.id.rbmedecin);
        RadioButton rbInfirmier = (RadioButton) findViewById(R.id.rbinfirmier);
        RadioButton rbAide = (RadioButton) findViewById(R.id.rbaidesoignant);
        RadioButton rbBrancardier = (RadioButton) findViewById(R.id.rbbrancardier);

        EditText EditNom = (EditText) findViewById(R.id.etNom);
        EditNom.setText(nom);
        EditText EditPrenom = (EditText) findViewById(R.id.etPrenom);
        EditPrenom.setText(prenom);
        EditText EditAdresse= (EditText) findViewById(R.id.etadresse);
        EditAdresse.setText(adresse);
        EditText EditPhone = (EditText) findViewById(R.id.etphone);
        EditPhone.setText(phone);
        EditText EditEmail = (EditText) findViewById(R.id.etemail);
        EditEmail.setText(email);

        if(emploi.equals("Médecin")){
            rbMedecin.setChecked(true);
        }
        else if(emploi.equals("Infirmier")){
            rbInfirmier.setChecked(true);
        }
        else if(emploi.equals("Aide Soignant")){
            rbAide.setChecked(true);
        }
        else if(emploi.equals("Brancardier")){
            rbBrancardier.setChecked(true);
        }
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

            bdd.updatePersonnel(id, nom, prenom, adresse, phone, email, emploi);

            this.finish();


        }
        else if(v == Bdelete){
            bdd.deletePersonnel(id);

            this.finish();
        }

    }
}
