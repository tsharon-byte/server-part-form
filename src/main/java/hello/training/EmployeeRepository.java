package hello.training;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
