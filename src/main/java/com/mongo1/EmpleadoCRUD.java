package com.mongo1;

import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class EmpleadoCRUD {
        

    public void insertarEmpleado(Empleado emp){
        try (MongoProvider provi = new MongoProvider();) {
            Document doc = new Document();
            doc.append("Emp_no", emp.getNumEmp());
            doc.append("nombre", emp.getNombre());
            doc.append("dep", emp.getDepartamento());
            doc.append("salario", emp.getSalario());
            doc.append("fechaalta", emp.getFechaalta());
            doc.append("oficio", emp.getOficio());
            doc.append("comision", emp.getComision());

            provi.empleados().insertOne(doc);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void insertarEmpleados(int i){
        for (int j = 0; j < i; j++) {
            
        }
    }


    public void deleteEmpleado(Empleado emp){
        try (MongoProvider provi = MongoProvider.getInstance()) {
            provi.empleados().deleteOne(new Document("nombre", emp.getNombre()));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void deleteEmpleado(String nombre){
        try (MongoProvider provi = MongoProvider.getInstance()) {
            provi.empleados().deleteOne(new Document("nombre", nombre));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void deleteEmpleado(int empNum){
        try (MongoProvider provi = MongoProvider.getInstance()) {
            provi.empleados().deleteOne(new Document("Emp_no", empNum));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void increaseEmpleadoSalario(Empleado emp,int increase){
        try (MongoProvider provider = new MongoProvider()) {
            Bson filtro1 = Filters.eq("nombre",emp.getNombre());
            Bson update = Updates.inc("salario",increase);
            provider.empleados().updateOne(filtro1, update);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void increaseOficioSalario(int extra, String oficio){
        try (MongoProvider pr = new MongoProvider()) {
            Bson filtro = Filters.eq("oficio",oficio);
            Bson update = Updates.inc("salario", extra);
            pr.empleados().updateMany(filtro, update);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void findByDept(int depto){
        try (MongoProvider provider = MongoProvider.getInstance()) {
            Bson filtro = Filters.eq("dep",depto);
            FindIterable<Document> iter = provider.empleados().find(filtro);

            Iterator it = iter.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void findByDep(int depto1, int depto2){
        try (MongoProvider pro = new MongoProvider()) {
            Bson filtro = Filters.or(Filters.eq("dep",depto1),Filters.eq("dep",depto2));
            FindIterable<Document> iter = pro.empleados().find(filtro);

            Iterator it = iter.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void findSalaryHigherThanAndJob(int salario, String oficio){
        try (MongoProvider pro = new MongoProvider()) {
            Bson filtro = Filters.and(Filters.gt("salario", salario),Filters.eq("oficio",oficio));
            FindIterable<Document> iter = pro.empleados().find(filtro);

            Iterator it = iter.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void updateComision(int cambio){
        try (MongoProvider pr = new MongoProvider()) {
            Bson filter = Filters.gt("comision", 0);
            Bson update = Updates.inc("comision", cambio);
            pr.empleados().updateMany(filter, update);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void salAvg(){
        try (MongoProvider pr = new MongoProvider()) {
            BasicDBObject groupField = new BasicDBObject("nombre");
            BasicDBObject avg = new BasicDBObject("$avg", "$salario");
            pr.empleados();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
