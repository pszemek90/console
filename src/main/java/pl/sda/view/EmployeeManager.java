package pl.sda.view;

import pl.sda.dao.EmployeeDAO;
import pl.sda.dto.Employee;
import pl.sda.view.table.TablePrinter;

import java.util.List;
import java.util.Scanner;

public class EmployeeManager {

    public List<Employee> fetchEmployees(EmployeeDAO employeeDAO){
        return employeeDAO.readAll();
    }

    public void printList(List<Employee> employees) {
        TablePrinter<Employee> tablePrinter = new TablePrinter<Employee>()
                .withData(employees)
                .withColumn("Imię", Employee::getFirstName)
                .withColumn("Nazwisko", Employee::getLastName)
                .withColumn("Stanowisko", Employee::getPosition)
                .withColumn("Wynagrodzenie", employee -> employee.getSalary().toString())
                .withColumn("Rok urodzenia", employee -> employee.getBirthYear().toString());
        tablePrinter.printTable();
    }

    public void addEmployee(EmployeeDAO employeeDAO) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj stanowisko ");
        String position = scanner.nextLine();
        System.out.println("Podaj wynagrodzenie: ");
        Integer salary = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj rok urodzenia: ");
        Integer birthYear = Integer.parseInt(scanner.nextLine());
        employeeDAO.create(new Employee(firstName, lastName, position, salary, birthYear));
    }
}
