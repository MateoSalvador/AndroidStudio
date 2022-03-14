package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.ConexionSqlUsuarios;
import com.example.medicalapp.Clases.Usuario;
import com.example.medicalapp.UtilidadesUI.UIUtils;

public class ActividadRegistrarEmpleado extends AppCompatActivity {

    Button btnGuardaruser, btnCancelar;
    EditText txtUserEmpleado, txtContraseña, txtContraeñaCheck, txtCod;

    ConexionSqlUsuarios helper = new ConexionSqlUsuarios(this,"MedicalBD3",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registrar_empleado);
        btnGuardaruser = (Button) findViewById(R.id.btnGuardarRegistroEmpleado);
        btnCancelar = (Button) findViewById(R.id.btnCancelarRegistroEmpleado);
        txtUserEmpleado = (EditText) findViewById(R.id.txtRegistrarUsuarioEmpleado);
        txtContraseña = (EditText) findViewById(R.id.txtRegistrarContraseñaEmpleado);
        txtContraeñaCheck = (EditText) findViewById(R.id.txtConfirmarContraseñaEmpleado);
        txtCod = (EditText) findViewById(R.id.txtCodigoActivacion);

        /** Configuración del botón para guardar los registros ingresados*/
        btnGuardaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarUsuario(view);

            }
        });
        /** Configuración del botón para regresar a la actividad del menú*/
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad del menú*/
                Intent intent = new Intent(getApplicationContext(), ActividadLoggingEmpleado.class);
                startActivity(intent);
            }
        });
    }

    public void guardarUsuario(View view){
        Log.d("Contraseña",txtContraseña.getText().toString());
        Log.d("ContraseñaCheck",txtContraeñaCheck.getText().toString());
      if(txtContraseña.getText().toString().equals(txtContraeñaCheck.getText().toString())){
          if(txtCod.getText().toString().equals("MedicalApp$Admin$Validation2022")){
              Usuario usuario = crearUsuario();
                if(validarUsuario(usuario) == true){ //Valicdación de que todos los campos sean llenado
                    helper.abrir(); //Apertura de la base de datos e ingreso de los datos en esta
                    helper.insertarUsuarioEmpleado(usuario.getUser(),usuario.getPassword());
                    helper.cerrar(); //Clausura de la conexión a la base datos
                    UIUtils.mostrarMensajeToast(this,"Usuario Guardado");
                    limpiartxtsActividadRegistrarEmpleado(); //Reinicio de los campos de texto
                }
                else{
                    UIUtils.mostrarMensajeToast(this, "Todos los campos deben ser llenados");
                }
          }
          else{
              UIUtils.mostrarMensajeToast(this, "Codigo de Validación Incorrecto");
          }
      }
      else{
          UIUtils.mostrarMensajeToast(this, "Las Contraseñas Deben Coincidir");
      }

    }

    /** Método para la creación de un objeto del tipo cliente*/
    public Usuario crearUsuario(){
        Usuario usuario1 = new Usuario(txtUserEmpleado.getText().toString(),txtContraseña.getText().toString());
        return usuario1;

    }

    /** Método para validar que los campos de texto de la Actividad no se encuentren vacíos*/
    public boolean validarUsuario(Usuario usuario1){
        if (usuario1.getUser().isEmpty() | usuario1.getPassword().isEmpty()) {
            return false;
        }
        else {
            return true;}
    }

    public void limpiartxtsActividadRegistrarEmpleado(){
        txtUserEmpleado.setText(null);
        txtContraseña.setText(null);
        txtContraeñaCheck.setText(null);
        txtCod.setText(null);
    }
}