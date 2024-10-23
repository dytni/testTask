package by.dytni.test.controller;

import by.dytni.test.models.Project;
import by.dytni.test.service.ProjectService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
        private final ProjectService projectService;

        @PreAuthorize("hasAuthority('ADMIN')")
        @PostMapping("/add")
        public void addProject(@RequestParam("name") String name,
                               @RequestParam("description") String description) {
            Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            projectService.save(project);
        }

        @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
        @GetMapping("/get")
        public List<Project> getProjects(@RequestParam(name = "name", required = false)String name){
            if (name == null || name.isEmpty()) {
                return projectService.findAll();
            }else{
                return projectService.findByName(name);
            }
        }


        @PreAuthorize("hasAuthority('ADMIN')")
        @DeleteMapping("/delete")
        public void deleteProject(@RequestParam(name = "id") Long id){
            projectService.delete(id);
        }
        @PreAuthorize("hasAuthority('ADMIN')")
        @PatchMapping("/update")
        public void updateProject(@RequestParam(name = "id") Long id,
                                  @RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "description", required = false) String description) {
            projectService.update(id,name,description);
        }
}
