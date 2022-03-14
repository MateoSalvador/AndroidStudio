package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.ConexionSqlUsuariosCliente;
import com.example.medicalapp.Clases.Usuario;
import com.example.medicalapp.UtilidadesUI.UIUtils;

public class ActividadRegistrarUsuarioCliente extends AppCompatActivity {

    Button btnGuardaruserCliente, btnCancelarCliente;
    EditText txtUserCliente, txtContraseñaCliente, txtContraeñaClienteCheck;
    ConexionSqlUsuariosCliente helper = new ConexionSqlUsuariosCliente(this,"MedicalBD4",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_registrar_usuario_cliente);
        btnGuardaruserCliente = (Button) findViewById(R.id.btnGuardarRegistroCliente);
        btnCancelarCliente = (Button) findViewById(R.id.btnCancelarRegistroCliente);
        txtUserCliente = (EditText) findViewById(R.id.txtRegistrarUsuarioCliente);
        txtContraseñaCliente = (EditText) findViewById(R.id.txtRegistrarContraseñaCliente);
        txtContraeñaClienteCheck = (EditText) findViewById(R.id.txtConfirmarContraseñaCliente);

        btnGuardaruserCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarUsuarioCliente(view);
            }
        });

        btnCancelarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActividadLoginCliente.class);
                startActivity(intent);
            }
        });

    }

    public void guardarUsuarioCliente(View view){
        if(txtContraseñaCliente.getText().toString()==txtContraeñaClienteCheck.getText().toString()){
                Usuario usuario = crearUsuarioCliente();
                if(validarUsuarioCliente(usuario) == true){ //Valicdación de que todos los campos sean llenado
                    helper.abrir(); //Apertura de la base de datos e ingreso de los datos en esta
                    helper.insertarUsuarioCliente(usuario.getUser(),usuario.getPassword());
                    helper.cerrar(); //Clausura de la conexión a la base datos
                    UIUtils.mostrarMensajeToast(this,"Usuario Guardado");
                    limpiartxtsActividadRegistrarUsuarioCliente(); //Reinicio de los campos de texto
                }
                else{
                    UIUtils.mostrarMensajeToast(this, "Todos los campos deben ser llenados");
                }
        }
        else{
            UIUtils.mostrarMensajeToast(this, "Las Contraseñas Deben Coincidir");
        }

    }

    public Usuario crearUsuarioCliente(){
        Usuario usuario1 = new Usuario(txtUserCliente.getText().toString(),txtContraseñaCliente.getText().toString());
        return usuario1;

    }

    /** Método para validar que los campos de texto de la Actividad no se encuentren vacíos*/
    public boolean validarUsuarioCliente(Usuario usuario1){
        if (usuario1.getUser().isEmpty() | usuario1.getPassword().isEmpty()) {
            return false;
        }
        else {
            return true;}
    }

    public void limpiartxtsActividadRegistrarUsuarioCliente(){
        txtUserCliente.setText(null);
        txtContraseñaCliente.setText(null);
        txtContraeñaClienteCheck.setText(null);
    }
}