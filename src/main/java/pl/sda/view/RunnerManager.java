package pl.sda.view;

import pl.sda.dao.PersistenceUnitName;
import pl.sda.dao.RunnerDAO;
import pl.sda.dto.Employee;
import pl.sda.dto.Runner;
import pl.sda.view.table.TablePrinter;

import java.util.ArrayList;
import java.util.List;

public class RunnerManager {

    RunnerDAO runnerDAO = new RunnerDAO(PersistenceUnitName.PERSISTENCE_UNIT_NAME);
    private List<Runner> runners = runnerDAO.readAll();

    public void printList() {
        TablePrinter<Runner> tablePrinter = new TablePrinter<Runner>()
                .withData(runners)
                .withColumn("Imię", Runner::getFirstName)
                .withColumn("Nazwisko", Runner::getLastName)
                .withColumn("Najlepszy czas", runner -> runner.getBestTime().toString())
                .withColumn("Aktualny czas", runner -> runner.getCurrentTime().toString());
        tablePrinter.printTable();
    }

    public void addEmployee() {
        
    }


}
