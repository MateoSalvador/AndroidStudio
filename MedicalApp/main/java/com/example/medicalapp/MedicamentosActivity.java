package com.example.medicalapp;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicalapp.Clases.ConexionSqlMedicamentos;
import com.example.medicalapp.Clases.Medicamento;
import com.example.medicalapp.UtilidadesUI.UIUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MedicamentosActivity extends AppCompatActivity {

    private Button btnRegreso;
    private Medicamento medicamento1;
    private EditText txtNombreMedicamento;
    private Spinner spnTIpo;
    private EditText txtCosto;
    private EditText txtPvp;
    private EditText txtFechaVencimiento;
    private EditText txtProveedor;
    private Button btnGuardar;
    final Calendar calendario2 = Calendar.getInstance();

    ConexionSqlMedicamentos helper = new ConexionSqlMedicamentos(this,"MedicalBD2",null,1);
    /** Sobreescritura del metodo onCreate para lectura de cuadros de texto
     * en para capturar los datos del clientee y poder registralo*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        /** Instanciamiento de los elementos visuales en la Actividad de Registro de Medicamentos*/
        txtNombreMedicamento = (EditText) findViewById(R.id.txtNombreMedicamento);
        spnTIpo = (Spinner) findViewById(R.id.spnTipo);
        txtCosto = (EditText) findViewById(R.id.txtCosto);
        txtPvp = (EditText) findViewById(R.id.txtPvp);
        txtFechaVencimiento = (EditText) findViewById(R.id.txtFechaVencimiento);
        txtProveedor = (EditText) findViewById(R.id.txtProveedor);
        btnGuardar = (Button) findViewById(R.id.btnGuardarMedicamento);
        btnRegreso = (Button) findViewById(R.id.btnRegresar);



        /** Configuración del botón para guardar los registros ingresados*/
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarMedicamento(view);
            }
        });
        /** Creación de Date Picker para la creación de visual de un calendario con el cual
        * se llene el campo de fecha de nacimiento del Cliente*/
        btnRegreso.setOnClickListener(new View.OnClickListener() {
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
                calendario2.set(Calendar.YEAR,anio);
                calendario2.set(Calendar.MONTH,mes);
                calendario2.set(Calendar.DAY_OF_MONTH,dia);
                updatelabel2();
            }
        };

        /** Configuración del campo de texto para la fecha de nacimiento con el cual se abrirá el
         * un calendario al seleccionar la caja de texto*/
        txtFechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MedicamentosActivity.this, date, calendario2.get(Calendar.YEAR),
                        calendario2.get(Calendar.MONTH), calendario2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    /** Metodo mediante el cual con previa validación se registra el medicamento con los datos ingresados en los
     * diferentes campos solicitados*/
        public void guardarMedicamento(View view){
            Medicamento medicamento = crearMedicamento();
            if(validarMedicamento(medicamento) == true){
                if(medicamento.getCostoUnitario()<0){
                    UIUtils.mostrarMensajeToast(this,
                            "Costo de medicamento no válido");
                }
                else if(medicamento.getPvp()<0){
                    UIUtils.mostrarMensajeToast(this,
                            "PVP de medicamento no válido");
                }
                else {
                    UIUtils.mostrarMensajeToast(this,
                            "Registro Guardado");
                    helper.abrir(); //Apertura de la base de datos e ingreso de los datos en esta
                    helper.insertarMedicamentos(medicamento.getNombreMedicamento(),medicamento.getTipoMedicamento(),medicamento.getCostoUnitario(),medicamento.getPvp(),
                            medicamento.getFechaVencimiento(),medicamento.getProveedor());
                    helper.cerrar(); //Clausura de la conexión a la base datos
                    limpiartxtsMedicamentosActivity();
                }
            }
            else{
                UIUtils.mostrarMensajeToast(this,
                        "Todos los campos deben ser llenados");
            }
        }

    /** Método para la creación de un objeto del tipo Medicamento*/
        public Medicamento crearMedicamento(){
            float costo = 0.0f;
            float pvp = 0.0f;
            if(!txtCosto.getText().toString().equals("") & !txtCosto.getText().toString().equals(".") & !txtPvp.getText().toString().equals(",")
                    & !txtPvp.getText().toString().equals("-")){
                costo=Float.parseFloat(txtCosto.getText().toString());
            }
            if(!txtPvp.getText().toString().equals("") & !txtPvp.getText().toString().equals(".") & !txtPvp.getText().toString().equals(",")
                    & !txtPvp.getText().toString().equals("-")){
                pvp=Float.parseFloat(txtPvp.getText().toString());
            }
            Medicamento medicamento1 = new Medicamento(txtNombreMedicamento.getText().toString(),spnTIpo.getSelectedItem().toString(),
                    costo,pvp,txtFechaVencimiento.getText().toString(), txtProveedor.getText().toString());
            return medicamento1;

        }
    /** Método para validar que los campos de texto de la Actividad no se encuentren vacíos*/
        public boolean validarMedicamento(Medicamento medicamento1){
            if (medicamento1.getNombreMedicamento().isEmpty() | medicamento1.getTipoMedicamento().isEmpty() | medicamento1.getCostoUnitario() == 0 |
                    medicamento1.getPvp() ==0 | medicamento1.getProveedor().isEmpty() | medicamento1.getFechaVencimiento().isEmpty()) {
                return false;
            }
            else {
                return true;}
        }
    /** Método para visualizar la fecha seleccionada en el formato dia/mes/año */
    private void updatelabel2(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        txtFechaVencimiento.setText(formato.format(calendario2.getTime()));
    }
    public void limpiartxtsMedicamentosActivity(){
        txtNombreMedicamento.setText(null);
        txtCosto.setText(null);
        txtPvp.setText(null);
        txtProveedor.setText(null);
        txtFechaVencimiento.setText(null);

    }
}
