package com.gv.amanzano.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.gv.amanzano.bd.GesVenSQLiteHelper;
import com.gv.amanzano.gesvenapp.LoginActivity;
import com.gv.amanzano.gesvenapp.R;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link UserListActivity}.
 */
public class UserDetailActivity extends AppCompatActivity {

    public static GesVenSQLiteHelper gesVenBD = LoginActivity.gesVenBD;;
    public static SQLiteDatabase bd = null;

    private EditText cedula;
    private CollapsingToolbarLayout code;
    private EditText codigo;
    private EditText nombre;
    private EditText contraseña;
    private EditText direccion;
    private EditText correo;
    private EditText telefono;
    private FloatingActionButton fab;
    private FloatingActionButton fabDelete;
    private FloatingActionButton fabSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        bd = gesVenBD.getWritableDatabase();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);
        fabSave = (FloatingActionButton) findViewById(R.id.fabSave);

        fabSave.setEnabled(false);

        // Editar
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Editar Registro", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                cedula.setEnabled(true);
                codigo.setEnabled(true);
                nombre.setEnabled(true);
                contraseña.setEnabled(true);
                direccion.setEnabled(true);
                correo.setEnabled(true);
                telefono.setEnabled(true);
                fabSave.setEnabled(true);
            }
        });

        // Eliminar
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Eliminar Registro", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                code = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
                String cod = code.getTitle().toString();
                bd.delete("usuario", "nombre='" + cod + "'", null);
                finish();
            }
        });

        // Guardar
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cedula = (EditText) findViewById(R.id.cedula);
                codigo = (EditText) findViewById(R.id.codigo);
                nombre = (EditText) findViewById(R.id.nombre);
                contraseña = (EditText) findViewById(R.id.password);
                direccion = (EditText) findViewById(R.id.direccion);
                telefono = (EditText) findViewById(R.id.telefono);
                correo = (EditText) findViewById(R.id.correo);

                String cod = codigo.getText().toString();
                ContentValues editUser = new ContentValues();
                editUser.put("cedula", cedula.getText().toString());
                editUser.put("codigo", codigo.getText().toString());
                editUser.put("nombre", nombre.getText().toString());
                editUser.put("contraseña", contraseña.getText().toString());
                editUser.put("direccion", direccion.getText().toString());
                editUser.put("telefono", telefono.getText().toString());
                editUser.put("correo", correo.getText().toString());
                editUser.put("estatus", "A");
                bd.update("usuario", editUser, "codigo=" + cod, null);

                fabSave.setEnabled(false);

                Snackbar.make(view, "Registro Guardado Exitosamente!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(UserDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(UserDetailFragment.ARG_ITEM_ID));
            UserDetailFragment fragment = new UserDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, UserListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
