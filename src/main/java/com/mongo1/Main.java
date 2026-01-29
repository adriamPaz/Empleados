package com.mongo1;


public class Main {
    public static void main(String[] args) {
        Empleado juanito = new Empleado(1,"Juan", 10, 1000, "10/10/1999");
        Empleado ali = new Empleado(2, "Alicia", 10, 1400, "07/08/2000","Profesora");
        Empleado mari = new Empleado(3, "Maria Jesus", 20, 1500, "05/01/2005","Analista",100);
        Empleado berto = new Empleado(4,"Alberto" ,20, 1100, "15/11/2001");
        Empleado fer = new Empleado(5, "Fernando", 30, 1400, "20/11/1999", "Analista", 200);



        EmpleadoCRUD crudpleado = new EmpleadoCRUD();
        //crudpleado.insertarEmpleado(juanito);
        //crudpleado.insertarEmpleado(ali);
        //crudpleado.insertarEmpleado(mari);
        //crudpleado.insertarEmpleado(berto);
        //crudpleado.insertarEmpleado(fer);

        //crudpleado.findByDept(10);
        //crudpleado.findByDep(10, 20);
        //crudpleado.findSalaryHigherThanAndJob(1300, "Profesora");
        //crudpleado.increaseOficioSalario(100, "Analista");
        //crudpleado.updateComision(-20);
        crudpleado.salAvg();


        //crudpleado.increaseEmpleadoSalario(juanito, 250);


        //crudpleado.deleteEmpleado("Juan");
        
    }

    
}




