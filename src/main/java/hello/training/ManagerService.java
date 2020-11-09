package hello.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerService {
    ManagerRepository repository;

    @Autowired
    public ManagerService(ManagerRepository repository) {
        this.repository = repository;
    }

    public Manager save(Manager Manager) {
        return repository.save(Manager);
    }

    public List<Manager> findAll() {
        return (List<Manager>) repository.findAll();
    }

    public List<Manager> findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public List<Manager> findByLoginAndPwd(String login, String pwd) {

        return repository.findByLoginAndPwd(login, pwd);
    }

    public Optional<Manager> findById(UUID id) {
        return repository.findById(id);
    }
}
