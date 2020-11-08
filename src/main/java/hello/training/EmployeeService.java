package hello.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName){
        return repository.findByFirstNameAndLastName(firstName,lastName);
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }


    public void deleteEmployee(UUID id) {
        repository.deleteById(id);
    }

    public int deleteEmployee(String firstName, String lastName) {
        List<Employee> list = repository.findByFirstNameAndLastName(firstName, lastName);
        int result = list.size();
        list.forEach(item -> repository.delete(item));
        return result;
    }
    public List<Employee> findAll() {
        return (List<Employee>) repository.findAll();
    }
}
