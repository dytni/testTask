package by.dytni.test.controller;

import by.dytni.test.models.Record;
import by.dytni.test.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/start")
    public void startRecord(@RequestParam(name = "name")String name,
                          @RequestParam(name = "projectName")String projectName,
                          @RequestParam(name = "description") String description,
                          HttpSession session
    ){
        session.setAttribute("recordId", recordService.startRecord(projectName, name, description));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/end")
    public void endRecord(HttpSession session){
       recordService.endRecord((Long) session.getAttribute("recordId"));
       session.removeAttribute("recordId");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/delete")
    public void deleteRecord(@RequestParam(name = "id") Long id){
        recordService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PatchMapping("/update")
    public void updateRecord(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "name", required = false)String name,
                             @RequestParam(name = "description", required = false) String description
    ){
        recordService.update(id, name, description);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/getByProject")
    public List<Record> getByProject(@RequestParam(name = "projectName") String projectName){
       return recordService.getRecordsByProjectName(projectName);
    }

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
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getByUser")
    public List<Record> getByUser(@RequestParam(name = "username") String username){
       return recordService.getRecordsByUserUsername(username);
    }
}