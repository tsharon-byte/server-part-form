package hello.training;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String currentUserRole;
    private String recommend;
    private String language;
    private String comments;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, int age, String currentUserRole, String recommend, String language, String comments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.currentUserRole = currentUserRole;
        this.recommend = recommend;
        this.language = language;
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee person = (Employee) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", currentUserRole='" + currentUserRole + '\'' +
                ", recommend='" + recommend + '\'' +
                ", language='" + language + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
