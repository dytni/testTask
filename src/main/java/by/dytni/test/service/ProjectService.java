package by.dytni.test.service;

import by.dytni.test.models.Project;
import by.dytni.test.repository.ProjectRepository;
import by.dytni.test.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RecordRepository recordRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }
    public List<Project> findByName(String name) {
        return List.of(projectRepository.findByName(name).orElse(new Project()));
    }
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
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
    public void delete(Long id) {
        if(projectRepository.existsById(id)) {
            recordRepository.deleteAllByProjectId(id);
            projectRepository.deleteById(id);
        }
    }

}
