package com.example.medicalapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.ConexionSqlUsuarios;
import com.example.medicalapp.UtilidadesUI.UIUtils;

public class ActividadLoggingEmpleado extends AppCompatActivity {

    EditText txtUser, txtPasswd;
    Button btnIngresarEmpleado, btnRegistrarse, btnRegresarInicio;
    ConexionSqlUsuarios helper = new ConexionSqlUsuarios(this,"MedicalBD3",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_logging_empleado);
        btnIngresarEmpleado = (Button) findViewById(R.id.btnIngresarEmpleado);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarEmpleado);
        btnRegresarInicio = (Button) findViewById(R.id.btnRegresarInicio);
        txtUser = (EditText) findViewById(R.id.txtUserEmpleado);
        txtPasswd = (EditText) findViewById(R.id.txtPasswordEmpleado);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActividadRegistrarEmpleado.class);
                startActivity(intent);
            }
        });

        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AcitividadInicio.class);
                startActivity(intent);
            }
        });

        btnIngresarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Cursor cursor = helper.ConsultarUser(txtUser.getText().toString(), txtPasswd.getText().toString());
                    if (cursor.getCount() > 0) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        UIUtils.mostrarMensajeToast(getApplicationContext(), "Usuario y/o Contraseña Incorrectos");
                    }
                    limpiartxtsActividadLoggingEmpleado();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void limpiartxtsActividadLoggingEmpleado(){
        txtUser.setText(null);
        txtPasswd.setText(null);

    }
}