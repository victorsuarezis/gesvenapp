package com.gv.amanzano.gesvenapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gv.amanzano.bd.GesVenSQLiteHelper;
import com.gv.amanzano.gesvenapp.dummy.DummyContent;

public class AddUser extends AppCompatActivity {

    public static GesVenSQLiteHelper gesVenBD = LoginActivity.gesVenBD;;
    public static SQLiteDatabase bd = null;

    private EditText cedula;
    private EditText codigo;
    private EditText nombre;
    private EditText contraseña;
    private EditText direccion;
    private EditText correo;
    private EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void OnClick(View view) {
        cedula = (EditText) findViewById(R.id.cedula);
        codigo = (EditText) findViewById(R.id.codigo);
        nombre = (EditText) findViewById(R.id.nombre);
        contraseña = (EditText) findViewById(R.id.password);
        direccion = (EditText) findViewById(R.id.direccion);
        telefono = (EditText) findViewById(R.id.telefono);
        correo = (EditText) findViewById(R.id.correo);

        bd = gesVenBD.getWritableDatabase();
        String sqlID = "SELECT id FROM usuario ORDER BY id DESC LIMIT 1;";
        int id = 0;
        Cursor c = bd.rawQuery(sqlID, null);
        if(c.moveToFirst())
            id = c.getInt(0) + 1;

        ContentValues newUser = new ContentValues();
        newUser.put("cedula", cedula.getText().toString());
        newUser.put("id", id);
        newUser.put("codigo", codigo.getText().toString());
        newUser.put("nombre", nombre.getText().toString());
        newUser.put("contraseña", contraseña.getText().toString());
        newUser.put("direccion", direccion.getText().toString());
        newUser.put("telefono", telefono.getText().toString());
        newUser.put("correo", correo.getText().toString());
        newUser.put("estatus", "A");

        bd.insert("usuario", null, newUser);

        Snackbar.make(view, "Usuario Registrado Exitosamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        DummyContent.getItems();
        finish();
    }

}
