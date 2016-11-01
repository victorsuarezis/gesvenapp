package com.gv.amanzano.gesvenapp.dummy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gv.amanzano.bd.GesVenSQLiteHelper;
import com.gv.amanzano.gesvenapp.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static int COUNT = 0;

    public static GesVenSQLiteHelper gesVenBD = LoginActivity.gesVenBD;;
    public static SQLiteDatabase bd = null;

    public static void getDummyContent() {
        // Add some sample items.
        bd = gesVenBD.getWritableDatabase();
        String sql = "SELECT cedula, codigo, nombre, direccion, telefono, correo, contraseña, estatus FROM usuario;";
        Cursor cursor = null;
        cursor = bd.rawQuery(sql, null);
        String cedula = "";
        String codigo = "";
        String nombre = "";
        String direccion = "";
        String telefono = "";
        String correo = "";
        String contraseña = "";
        String estatus = "";
        ITEMS.clear();
        if(cursor.moveToFirst()) {
            do {
                cedula = cursor.getString(0);
                codigo = cursor.getString(1);
                nombre = cursor.getString(2);
                direccion = cursor.getString(3);
                telefono = cursor.getString(4);
                correo = cursor.getString(5);
                contraseña = cursor.getString(6);
                estatus = cursor.getString(7);
                addItem(createDummyItem(cedula, nombre, codigo, direccion, telefono, correo, contraseña, estatus));
                COUNT++;
            } while (cursor.moveToNext());
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.cedula, item);
    }

    private static DummyItem createDummyItem(String cedula, String nombre, String codigo, String direccion, String telefono, String correo, String contraseña, String estatus) {
        return new DummyItem(cedula, nombre, codigo, direccion, telefono, correo, contraseña, estatus);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static List<DummyItem> getItems() {
        getDummyContent();
        return ITEMS;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String cedula;
        public final String nombre;
        public final String codigo;
        public final String direccion;
        public final String telefono;
        public final String correo;
        public final String contraseña;
        public final String estatus;

        public DummyItem(String cedula, String nombre, String codigo, String direccion, String telefono, String correo, String contraseña, String estatus) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.codigo = codigo;
            this.direccion = direccion;
            this.telefono = telefono;
            this.correo = correo;
            this.contraseña = contraseña;
            this.estatus = estatus;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
}
