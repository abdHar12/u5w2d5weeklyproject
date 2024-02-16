package harouane.u5w2d5weeklyproject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String avatar;
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    List<Device> devices;

    public Employee(String username, String email, String name, String surname) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.avatar= "https://ui-avatars.com/api/?name" + name + "+" + surname;
    }
}
