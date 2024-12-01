package DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import database.MongoDBConnection;
import modelo.Profesor;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class ProfesorDAO {
    private final MongoCollection<Profesor> coleccion;

    public ProfesorDAO() {
        coleccion = new MongoDBConnection().getProfesoresCollection();
    }

    public void insertProfesor(Profesor profesor) {
        coleccion.insertOne(profesor);
    }

    public ArrayList<Profesor> getProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();
        MongoCursor<Profesor> cursor = coleccion.find().iterator();
        while (cursor.hasNext()) {
            profesores.add(cursor.next());
        }
        return profesores;
    }

    public ArrayList<Profesor> findProfesoresByAgeRange(int minAge, int maxAge) {
        Bson filtro = Filters.and(Filters.gte("age", minAge), Filters.lte("age", maxAge));
        FindIterable<Profesor> iterable = coleccion.find(filtro);
        MongoCursor<Profesor> cursor = iterable.cursor();
        ArrayList<Profesor> profesores = new ArrayList<>();
        while (cursor.hasNext()) {
            profesores.add(cursor.next());
        }
        return profesores;
    }

    public boolean updateProfesorRatingByEmail(String email, double nuevoRating) {
        Bson filtro = Filters.eq("email", email);
        Bson actualizacion = Updates.set("rating", nuevoRating);
        return coleccion.updateOne(filtro, actualizacion).getModifiedCount() > 0;
    }
}
