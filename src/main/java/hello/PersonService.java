package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Person> findAll() {
        return (List<Person>) repository.findAll();
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public int deleteEmployee(String firstName, String lastName) {
        List<Person> list = repository.findByFirstNameAndLastName(firstName, lastName);
        int result = list.size();
        list.forEach(item -> repository.delete(item));
        return result;
    }
}
