package pl.sda.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Runner")
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "best_time")
    private LocalTime bestTime;
    @Column(name = "current_run_time")
    private LocalTime currentTime;

    public Runner(String firstName, String lastName, LocalTime bestTime, LocalTime currentTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bestTime = bestTime;
        this.currentTime = currentTime;
    }
}
