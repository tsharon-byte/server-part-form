package hello.training;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ManagerRepository extends CrudRepository<Manager, UUID> {
    Optional<Manager> findById(UUID id);
    List<Manager> findByLogin(String login);
    List<Manager> findByLoginAndPwd(String login, String pwd);
}
