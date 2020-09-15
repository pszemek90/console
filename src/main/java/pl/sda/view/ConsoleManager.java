package pl.sda.view;

import pl.sda.dao.EntityManagerFactoryService;
import pl.sda.dao.EmployeeDAO;
import pl.sda.dao.TaskDAO;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleManager {

    private EmployeeManager employeeManager = new EmployeeManager();
    private TaskManager taskManager = new TaskManager();
    private EmployeeDAO employeeDAO = new EmployeeDAO(EntityManagerFactoryService.getInstance());
    private TaskDAO taskDAO = new TaskDAO(EntityManagerFactoryService.getInstance());

    public void start() {
        char userChoice = ' ';
        while (userChoice != 'q') {
            printMenu();
            userChoice = readChar();
            executeAction(userChoice);
        }

    }

    private void printMenu() {
        clrscr();
        System.out.println("Menu:");
        System.out.println("1 - Employee list");
        System.out.println("2 - Add employee");
        System.out.println("3 - Task list");
        System.out.println("4 - Add task");
        System.out.println();
        System.out.println("q - Quit");
    }

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }


    private void executeAction(char userChoice) {
        switch (userChoice) {
            case '1':
                List<Employee> employees = employeeManager.fetchEmployees(employeeDAO);
                employeeManager.printList(employees);
                printMenuForEmployeeList();
                userChoice = readChar();
                chooseEmployeeListSubmenuOption(userChoice);
                pressEnterKeyToContinue();
                break;
            case '2':
                employeeManager.addEmployee(employeeDAO);
                break;
            case '3':
                List<Task> tasks = taskManager.fetchTasks(taskDAO);
                taskManager.printList(tasks);
                printMenuForTaskList();
                userChoice = readChar();
                chooseTaskListSubmenuOption(userChoice);
                pressEnterKeyToContinue();
                break;
            case '4':
                taskManager.addTask(taskDAO);
                break;
        }
    }

    private void chooseEmployeeListSubmenuOption(char userChoice) {
        switch (userChoice){
            case '1':
                taskManager.findTasksByEmployee(taskDAO);
                break;
            default:
                System.out.println("Wrong option!");
        }
    }

    private void printMenuForEmployeeList() {
        System.out.println("1 - Find employees tasks");
    }

    private void chooseTaskListSubmenuOption(char userChoice) {
        switch (userChoice){
            case '1':
                taskManager.addEmployeeToTask(taskDAO);
                break;
            default:
                System.out.println("Wrong option!");
        }
    }

    private void printMenuForTaskList() {
        System.out.println("1 - Add employee to task");
    }


    public void pressEnterKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        readChar();
    }

    private char readChar() {
        Scanner s = new Scanner(System.in);
        return (char) s.nextLine().chars().findFirst().orElse(0);
    }
}
