import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class User {
    @Id
    @GeneratedValue
    public int id;
    public string name;
    public string email;
    public int age;
    public LocalDateTime created_at;
}
