package by.dytni.test.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "record")
@Table(name = "records")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonBackReference
    private Project project;

    @Column(name = "start_time") // Указание столбца в таблице
    private LocalDateTime startTime;

    @Column(name = "end_time") // Указание столбца в таблице
    private LocalDateTime endTime;

    @Column(name = "description") // Указание столбца в таблице
    private String description;

    @Column(name = "name") // Указание столбца в таблице
    private String name;
}
