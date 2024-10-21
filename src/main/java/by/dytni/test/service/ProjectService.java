package by.dytni.test.service;

import by.dytni.test.models.Project;
import by.dytni.test.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }
    public Project findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

}
