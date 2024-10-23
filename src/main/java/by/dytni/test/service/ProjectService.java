package by.dytni.test.service;

import by.dytni.test.models.Project;
import by.dytni.test.repository.ProjectRepository;
import by.dytni.test.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
///
/// Сервис для работы с данными таблицы хранящей Project
///

@AllArgsConstructor
@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    private RecordRepository recordRepository;

    //метод для сохранения проекта
    public void save(String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        projectRepository.save(project);
    }
    //метод поиска проекта по имени
    public List<Project> findByName(String name) {
        return List.of(projectRepository.findByName(name).orElse(new Project()));
    }
    //метод получает все проекты
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    //метод обновления проекта
    public void update(Long id, String name, String description) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return;
        }
        if (!(name == null || name.isEmpty())){
            project.setName(name);
        }
        if (!(description == null || description.isEmpty())){
            project.setDescription(description);
        }
        projectRepository.save(project);

    }
    //метод для удаления проекта
    public void delete(Long id) {
        if(projectRepository.existsById(id)) {
            recordRepository.deleteAllByProjectId(id);
            projectRepository.deleteById(id);
        }
    }

}
