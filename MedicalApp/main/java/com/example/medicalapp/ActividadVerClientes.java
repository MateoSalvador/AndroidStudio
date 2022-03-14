package com.example.medicalapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.Cliente;
import com.example.medicalapp.Clases.ConexionSql;

import java.util.ArrayList;

public class ActividadVerClientes extends AppCompatActivity {

    ListView listaClientes;
    ArrayList<String>  listainformacion;
    ArrayList<Cliente> listadoClientes;
    private Button btnRegresoMenu;

    ConexionSql conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clientes);

        btnRegresoMenu = (Button) findViewById(R.id.btnRegresarAlMenu);
        btnRegresoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad del menú*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        conexion = new ConexionSql(this,"MedicalBD",null,1);
        listaClientes = (ListView) findViewById(R.id.listViewClientes);

        consultarListaClientes();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        listaClientes.setAdapter(adaptador);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacionCliente="Cédula: "+listadoClientes.get(pos).getCedula()+"\n";
                informacionCliente+="Nombre: "+listadoClientes.get(pos).getNombre()+"\n";
                informacionCliente+="Apellido: "+listadoClientes.get(pos).getApellido()+"\n";
                informacionCliente+="Dirección: "+listadoClientes.get(pos).getDireccion()+"\n";
                informacionCliente+="Fecha Nacimiento: "+listadoClientes.get(pos).getFechaNacimiento()+"\n";
                informacionCliente+="Correo: "+listadoClientes.get(pos).getCorreo()+"\n";

                //Toast.makeText(getApplicationContext(), informacionCliente,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EditarEliminarCliente.class).putExtra("ID",listadoClientes.get(pos).getCedula());
                startActivity(intent);
            }
        });
    }

    private void consultarListaClientes(){

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cliente cliente = null;
        listadoClientes = new ArrayList<Cliente>();
        Cursor cursor = db.rawQuery("SELECT * FROM tblCLiente",null);

        while (cursor.moveToNext()){
            cliente=new Cliente();
            cliente.setNombre(cursor.getString(1));
            cliente.setApellido(cursor.getString(2));
            cliente.setCedula(cursor.getString(0));
            cliente.setCorreo(cursor.getString(4));
            cliente.setDireccion(cursor.getString(5));
            cliente.setFechaNacimiento(cursor.getString(3));

            listadoClientes.add(cliente);
        }

        obtenerLista();

    }

    private  void obtenerLista(){

        listainformacion= new ArrayList<String>();

        for (int i=0; i < listadoClientes.size(); i++){
            listainformacion.add(listadoClientes.get(i).getNombre()+" "
                    +listadoClientes.get(i).getApellido());
        }
    }
}