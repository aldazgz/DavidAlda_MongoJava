package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    @BsonProperty("_id")
    private String id;
    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private int calification;
    private String higherGrade;
    private Boolean FCTs;
}