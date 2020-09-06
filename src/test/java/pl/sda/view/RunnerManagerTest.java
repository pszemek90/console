package pl.sda.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.dao.RunnerDAO;
import pl.sda.dao.TestEntityManagerFactoryService;
import pl.sda.dto.Runner;

import java.util.List;

class RunnerManagerTest {
    private RunnerDAO runnerDAO;
    private RunnerManager runnerManager;

    @BeforeEach
    void setUp() {
        TestEntityManagerFactoryService.start();
        runnerDAO = new RunnerDAO(TestEntityManagerFactoryService.getInstance());
        runnerManager = new RunnerManager();
    }

    @Test
    void shouldPrintRunners() {
        //given
        List<Runner> runners = runnerManager.fetchRunners(runnerDAO);
        //when
        runnerManager.printList(runners);
    }

    @AfterEach
    void tearDown() {
        TestEntityManagerFactoryService.close();
    }
}