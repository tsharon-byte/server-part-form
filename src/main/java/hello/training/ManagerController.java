package hello.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin
public class ManagerController {
    ManagerService service;
    EmployeeService employeeService;
    private static final Logger logger
            = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    public ManagerController(ManagerService service, EmployeeService employeeService) {
        this.service = service;
        this.employeeService = employeeService;
    }

    @ResponseBody
    @RequestMapping(value = "/training/register", method = RequestMethod.POST)
    public ResponseEntity<?> Manager(@RequestBody Manager manager) {
        logger.info("Register..." + manager.toString());
        List<Manager> list = service.findByLogin(manager.getLogin());
        if (!list.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            Manager savedManager = service.save(manager);
            return ResponseEntity.ok(savedManager);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/training/login", method = RequestMethod.POST)
    public ResponseEntity<?> ManagerList(@RequestBody Manager manager) {
        logger.info("Login..." + manager.toString());
        List<Manager> list = service.findByLoginAndPwd(manager.getLogin(), manager.getPwd());
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list.get(0));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/training/employee/{id}", method = RequestMethod.POST)
    public ResponseEntity employee(@RequestBody Employee employee, @PathVariable UUID id) {
        Optional<Manager> manager = service.findById(id);
        if (manager.isPresent()) {
            Employee emp = employeeService.save(employee);
            if (manager.get().getEmployeeList() == null) {
                manager.get().setEmployeeList(new ArrayList<>());
            }
            manager.get().getEmployeeList().add(emp);
            service.save(manager.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/training/manager/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> employeeList(@PathVariable UUID id) {
        Optional<Manager> manager = service.findById(id);
        if (manager.isPresent()) {
            return ResponseEntity.ok(manager.get().getEmployeeList());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/training/employee", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@RequestParam UUID employee_id, @RequestParam UUID manager_id) {

        Optional<Manager> manager = service.findById(manager_id);
        if (manager.isPresent()) {
            service.deleteEmployee(manager.get(), employee_id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
