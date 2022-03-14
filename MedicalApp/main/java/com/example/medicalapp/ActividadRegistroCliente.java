package com.example.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.medicalapp.Clases.Cliente;
import com.example.medicalapp.UtilidadesUI.UIUtils;
import com.example.medicalapp.Clases.ConexionSql;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.regex.Pattern;

/** Actividad para el registro de datos de un cliente*/
public class ActividadRegistroCliente extends ActividadCicloDeVida {

    private Cliente cliente1;
    private EditText txtNombreCliente;
    private EditText txtApellidoCliente;
    private EditText txtCedula;
    private EditText txtFechaNacimiento;
    private EditText txtCorreo;
    private EditText txtDireccion;
    private Button btnGuardar;
    private Button btnRegresar;
    final Calendar calendario = Calendar.getInstance();
    /** Estableciemiento de una conexión SQL mediante la cual es posible Crear una base de datos
     * en la cual registrar los datos del cliente*/
    ConexionSql helper = new ConexionSql(this,"MedicalBD",null,1);
    /** Sobreescritura del metodo onCreate para lectura de cuadros de texto
     * en para capturar los datos del clientee y poder registralo*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Instanciamiento de los elementos visuales en la Actividad de Registro del Cliente*/

        txtNombreCliente = (EditText) findViewById(R.id.txtNombreCliente);
        txtApellidoCliente = (EditText) findViewById(R.id.txtApellido);
        txtCedula = (EditText) findViewById(R.id.txtID);
        txtFechaNacimiento = (EditText) findViewById(R.id.txtFecha);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        btnGuardar = (Button) findViewById(R.id.btnGuardarCliente);
        btnRegresar = (Button) findViewById(R.id.btnRegresarMenu);

        /** Configuración del botón para guardar los registros ingresados*/
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCliente(view);

            }
        });
        /** Configuración del botón para regresar a la actividad del menú*/
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Generación de un intent para realizar cambio a la actividad del menú*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        /** Creación de Date Picker para la creación de visual de un calendario con el cual
         * se llene el campo de fecha de nacimiento del Cliente*/
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                calendario.set(Calendar.YEAR,anio);
                calendario.set(Calendar.MONTH,mes);
                calendario.set(Calendar.DAY_OF_MONTH,dia);
                updatelabel();
            }
        };
        /** Configuración del campo de texto para la fecha de nacimiento con el cual se abrirá el
         * un calendario al seleccionar la caja de texto*/
        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ActividadRegistroCliente.this, date, calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    /** Método para visualizar la fecha seleccionada en el formato dia/mes/año */
    private void updatelabel(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        txtFechaNacimiento.setText(formato.format(calendario.getTime()));
    }

    /** Metodo mediante el cual con previa validación se registra el cliente con los datos ingresados en los
     * diferentes campos solicitados*/
    public void guardarCliente(View view){
        Cliente cliente = crearCliente();
        if(validarCliente(cliente) == true){ //Valicdación de que todos los campos sean llenado
            if(cliente.getCedula().length()!=10){ // Comprobación de que el campo cédula sea debidamente ingresado
                UIUtils.mostrarMensajeToast(this,
                        "Cédula debe tener 10 dígitos");
            } else if(validarEmail(cliente.getCorreo())==false){ //Comprobación de que el campo coreeo sea correctamente llenado
                UIUtils.mostrarMensajeToast(this,
                        "Correo no valido");
            }
            else {
                UIUtils.mostrarMensajeToast(this,"Registro Guardado");
                            helper.abrir(); //Apertura de la base de datos e ingreso de los datos en esta
                            helper.insertarUsuarios(cliente.getCedula(),cliente.getNombre(),cliente.getApellido(),cliente.getFechaNacimiento(),cliente.getCorreo(),cliente.getDireccion());
                            helper.cerrar(); //Clausura de la conexión a la base datos
                            limpiartxts(); //Reinicio de los campos de texto
            }
        }
        else{
            UIUtils.mostrarMensajeToast(this,
                    "Todos los campos deben ser llenados");
        }
    }

    /** Método para la creación de un objeto del tipo cliente*/
    public Cliente crearCliente(){
        Cliente cliente1 = new Cliente(txtNombreCliente.getText().toString(),txtApellidoCliente.getText().toString(),
                txtCedula.getText().toString(),txtFechaNacimiento.getText().toString(), txtCorreo.getText().toString(),
                txtDireccion.getText().toString());
        return cliente1;

    }

    /** Método para validar que los campos de texto de la Actividad no se encuentren vacíos*/
    public boolean validarCliente(Cliente cliente1){
        if (cliente1.getNombre().isEmpty() | cliente1.getApellido().isEmpty() | cliente1.getCedula().isEmpty() | cliente1.getCorreo().isEmpty() |
                cliente1.getDireccion().isEmpty() | cliente1.getFechaNacimiento().isEmpty()) {
            return false;
        }
        else {
            return true;}
    }
    /** Método para reiniciar los campos de datos de la Actividad una vez se complete el evento de guardar
     * el registro del cliente*/
    public void limpiartxts(){
        txtNombreCliente.setText(null);
        txtApellidoCliente.setText(null);
        txtCedula.setText(null);
        txtFechaNacimiento.setText(null);
        txtCorreo.setText(null);
        txtDireccion.setText(null);
    }
    /** Método para validar que el campo de correo cumpla con sus patrones*/
    public boolean validarEmail(String correoingresado) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(correoingresado).matches();
    }
}
