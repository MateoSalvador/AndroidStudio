package com.example.medicalapp.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSqlUsuarios extends SQLiteOpenHelper {

    public ConexionSqlUsuarios(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUsuario  = "CREATE TABLE tblUsuarioEmpleado (idusario INTEGER PRIMARY KEY autoincrement, nombreUsuarioEmpleado varchar(50), " +
                "contraseñaEmpleado varchar(20))";
        db.execSQL(queryUsuario);

    }

    /** Método para realizar cambios en la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tblUsuarioEmpleado");
        onCreate(db);
    }

    public void abrir(){
        this.getWritableDatabase();
    }

    // METODO PARA CERRAR LA BASE DE DATOS
    public void cerrar() {
        this.close();
    }

    public void insertarUsuarioEmpleado(String user, String passwd){
        ContentValues valores = new ContentValues();
        valores.put("nombreUsuarioEmpleado",user);
        valores.put("contraseñaEmpleado",passwd);
        this.getWritableDatabase().insert("tblUsuarioEmpleado",null,valores);
    }

    public Cursor ConsultarUser(String usr, String psw) throws SQLException {
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("tblUsuarioEmpleado",new String[]{"*"},
                "nombreUsuarioEmpleado like '"+ usr +"' "+"and contraseñaEmpleado like '"+ psw +"' ",null,null,null,null);
        return  mcursor;
    }

}
