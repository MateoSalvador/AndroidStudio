package com.example.medicalapp.Clases;

import javax.xml.transform.sax.SAXResult;
/** Creación de la Clase Medicamento*/
public class Medicamento {

    private String nombreMedicamento;
    private String tipoMedicamento;
    private float costoUnitario;
    private float pvp;
    private String fechaVencimiento;
    private String proveedor;

    public Medicamento(){}
/** Constructor de la Clase medicamento*/
    public Medicamento (String strNombre, String strTipo, float costo, float preciovm, String fecha,String strproveedor){
        this.nombreMedicamento = strNombre;
        this.tipoMedicamento = strTipo;
        this.costoUnitario = costo;
        this.pvp = preciovm;
        this.fechaVencimiento = fecha;
        this.proveedor = strproveedor;
    }
/** Creación de getters y setters para los diferentes atribrutos de la clase*/
    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

}
