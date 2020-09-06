package pl.sda.view;

import pl.sda.dao.PersistenceUnitName;
import pl.sda.dao.RunnerDAO;
import pl.sda.dto.Runner;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleManager {

    private RunnerManager runnerManager = new RunnerManager();
    private RunnerDAO runnerDAO = new RunnerDAO(PersistenceUnitName.PERSISTENCE_UNIT_NAME);

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
        System.out.println("1 - Lista zawodników");
        System.out.println("2 - Dodaj zawodnika");
        System.out.println();
        System.out.println("q - Wyjście");
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
                List<Runner> runners = runnerManager.fetchRunners(runnerDAO);
                runnerManager.printList(runners);
                pressEnterKeyToContinue();
                break;
            case '2':
                runnerManager.addRunner();

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
