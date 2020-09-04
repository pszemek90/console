package pl.sda.view;

import pl.sda.dto.Employee;
import pl.sda.view.table.TablePrinter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {


    private List<Employee> employees = new ArrayList<>();

    {
        employees.add(new Employee(1L, "Jan", "Nowak", "programista", 6000, 1991));
        employees.add(new Employee(2L, "Kamil", "Kowalski", "st. programista", 8000, 1986));
    }

    public void printList() {
        TablePrinter<Employee> tablePrinter = new TablePrinter<Employee>()
                .withData(employees)
                .withColumn("Imię", employee -> ((Employee) employee).getFirstName())
                .withColumn("Nazwisko", employee -> ((Employee) employee).getFirstName())
                .withColumn("Stanowisko", employee -> ((Employee) employee).getPosition())
                .withColumn("Rok ur.", employee -> ((Employee) employee).getBirthYear().toString())
                .withColumn("Wynagrodzenie.", employee -> ((Employee) employee).getSalary().toString());

        tablePrinter.printTable();

    }

    public void addEmployee() {

    }


}
