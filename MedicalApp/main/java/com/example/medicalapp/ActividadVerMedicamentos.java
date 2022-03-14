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

import com.example.medicalapp.Clases.ConexionSqlMedicamentos;
import com.example.medicalapp.Clases.Medicamento;

import java.util.ArrayList;

public class ActividadVerMedicamentos extends AppCompatActivity {

    ListView listaMedicamentos;
    ArrayList<String> listainformacionMedicamentos;
    ArrayList<Medicamento> listadoMedicamentos;
    private Button btnRegresoMenu;

    ConexionSqlMedicamentos conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ver_medicamentos);

        btnRegresoMenu = (Button) findViewById(R.id.btnRegresaralMenu);
        btnRegresoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad del menú*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        conexion = new ConexionSqlMedicamentos(this,"MedicalBD2",null,1);
        listaMedicamentos = (ListView) findViewById(R.id.listviewMedicamentos);

        consultarListaMedicamentos();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacionMedicamentos);
        listaMedicamentos.setAdapter(adaptador);

        listaMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacionMedicamento="Nombre: "+listadoMedicamentos.get(pos).getNombreMedicamento()+"\n";
                informacionMedicamento+="Tipo: "+listadoMedicamentos.get(pos).getTipoMedicamento()+"\n";
                informacionMedicamento+="Costo Unitario: "+listadoMedicamentos.get(pos).getCostoUnitario()+"\n";
                informacionMedicamento+="PVP: "+listadoMedicamentos.get(pos).getPvp()+"\n";
                informacionMedicamento+="Fecha Vencimiento: "+listadoMedicamentos.get(pos).getFechaVencimiento()+"\n";
                informacionMedicamento+="Proveedor: "+listadoMedicamentos.get(pos).getProveedor()+"\n";

                //Toast.makeText(getApplicationContext(), informacionMedicamento,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EditarEliminarMedicamento.class).putExtra("ID",listadoMedicamentos.get(pos).getNombreMedicamento());
                startActivity(intent);
            }
        });

    }
    private void consultarListaMedicamentos(){

        SQLiteDatabase db = conexion.getReadableDatabase();
        Medicamento medicamento = null;
        listadoMedicamentos = new ArrayList<Medicamento>();
        Cursor cursor = db.rawQuery("SELECT * FROM tblMedicamentos",null);

        while (cursor.moveToNext()){
            medicamento=new Medicamento();
            medicamento.setNombreMedicamento(cursor.getString(1));
            medicamento.setTipoMedicamento(cursor.getString(2));
            medicamento.setCostoUnitario(cursor.getFloat(3));
            medicamento.setPvp(cursor.getFloat(4));
            medicamento.setFechaVencimiento(cursor.getString(5));
            medicamento.setProveedor(cursor.getString(6));

            listadoMedicamentos.add(medicamento);
        }

        obtenerLista();

    }

    private  void obtenerLista(){

        listainformacionMedicamentos= new ArrayList<String>();

        for (int i=0; i < listadoMedicamentos.size(); i++){
            listainformacionMedicamentos.add(listadoMedicamentos.get(i).getNombreMedicamento());
        }
    }
}