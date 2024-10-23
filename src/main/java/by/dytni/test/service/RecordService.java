package by.dytni.test.service;

import by.dytni.test.models.Project;
import by.dytni.test.models.Record;
import by.dytni.test.models.User;
import by.dytni.test.repository.ProjectRepository;
import by.dytni.test.repository.RecordRepository;
import by.dytni.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


///
/// Сервис для работы с данными таблицы хранящей Record
///

@AllArgsConstructor
@Service
public class RecordService {
    private RecordRepository recordRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    //метод добавления новой записи со временем начала равным нынешнему времени и незаполненным временем окончания
    public Long startRecord(String projectName, String name, String description) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElse(null);
        Project project = projectRepository.findByName(projectName).orElse(null);
        if (project == null) {
            return -1L;
        }
        if (user == null) {
            return -1L;
        }
        Record newRecord = new Record();
        newRecord.setUser(user);
        newRecord.setProject(project);
        newRecord.setStartTime(LocalDateTime.now());
        newRecord.setEndTime(null);
        newRecord.setDescription(description);
        newRecord.setName(name);
        return recordRepository.save(newRecord).getId();
    }
    //метод для обновления записи в которой еще нет времени окончания
    public void endRecord(Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            return;
        }
        record.setEndTime(LocalDateTime.now());
        recordRepository.save(record);
    }
    //метод поиска записей по username пользователя
    public List<Record> getRecordsByUserUsername(String userUsername) {
        return recordRepository.findByUserUsername(userUsername);
    }
    //метод поиска записей по projectName проекта
    public List<Record> getRecordsByProjectName(String projectName) {
        return recordRepository.findByProjectName(projectName);
    }
    //метод для удаления проекта
    public void deleteById(Long id) {
        if(recordRepository.existsById(id)) {
            recordRepository.deleteById(id);
        }
    }
    //метод для удаления записи
    public void update(Long id, String name, String description) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            return;
        }
        if (!(name == null || name.isEmpty())){
            record.setName(name);
        }
        if (!(description == null || description.isEmpty())){
            record.setDescription(description);
        }
        recordRepository.save(record);

    }
}
