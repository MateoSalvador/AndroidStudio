package com.example.medicalapp.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSqlUsuariosCliente extends SQLiteOpenHelper {

    public ConexionSqlUsuariosCliente(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUsuarioCliente  = "CREATE TABLE tblUsuarioCliente (idusarioCliente INTEGER PRIMARY KEY autoincrement, nombreUsuarioCliente varchar(50), " +
                "contraseñaCliente varchar(20))";
        db.execSQL(queryUsuarioCliente);

    }

    /** Método para realizar cambios en la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tblUsuarioCliente");
        onCreate(db);
    }

    public void abrir(){
        this.getWritableDatabase();
    }

    // METODO PARA CERRAR LA BASE DE DATOS
    public void cerrar() {
        this.close();
    }

    public void insertarUsuarioCliente(String user, String passwd){
        ContentValues valores = new ContentValues();
        valores.put("nombreUsuarioCliente",user);
        valores.put("contraseñaCliente",passwd);
        this.getWritableDatabase().insert("tblUsuarioCliente",null,valores);
    }

    public Cursor ConsultarUserCliente(String usr, String psw){
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("tblUsuarioCliente",new String[]{"idusuarioCliente","nombreUsuarioClient","contraseñaEmpleado"},
                "nombreUsuarioCliente like '"+ usr +"' "+"and contraseñaCliente like '"+ psw +"' ",null,null,null,null);
        return  mcursor;
    }
}
