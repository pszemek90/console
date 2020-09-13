package pl.sda.view;

import pl.sda.dao.TaskDAO;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;
import pl.sda.view.table.TablePrinter;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskManager {

    public List<Task> fetchTasks(TaskDAO taskDAO){
        return taskDAO.readAll();
    }

    public void printList(List<Task> tasks) {
        TablePrinter<Task> tablePrinter = new TablePrinter<Task>()
                .withData(tasks)
                .withColumn("Nazwa", Task::getName)
                .withColumn("Opis", Task::getDescription)
                .withColumn("Lista pracowników", task -> task.getEmployees()
                        .stream()
                        .map(Employee::getId)
                        .collect(Collectors.toList())
                        .toString());
        tablePrinter.printTable();
    }

    public void addTask(TaskDAO taskDAO) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę: ");
        String name = scanner.nextLine();
        System.out.println("Podaj opis: ");
        String description = scanner.nextLine();
        taskDAO.create(new Task(name, description));
    }
}
