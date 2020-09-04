package pl.sda.view;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {

    private EmployeeManager employeeManager = new EmployeeManager();

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
        System.out.println("1 - Lista pracowników");
        System.out.println("2 - Dodaj pracownika");
        System.out.println();
        System.out.println("q - wyjście");

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
                employeeManager.printList();
                pressEnterKeyToContinue();
                break;
            case '2':
                employeeManager.addEmployee();

        }
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
