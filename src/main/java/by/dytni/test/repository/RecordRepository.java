package by.dytni.test.repository;

import by.dytni.test.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    // Поиск записей по username
    List<Record> findByUserUsername(String username);

    void deleteAllByProjectId(Long projectId);

    List<Record> findByProjectName(String projectName);


    @Modifying
    @Transactional
    @Query("DELETE FROM record r WHERE r.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

}

