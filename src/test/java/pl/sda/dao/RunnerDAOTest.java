package pl.sda.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.dto.Runner;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static pl.sda.dao.TestPersistenceUnitName.PERSISTENCE_UNIT_NAME;

class RunnerDAOTest {
    private RunnerDAO runnerDAO;

    @BeforeEach
    void setUp() {
        runnerDAO = new RunnerDAO(PERSISTENCE_UNIT_NAME);
    }

    @Test
    void shouldReadAllRunners() {
        //when
        List<Runner> runners = runnerDAO.readAll();
        //then
        assertNotNull(runners);
    }

    @Test
    void shouldReadRunner() {
        //when
        Optional<Runner> optionalRunner = runnerDAO.read(1);
        //then
        assertEquals(1, optionalRunner.map(Runner::getId).orElseThrow(NoSuchElementException::new));
    }

    @Test
    void shouldCreateRunner() {
        //when
        Runner runner = new Runner("Jan", "Kowalski", LocalTime.parse("00:32:15"), null);
        runnerDAO.create(runner);
        //then
        assertNotNull(runner);
    }

    @Test
    void shouldDeleteRunner() {
        //when
        try {
            runnerDAO.delete(1);
        } catch (IllegalArgumentException e) {
            System.out.println("No such entity");
        }
        //then
        assertNull(runnerDAO.read(1));
    }
}