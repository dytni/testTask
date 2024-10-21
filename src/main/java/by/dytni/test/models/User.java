package by.dytni.test.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RecordTime> records;


}
