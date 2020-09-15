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
                .withColumn("First name", Employee::getFirstName)
                .withColumn("Last name", Employee::getLastName)
                .withColumn("Position", Employee::getPosition)
                .withColumn("Salary", employee -> employee.getSalary().toString())
                .withColumn("Birth year", employee -> employee.getBirthYear().toString());
        tablePrinter.printTable();
    }

    public void addEmployee(EmployeeDAO employeeDAO) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Set last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Set position: ");
        String position = scanner.nextLine();
        System.out.println("Set salary: ");
        Integer salary = Integer.parseInt(scanner.nextLine());
        System.out.println("Set birth year: ");
        Integer birthYear = Integer.parseInt(scanner.nextLine());
        employeeDAO.create(new Employee(firstName, lastName, position, salary, birthYear));
    }
}
