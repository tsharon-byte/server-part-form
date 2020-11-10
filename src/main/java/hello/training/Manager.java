package hello.training;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String login;
    private String email;
    private String pwd;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Employee> employeeList;
}
