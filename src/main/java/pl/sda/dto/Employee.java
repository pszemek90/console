package pl.sda.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private Integer salary;
    @Column(name = "birth_year")
    private Integer birthYear;
    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Employee(String firstName, String lastName, String position, Integer salary, Integer birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.birthYear = birthYear;
    }
}
