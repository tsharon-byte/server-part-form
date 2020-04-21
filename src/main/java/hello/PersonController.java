package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin
public class PersonController {
    PersonService personService;
    private static final Logger logger
            = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String form(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
        List<Person> personsList;
        personService.save(person);
        personsList = personService.findAll();
        model.addAttribute("personsList", personsList);
        return "persons";
    }

    @ResponseBody
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity employee(@RequestBody Person person) {
        logger.info("POST..." + person.toString());
        List<Person> list = personService.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        if (!list.isEmpty()) {
            Person personFromDb = list.get(0);
            personFromDb.setAge(person.getAge());
            personFromDb.setComments(person.getComments());
            personFromDb.setCurrentUserRole(person.getCurrentUserRole());
            personFromDb.setEmail(person.getEmail());
            personFromDb.setLanguage(person.getLanguage());
            personFromDb.setRecommend(person.getRecommend());
            logger.info("updated to DB:" + personService.save(personFromDb));
        }
        else{
            logger.info("saved to DB:" + personService.save(person));
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        personService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@RequestBody Person person) {
        logger.info("DELETE..." + person.toString());
        int result = personService.deleteEmployee(person.getFirstName(), person.getLastName());
        if(result == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/employeeList", method = RequestMethod.GET)
    public List<Person> employeeList() {
        logger.info("GET all...");
        List<Person> personsList;
        personsList = personService.findAll();
        return personsList;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
        System.out.println(person);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String form(Model model) {
        String[] currentRoles = {"Student", "Full time job"};
        String[] recommendTypes = {"Yes", "No", "Maybe"};
        String[] languageTypes = {"Java", "Java script", "C++"};
        model.addAttribute("currentRoles", currentRoles);
        model.addAttribute("person", new Person());
        model.addAttribute("recommendTypes", recommendTypes);
        model.addAttribute("languageTypes", languageTypes);
        return "form";
    }
}