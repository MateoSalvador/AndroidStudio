package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.ConexionSqlUsuariosCliente;

public class ActividadLoginCliente extends AppCompatActivity {

    EditText txtUserCliente, txtPasswdCliente;
    Button btnIngresarCliente, btnRegistrarseCliente, btnRegresoInicio;
    ConexionSqlUsuariosCliente helper = new ConexionSqlUsuariosCliente(this,"MedicalBD4",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_login_cliente);
        btnIngresarCliente = (Button) findViewById(R.id.btnIngresarCliente);
        btnRegistrarseCliente = (Button) findViewById(R.id.btnRegistrarCliente);
        btnRegresoInicio = (Button) findViewById(R.id.btnRegresoInicio);
        txtUserCliente = (EditText) findViewById(R.id.txtUserCliente);
        txtPasswdCliente = (EditText) findViewById(R.id.txtPasswordCliente);

        /*btnIngresarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Cursor cursor = helper.ConsultarUserCliente(txtUserCliente.getText().toString(), txtPasswdCliente.getText().toString());
                    if (cursor.getCount() > 0) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        UIUtils.mostrarMensajeToast(getApplicationContext(), "Usuario y/o Contraseña Incorrectos");
                    }
                    limpiartxtsActividadLoginCliente();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });*/

        btnRegistrarseCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ActividadRegistrarUsuarioCliente.class);
                    startActivity(intent);
            }
        });

        btnRegresoInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AcitividadInicio.class);
                startActivity(intent);
            }
        });

    }
    public void limpiartxtsActividadLoginCliente(){
        txtUserCliente.setText(null);
        txtPasswdCliente.setText(null);

    }
}