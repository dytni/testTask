package by.dytni.test.controller;

import by.dytni.test.models.Record;
import by.dytni.test.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

///
///Контроллер для работы с записями
///

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    //начало работы с записью сохранение объекта в бд и id в сессию
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/start")
    public void startRecord(@RequestParam(name = "name")String name,
                          @RequestParam(name = "projectName")String projectName,
                          @RequestParam(name = "description") String description,
                          HttpSession session
    ){
        session.setAttribute("recordId", recordService.startRecord(projectName, name, description));
    }
    //конец работы с записью
    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/end")
    public void endRecord(HttpSession session){
       recordService.endRecord((Long) session.getAttribute("recordId"));
       session.removeAttribute("recordId");
    }
    //удаление записи (Доступ имеет как админ, так и обычный пользователь)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/delete")
    public void deleteRecord(@RequestParam(name = "id") Long id){
        recordService.deleteById(id);
    }
    //обновление записи (Доступ имеет только USER)
    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/update")
    public void updateRecord(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "name", required = false)String name,
                             @RequestParam(name = "description", required = false) String description
    ){
        recordService.update(id, name, description);
    }
    //получение всех записей принадлежащих к одному проекту (Доступ имеет как админ, так и обычный пользователь)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/getByProject")
    public List<Record> getByProject(@RequestParam(name = "projectName") String projectName){
       return recordService.getRecordsByProjectName(projectName);
    }
    //Получить все записи принадлежащие текущему пользователю
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getByUser")
    public List<Record> getByUser(){
       return recordService.getRecordsByUserUsername(
               SecurityContextHolder
                       .getContext()
                       .getAuthentication()
                       .getName()
       );
    }
    //Получить все записи принадлежащие выбранному пользователю
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getByUser")
    public List<Record> getByUser(@RequestParam(name = "username") String username){
       return recordService.getRecordsByUserUsername(username);
    }
}
