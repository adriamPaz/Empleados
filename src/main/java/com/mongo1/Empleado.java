package com.mongo1;

public class Empleado {
    private int numEmp;
    private String nombre;
    private int departamento;
    private int salario;
    private String fechaalta;

    private String oficio;
    private int comision;

    
    

    public Empleado(int numEmp, String nombre, int departamento, int salario, String fechaalta, String oficio) {
        this.numEmp = numEmp;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fechaalta = fechaalta;
        this.oficio = oficio;
    }



    public Empleado(int numEmp,String nombre, int departamento, int salario, String fechaalta, String oficio, int comision) {
        this.numEmp = numEmp;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fechaalta = fechaalta;
        this.oficio = oficio;
        this.comision = comision;
    }



    public Empleado(int numEmp, String nombre, int departamento, int salario, String fechaalta) {
        this.numEmp = numEmp;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fechaalta = fechaalta;
    }

    

    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public int getDepartamento() {
        return departamento;
    }



    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }



    public int getSalario() {
        return salario;
    }



    public void setSalario(int salario) {
        this.salario = salario;
    }



    public String getFechaalta() {
        return fechaalta;
    }



    public void setFechaalta(String fechaalta) {
        this.fechaalta = fechaalta;
    }



    public String getOficio() {
        return oficio;
    }



    public void setOficio(String oficio) {
        this.oficio = oficio;
    }



    public int getComision() {
        return comision;
    }



    public void setComision(int comision) {
        this.comision = comision;
    }

    public int getNumEmp() {
        return numEmp;
    }

    @Override
    public String toString() {
        return "Emp bb";
    }
}
