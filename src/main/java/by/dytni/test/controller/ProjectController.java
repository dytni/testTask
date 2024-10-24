package by.dytni.test.controller;

import by.dytni.test.models.Project;
import by.dytni.test.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

///
/// Контроллер для работы с проектами
///

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
        private final ProjectService projectService;

        //добавление проекта
        @PreAuthorize("hasAuthority('ADMIN')")
        @PostMapping("/add")
        public void addProject(@RequestParam("name") String name,
                               @RequestParam("description") String description) {
            projectService.save(name, description);
        }
        //получение проекта по имени если имя не передано получение всех проектов (Доступ имеет как админ, так и обычный пользователь)
        @GetMapping("/get")
        public List<Project> getProjects(@RequestParam(name = "name", required = false)String name){
            if (name == null || name.isEmpty()) {
                return projectService.findAll();
            }else{
                return projectService.findByName(name);
            }
        }

        //удаление проекта
        @PreAuthorize("hasAuthority('ADMIN')")
        @DeleteMapping("/delete")
        public void deleteProject(@RequestParam(name = "id") Long id){
            projectService.delete(id);
        }
        //обновление проекта
        @PreAuthorize("hasAuthority('ADMIN')")
        @PatchMapping("/update")
        public void updateProject(@RequestParam(name = "id") Long id,
                                  @RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "description", required = false) String description) {
            projectService.update(id,name,description);
        }
}
