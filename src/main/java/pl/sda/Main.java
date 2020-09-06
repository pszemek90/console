package pl.sda;

import pl.sda.dao.RunnerDAO;
import pl.sda.dto.Runner;
import pl.sda.view.ConsoleManager;

import java.io.IOException;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
//        RunnerDAO runnerDAO = new RunnerDAO();
//        Runner runner = new Runner("sdas", "asda", LocalTime.NOON, LocalTime.NOON);
//        runnerDAO.create(runner, "jpa.hibernate");
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.start();
    }


}
