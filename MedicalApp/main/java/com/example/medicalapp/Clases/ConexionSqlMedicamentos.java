package com.example.medicalapp.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;


public class ConexionSqlMedicamentos extends SQLiteOpenHelper {

    public ConexionSqlMedicamentos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCliente  ="CREATE TABLE tblMedicamentos (idMedicamento INTEGER PRIMARY KEY autoincrement, nombreMedicamento varchar(50), tipoMedicamento varchar(12), " +
                "costoUnitario float, precioVenta float, fechaCaducidad varchar(50), proveedorMedicamento varchar(50))";
        db.execSQL(queryCliente);

    }

    /** Método para realizar cambios en la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tblMedicamentos");
        onCreate(db);
    }

    public void abrir(){
        this.getWritableDatabase();
    }

    // METODO PARA CERRAR LA BASE DE DATOS
    public void cerrar() {
        this.close();

    }

    public void insertarMedicamentos(String nombre, String tipo, float costo, float pvp , String fecha, String proveedor){
        ContentValues valores = new ContentValues();
        valores.put("nombreMedicamento",nombre);
        valores.put("tipoMedicamento",tipo);
        valores.put("costoUnitario",costo);
        valores.put("precioVenta",pvp);
        valores.put("fechaCaducidad",fecha);
        valores.put("proveedorMedicamento",proveedor);
        this.getWritableDatabase().insert("tblMedicamentos",null,valores);

    }

    public Medicamento verMedicamento(String id){
        Medicamento medicamento = null;
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tblMedicamentos Where nombreMedicamento = '"+ id +"' Limit 1", null);

        if (cursor.moveToFirst()) {
            medicamento=new Medicamento();
            medicamento.setNombreMedicamento(cursor.getString(1));
            medicamento.setTipoMedicamento(cursor.getString(2));
            medicamento.setCostoUnitario(cursor.getFloat(3));
            medicamento.setPvp(cursor.getFloat(4));
            medicamento.setFechaVencimiento(cursor.getString(5));
            medicamento.setProveedor(cursor.getString(6));
        }
        cursor.close();
        return medicamento;
    }

    public boolean editarMedicamento(String nombre, String tipo, float costo, float pvp , String fecha, String proveedor) {

        boolean correcto = false;
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("UPDATE tblMedicamentos" + " SET nombreMedicamento = '" + nombre + "', tipoMedicamento = '" + tipo + "', costoUnitario = '"
                    + costo + "', precioVenta = '" + pvp + "', fechaCaducidad = '" + fecha + "' , proveedorMedicamento = '" + proveedor + "'  WHERE nombreMedicamento='" + nombre + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarMedicamento(String nombre) {

        boolean correcto = false;

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM tblMedicamento" + " WHERE nombreMedicamento = '" + nombre + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
