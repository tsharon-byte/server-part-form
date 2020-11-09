package hello.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin
public class EmployeeController {
    EmployeeService service;
    private static final Logger logger
            = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping(value = "/training/employee", method = RequestMethod.POST)
    public ResponseEntity employee(@RequestBody Employee employee) {
        logger.info("POST..." + employee.toString());
        List<Employee> list = service.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
        if (!list.isEmpty()) {
            Employee employeeFromDb = list.get(0);
            employeeFromDb.setAge(employee.getAge());
            employeeFromDb.setEmail(employee.getEmail());
            employeeFromDb.setLanguage(employee.getLanguage());
            employeeFromDb.setSourceInformation(employee.getSourceInformation());
            employeeFromDb.setTimeForProgramming(employee.getTimeForProgramming());
            employeeFromDb.setGender(employee.getGender());
            logger.info("updated to DB:" + service.save(employeeFromDb));
        }
        else{
            logger.info("saved to DB:" + service.save(employee));
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/training/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable UUID id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/training/employeeList", method = RequestMethod.GET)
    public List<Employee> employeeList() {
        logger.info("GET all...");
        List<Employee> personsList;
        personsList = service.findAll();
        return personsList;
    }
}
