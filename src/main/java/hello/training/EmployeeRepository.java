package hello.training;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
