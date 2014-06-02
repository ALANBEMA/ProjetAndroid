package fr.epsi.BaseDeDonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexandredouchin on 26/05/2014.
 */
public class BDDHelper extends SQLiteOpenHelper {

    public BDDHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BDDHelper(Context context) {
        super(context, "infos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE personnel(_id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL, prenom TEXT NOT NULL, adresse TEXT NOT NULL, email TEXT NOT NULL, numTel TEXT NOT NULL, emploi TEXT NOT NULL )");
        db.execSQL("CREATE TABLE agenda(_id INTEGER PRIMARY KEY AUTOINCREMENT, id_personnel INTEGER NOT NULL, date_debut DATE NOT NULL, date_fin DATE NOT NULL, type TEXT NOT NULL, FOREIGN KEY(id_personnel) REFERENCES personnel(_id))");

        db.execSQL("INSERT INTO personnel (_id, nom, prenom, adresse, email, numTel, emploi) VALUES (1, 'Douchin', 'Alexandre', '28 chemin du moulin 44500 La Baule', 'alexandre.douchin@gmail.com', '0619113872', 'médecin');");
        db.execSQL("INSERT INTO personnel (_id, nom, prenom, adresse, email, numTel, emploi) VALUES (2, 'Humeau', 'Benjamin', '28 chemin du moulin 44500 La Baule', 'benjamin.humeau@gmail.com', '0619113872', 'brancardier');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
