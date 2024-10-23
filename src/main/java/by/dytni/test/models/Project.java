package by.dytni.test.models;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "project")
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<RecordTime> records;
}
