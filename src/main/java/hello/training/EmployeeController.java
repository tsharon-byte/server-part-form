package hello.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            employeeFromDb.setComments(employee.getComments());
            employeeFromDb.setCurrentUserRole(employee.getCurrentUserRole());
            employeeFromDb.setEmail(employee.getEmail());
            employeeFromDb.setLanguage(employee.getLanguage());
            employeeFromDb.setRecommend(employee.getRecommend());
            logger.info("updated to DB:" + service.save(employeeFromDb));
        }
        else{
            logger.info("saved to DB:" + service.save(employee));
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/training/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/training/employee", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@RequestBody Employee employee) {
        logger.info("DELETE..." + employee.toString());
        int result = service.deleteEmployee(employee.getFirstName(), employee.getLastName());
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
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
