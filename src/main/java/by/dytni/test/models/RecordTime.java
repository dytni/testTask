package by.dytni.test.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "record")
@Table(name = "records")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "start_time") // Указание столбца в таблице
    private LocalDateTime startTime;

    @Column(name = "end_time") // Указание столбца в таблице
    private LocalDateTime endTime;

    @Column(name = "description") // Указание столбца в таблице
    private String description;
}
