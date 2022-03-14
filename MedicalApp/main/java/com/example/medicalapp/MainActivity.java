package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
/** Actividad Mediante la cual se crea un Menú*/
public class MainActivity extends AppCompatActivity {

    private Button btnCliente;
    private Button btnMedicamento;
    private Button btnVerCliente;
    private Button btnVerMedicamento, btnMenuInicio;
/** Sobreescritura del método onCreate para el instancimiento de los botones del menú*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnCliente = (Button) findViewById(R.id.btnRegistroCliente);
        btnMedicamento = (Button) findViewById(R.id.btnRegistroMedicamento);
        btnVerCliente = (Button) findViewById(R.id.btnVerClientes);
        btnVerMedicamento = (Button) findViewById(R.id.btnVerMedicamentos);
        btnMenuInicio = (Button) findViewById(R.id.btnRegresar);
/** Configuración del botón para acceder al registro del Cliente*/
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad de Registro de Cliente*/
                Intent intent = new Intent(getApplicationContext(), ActividadRegistroCliente.class);
                startActivity(intent);

            }
        });

        btnMenuInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AcitividadInicio.class);
                startActivity(intent);
            }
        });
/** Configuración del botón para acceder al registro de Medicamentos*/
        btnMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad de Registro de Medicamentos*/
                Intent intent = new Intent(getApplicationContext(), MedicamentosActivity.class);
                startActivity(intent);
            }
        });

        btnVerCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad de Registro de Medicamentos*/
                Intent intent = new Intent(getApplicationContext(), ActividadVerClientes.class);
                startActivity(intent);
            }
        });

        btnVerMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad de Registro de Medicamentos*/
                Intent intent = new Intent(getApplicationContext(), ActividadVerMedicamentos.class);
                startActivity(intent);
            }
        });


    }
}