package pl.sda.dto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {

    @Id
    private int id;
    private String name;
    private String description;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "employee_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;
}
