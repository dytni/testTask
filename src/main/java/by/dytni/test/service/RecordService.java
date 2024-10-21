package by.dytni.test.service;

import by.dytni.test.models.RecordTime;
import by.dytni.test.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RecordService {
    private RecordRepository recordRepository;

    public void save(RecordTime record) {
        recordRepository.save(record);
    }
    public RecordTime findById(Long id) {
        Optional<RecordTime> record = recordRepository.findById(id);
        return record.orElse(null);
    }
    public List<RecordTime> getRecordsByUserId(Long userId) {
        return recordRepository.findByUserId(userId);
    }

    public List<RecordTime> getRecordsByProjectId(Long projectId) {
        return recordRepository.findByProjectId(projectId);
    }
    public void deleteById(Long id) {
        recordRepository.deleteById(id);
    }
}
