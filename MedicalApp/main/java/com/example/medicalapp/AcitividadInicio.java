package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AcitividadInicio extends AppCompatActivity {

    Button btnEmpleado, btnCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitividad_inicio);
        btnEmpleado = (Button) findViewById(R.id.btnEmpleado);
        btnCliente = (Button) findViewById(R.id.btnCliente);

        btnEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActividadLoggingEmpleado.class);
                startActivity(intent);
            }
        });

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActividadLoginCliente.class);
                startActivity(intent);
            }
        });
    }
}