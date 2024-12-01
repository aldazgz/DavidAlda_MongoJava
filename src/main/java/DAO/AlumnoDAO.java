package DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.MongoDBConnection;
import modelo.Alumno;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class AlumnoDAO {
    private final MongoCollection<Alumno> coleccion;

    public AlumnoDAO() {
        coleccion = new MongoDBConnection().getAlumnosCollection();
    }

    public void insertAlumno(Alumno alumno) {
        coleccion.insertOne(alumno);
    }

    public ArrayList<Alumno> getAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        MongoCursor<Alumno> cursor = coleccion.find().iterator();
        while (cursor.hasNext()) {
            alumnos.add(cursor.next());
        }
        return alumnos;
    }

    public Alumno getAlumnoByEmail(String email) {
        return coleccion.find(Filters.eq("email", email)).first();
    }

    public void deleteAlumnosByCalification(int calif) {
        Bson filter = Filters.lte("calification", calif);
        coleccion.deleteMany(filter);
    }
}