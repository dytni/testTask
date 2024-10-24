package by.dytni.test.repository;

import by.dytni.test.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    // Поиск записей по username
    List<Record> findByUserUsername(String username);

    void deleteAllByProjectId(Long projectId);

    List<Record> findByProjectName(String projectName);

    void deleteAllByUserId(Long id);

}

