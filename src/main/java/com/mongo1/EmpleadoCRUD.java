package com.mongo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

public class EmpleadoCRUD {
    MongoProvider provi = new MongoProvider();

    public void insertarEmpleado(Empleado emp){
        try {
            Document doc = new Document();
            doc.append("Emp_no", emp.getNumEmp());
            doc.append("nombre", emp.getNombre());
            doc.append("dep", emp.getDepartamento());
            doc.append("salario", emp.getSalario());
            doc.append("fechaalta", emp.getFechaalta());
            doc.append("oficio", emp.getOficio());
            doc.append("comision", emp.getComision());

            InsertOneResult insert = provi.empleados().insertOne(doc);
            System.out.println(insert.getInsertedId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarEmpleados(int i){
        for (int j = 0; j < i; j++) {
            
        }
    }


    public void deleteEmpleado(Empleado emp){
        try  {
            DeleteResult deleted = provi.empleados().deleteOne(new Document("nombre", emp.getNombre()));
            System.out.println(deleted.getDeletedCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmpleado(String nombre){
        try  {
            DeleteResult deleted = provi.empleados().deleteOne(new Document("nombre", nombre));
            System.out.println(deleted.getDeletedCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmpleado(int empNum){
        try  {
            DeleteResult del = provi.empleados().deleteOne(new Document("Emp_no", empNum));
            System.out.println(del.getDeletedCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseEmpleadoSalario(Empleado emp,int increase){
        try  {
            Bson filtro1 = Filters.eq("nombre",emp.getNombre());
            Bson update = Updates.inc("salario",increase);
            UpdateResult updated = provi.empleados().updateOne(filtro1, update);
            System.out.println(updated.getModifiedCount());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseOficioSalario(int extra, String oficio){
        try  {
            Bson filtro = Filters.eq("oficio",oficio);
            Bson update = Updates.inc("salario", extra);
            UpdateResult updated = provi.empleados().updateMany(filtro, update);
            System.out.println(updated.getModifiedCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void findByDept(int depto){
        try  {
            Bson filtro = Filters.eq("dep",depto);
            FindIterable<Document> iter = provi.empleados().find(filtro);

            Iterator it = iter.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void findByDep(int depto1, int depto2){
        try  {
            Bson filtro = Filters.or(Filters.eq("dep",depto1),Filters.eq("dep",depto2));
            FindIterable<Document> iter = provi.empleados().find(filtro);

            Iterator it = iter.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void findSalaryHigherThanAndJob(int salario, String oficio){
        try  {
            Bson filtro = Filters.and(Filters.gt("salario", salario),Filters.eq("oficio",oficio));
            FindIterable<Document> iter = provi.empleados().find(filtro);

            Iterator it = iter.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateComision(int cambio){
        try {
            Bson filter = Filters.gte("comision", cambio);
            Bson update = Updates.inc("comision", cambio);
            UpdateResult resultUpdated = provi.empleados().updateMany(filter, update);
            System.out.println(resultUpdated.getModifiedCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void salAvg(){
        try {
            AggregateIterable<org.bson.Document> aggregate = provi.empleados()
                .aggregate(Arrays.asList(Aggregates.group("_id", new BsonField("averageSal", new BsonDocument("$avg", new BsonString("$salario"))))));
            Document result = aggregate.first();
            double age = result.getDouble("averageSal");
            System.out.println(age);
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void numberOfEMployeesAvgSalMaxSalByDep(int dep){
        List<Document> list = new ArrayList<>();
        List<Bson> pipeline = List.of(
                Aggregates.group("$dep", Accumulators.avg("salarioMedio", "$salario"),
                Accumulators.max("salarioMaximo", "$salario")),
                Aggregates.sort(Sorts.ascending("dep")));
            
        try {
            provi.empleados().aggregate(pipeline).into(list);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void higherSalEmpName(){
        BsonField filtrar = Accumulators.max("maxSal", "$salario");
        
    }
}
