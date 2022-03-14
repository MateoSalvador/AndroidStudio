package com.example.medicalapp.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ConexionSql extends SQLiteOpenHelper {

    /** Constructor para la conexión SQL*/
    public ConexionSql( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
/** Sobreescritura del método onCreate en el cual se ejecuta el query para la
 * creación de tabla en la cual se generen los campos
 * para ingresar los datos de la entidad cliente*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCliente  ="CREATE TABLE tblCliente (cedulaCliente varchar(10) primary key, nombresCliente varchar(50), apellidosCliente varchar(50), fechaNacimientoCliente varchar(50), correoCliente varchar(50), direccionCliente varchar(50))";
        db.execSQL(queryCliente);

    }
/** Método para realizar cambios en la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tblCliente");
        onCreate(db);
    }
    // METODO PARA ABRIR LA BASE DE DATOS
    public void abrir(){
        this.getWritableDatabase();
    }

    // METODO PARA CERRAR LA BASE DE DATOS
    public void cerrar() {
        this.close();

    }
    // METODO PARA POBLAR TABLA CLIENTES
    public void insertarUsuarios(String cedula,String nombre, String apellido,String fecha , String correo, String direccion ){
        ContentValues valores = new ContentValues();
        valores.put("cedulaCliente",cedula);
        valores.put("nombresCliente",nombre);
        valores.put("apellidosCliente",apellido);
        valores.put("fechaNacimientoCliente",fecha);
        valores.put("correoCliente",correo);
        valores.put("direccionCliente",direccion);
        this.getWritableDatabase().insert("tblCliente",null,valores);

    }

    public Cliente verCliente(String id){
        Cliente cliente = null;
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tblCLiente Where cedulaCliente = "+ id +" Limit 1", null);

        if (cursor.moveToFirst()) {
            cliente=new Cliente();
            cliente.setNombre(cursor.getString(1));
            cliente.setApellido(cursor.getString(2));
            cliente.setCedula(cursor.getString(0));
            cliente.setCorreo(cursor.getString(4));
            cliente.setDireccion(cursor.getString(5));
            cliente.setFechaNacimiento(cursor.getString(3));
        }
        cursor.close();
        return cliente;
    }

    public boolean editarCliente(String cedula,String nombre, String apellido,String fecha , String correo, String direccion ) {

        boolean correcto = false;
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("UPDATE tblCliente" + " SET nombresCliente = '" + nombre + "', apellidosCliente = '" + apellido + "', fechaNacimientoCliente = '"
                    + fecha + "', correoCliente = '" + correo + "', direccionCliente = '" + direccion + "'  WHERE cedulaCliente='" + cedula + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarCliente(String id) {

        boolean correcto = false;

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM tblCliente" + " WHERE cedulaCliente = '" + id + "'");
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
