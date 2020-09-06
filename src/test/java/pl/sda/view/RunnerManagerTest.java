package pl.sda.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.dao.RunnerDAO;
import pl.sda.dao.TestPersistenceUnitName;
import pl.sda.dto.Runner;

import java.util.List;

class RunnerManagerTest {
    private RunnerDAO runnerDAO;
    private RunnerManager runnerManager;

    @BeforeEach
    void setUp() {
        runnerDAO = new RunnerDAO(TestPersistenceUnitName.TEST_PERSISTENCE_UNIT_NAME);
        runnerManager = new RunnerManager();
    }

    @Test
    void shouldPrintRunners() {
        //given
        List<Runner> runners = runnerManager.fetchRunners(runnerDAO);
        //when
        runnerManager.printList(runners);
    }
}