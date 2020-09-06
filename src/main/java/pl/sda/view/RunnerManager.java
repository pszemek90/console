package pl.sda.view;

import pl.sda.dao.RunnerDAO;
import pl.sda.dto.Runner;
import pl.sda.view.table.TablePrinter;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class RunnerManager {

    public List<Runner> fetchRunners (RunnerDAO runnerDAO){
        return runnerDAO.readAll();
    }

    public void printList(List<Runner> runners) {
        TablePrinter<Runner> tablePrinter = new TablePrinter<Runner>()
                .withData(runners)
                .withColumn("Imię", Runner::getFirstName)
                .withColumn("Nazwisko", Runner::getLastName)
                .withColumn("Najlepszy czas", runner -> runner.getBestTime().toString())
                .withColumn("Aktualny czas", runner -> runner.getOptionalCurrentTime()
                        .map(LocalTime::toString).orElse(""));
        tablePrinter.printTable();
    }

    public void addRunner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj najlepszy czas: ");
        LocalTime bestTime;
        try{
            bestTime = LocalTime.parse(scanner.nextLine());
        } catch (DateTimeParseException exception){
            System.out.println("Zły format czasu!");
            exception.getMessage();
        }
    }


}
