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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.ConexionSqlMedicamentos;
import com.example.medicalapp.Clases.Medicamento;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditarEliminarMedicamento extends AppCompatActivity {

    EditText txtNombre, txtCostoUnitario, txtPVP, txtFecha, txtProveedor;
    Button btnGuardar, btnEliminar, btnRegresar;
    String id;
    Spinner spnTIpo;
    Medicamento medicamento;
    boolean correct;
    final Calendar calendario = Calendar.getInstance();
    /** Estableciemiento de una conexión SQL mediante la cual es posible Crear una base de datos
     * en la cual registrar los datos del cliente*/
    ConexionSqlMedicamentos helper = new ConexionSqlMedicamentos(this,"MedicalBD2",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar_medicamento);
        txtNombre = (EditText) findViewById(R.id.txtNombreMedicamentoEditor);
        spnTIpo = (Spinner) findViewById(R.id.spnTipoMedicamentoEditor);
        txtCostoUnitario = (EditText) findViewById(R.id.txtCostoUnitarioEditor);
        txtPVP = (EditText) findViewById(R.id.txtPVPEditor);
        txtFecha = (EditText) findViewById(R.id.txtFechaVencimientoEditor);
        txtProveedor = (EditText) findViewById(R.id.txtProveedorEditor);
        btnGuardar = (Button) findViewById(R.id.btnGuardarEdicionMedicamentos);
        btnEliminar = (Button) findViewById(R.id.btnEliminarMedicamento);
        btnRegresar = (Button) findViewById(R.id.btnRegresarAVerMedicamento);

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
                new DatePickerDialog(EditarEliminarMedicamento.this, date, calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            id = extras.getString("ID");
        } else {
            id = savedInstanceState.getString("ID");
        }

        medicamento = helper.verMedicamento(id);

        if ( medicamento != null){
            txtNombre.setText(medicamento.getNombreMedicamento());
            txtCostoUnitario.setText(String.valueOf(medicamento.getCostoUnitario()));
            txtPVP.setText(String.valueOf(medicamento.getPvp()));
            txtFecha.setText(medicamento.getFechaVencimiento());
            txtProveedor.setText(medicamento.getProveedor());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarMedicamento(medicamento)==true){
                    correct = helper.editarMedicamento(txtNombre.getText().toString(),spnTIpo.getSelectedItem().toString(),
                            Float.parseFloat(txtCostoUnitario.getText().toString()),Float.parseFloat(txtPVP.getText().toString()),txtFecha.getText().toString(),txtProveedor.getText().toString());
                    if(correct){
                        Toast.makeText(EditarEliminarMedicamento.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(EditarEliminarMedicamento.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarEliminarMedicamento.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActividadVerMedicamentos.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditarEliminarMedicamento.this);
                builder.setMessage("¿Desea eliminar este medicamento?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                helper.eliminarMedicamento(txtNombre.getText().toString());
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

    public boolean validarMedicamento(Medicamento medicamento1){
        if (medicamento1.getNombreMedicamento().isEmpty() | medicamento1.getTipoMedicamento().isEmpty() | medicamento1.getCostoUnitario() == 0 |
                medicamento1.getPvp() ==0 | medicamento1.getProveedor().isEmpty() | medicamento1.getFechaVencimiento().isEmpty()) {
            return false;
        }
        else {
            return true;}
    }

    private void lista(){
        Intent intent = new Intent(this, ActividadVerMedicamentos.class);
        startActivity(intent);
    }


}