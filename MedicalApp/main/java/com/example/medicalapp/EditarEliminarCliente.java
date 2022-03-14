package com.example.medicalapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.Cliente;
import com.example.medicalapp.Clases.ConexionSql;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditarEliminarCliente extends AppCompatActivity {

    TextView textCedula;
    EditText txtNombre, txtApellido, txtFecha, txtCorreo, txtDireccion;
    Button btnGuardar, btnEliminar, btnRegresar;
    String id;
    Cliente cliente;
    boolean correct;
    final Calendar calendario = Calendar.getInstance();
    /** Estableciemiento de una conexión SQL mediante la cual es posible Crear una base de datos
     * en la cual registrar los datos del cliente*/
    ConexionSql helper = new ConexionSql(this,"MedicalBD",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar_cliente);
        textCedula = (TextView) findViewById(R.id.txtCédula);
        txtNombre = (EditText) findViewById(R.id.txtNombreEditor);
        txtApellido = (EditText) findViewById(R.id.txtApellidoEditor);
        txtCorreo = (EditText) findViewById(R.id.txtCorreoEditor);
        txtDireccion = (EditText) findViewById(R.id.txtDireccionEditor);
        txtFecha = (EditText) findViewById(R.id.txtFechaEditor);
        btnGuardar = (Button) findViewById(R.id.btnGuardarEdicion);
        btnEliminar = (Button) findViewById(R.id.btnEliminarCliente);
        btnRegresar = (Button) findViewById(R.id.btnRegresarALista);

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
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EditarEliminarCliente.this, date, calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            id = extras.getString("ID");
        } else {
            id = savedInstanceState.getString("ID");
        }

        cliente = helper.verCliente(id);
        if (cliente != null){
            textCedula.setText(cliente.getCedula());
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido());
            txtDireccion.setText(cliente.getDireccion());
            txtCorreo.setText(cliente.getCorreo());
            txtFecha.setText(cliente.getFechaNacimiento());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCliente(cliente)==true){
                  correct = helper.editarCliente(textCedula.getText().toString(),txtNombre.getText().toString(),
                          txtApellido.getText().toString(),txtFecha.getText().toString(),txtCorreo.getText().toString(),txtDireccion.getText().toString());
                    if(correct){
                        Toast.makeText(EditarEliminarCliente.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(EditarEliminarCliente.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarEliminarCliente.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
                }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActividadVerClientes.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditarEliminarCliente.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                helper.eliminarCliente(textCedula.getText().toString());
                                lista();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
    }
    private void updatelabel(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha.setText(formato.format(calendario.getTime()));
    }

    public boolean validarCliente(Cliente cliente1){
        if (cliente1.getNombre().isEmpty() | cliente1.getApellido().isEmpty() | cliente1.getCedula().isEmpty() | cliente1.getCorreo().isEmpty() |
                cliente1.getDireccion().isEmpty() | cliente1.getFechaNacimiento().isEmpty()) {
            return false;
        }
        else {
            return true;}
    }

    private void lista(){
        Intent intent = new Intent(this, ActividadVerClientes.class);
        startActivity(intent);
    }
}