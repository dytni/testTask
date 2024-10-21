package by.dytni.test.repository;

import by.dytni.test.models.RecordTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordTime, Long> {

    // Поиск записей по user_id
    List<RecordTime> findByUserId(Long userId);

    // Поиск записей по project_id
    List<RecordTime> findByProjectId(Long projectId);


}
