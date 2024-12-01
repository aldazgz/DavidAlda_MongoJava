package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    @BsonProperty("_id")
    private String id;
    private double rating;
    private int age;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private List<String> subjects;
    private String title;
}
