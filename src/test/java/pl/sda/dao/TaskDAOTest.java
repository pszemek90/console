package pl.sda.dao;

import org.junit.jupiter.api.*;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskDAOTest {
    private TaskDAO taskDAO;
    private EmployeeDAO employeeDAO;
    private Integer taskId;
    private Integer employeeId;

    @BeforeAll
    static void beforeAll() {
        TestEntityManagerFactoryService.start();
    }

    @AfterAll
    static void afterAll() {
        TestEntityManagerFactoryService.close();
    }

    @BeforeEach
    void setUp() {
        taskDAO = new TaskDAO(TestEntityManagerFactoryService.getInstance());
        Task task = new Task("porządek", "zrobić porządek w konferencyjnym");
        taskDAO.create(task);
        taskId = task.getId();
        Employee employee = new Employee("Janusz", "Korczak", "pisarz", 1000, 1840);
        employeeDAO = new EmployeeDAO(TestEntityManagerFactoryService.getInstance());
        employeeDAO.create(employee);
        employeeId = employee.getId();
    }

    @Test
    void shouldCreateTask() {
        //when
        Task task = new Task("implementacja", "zaimplementować coś sensownego");
        boolean result = taskDAO.create(task);
        //then
        assertTrue(result);
    }

    @Test
    void shouldReadAllTasks() {
        //when
        List<Task> tasks = taskDAO.readAll();
        //then
        assertNotNull(tasks);
    }

    @Test
    void shouldReadTask() {
        //when
        Optional<Task> optionalTask = taskDAO.read(taskId);
        //then
        assertEquals(taskId, optionalTask.map(Task::getId)
                .orElseThrow(NoSuchElementException::new));
    }

    @Test
    void shouldDeleteEmployee() {
        //when
        try {
            taskDAO.delete(taskId);
        } catch (IllegalArgumentException e) {
            System.out.println("No such entity");
        }
        //then
        assertEquals(Optional.empty(), taskDAO.read(taskId));
    }

    @Test
    void shouldAddEmployeeToTaskList(){
        //when
        taskDAO.addEmployeeToTask(employeeId, taskId);
        Task task = taskDAO.read(taskId).orElseThrow(NoSuchElementException::new);
        Integer listSize = task.getEmployees().size();
        //then
        assertEquals(1, listSize);
    }

    @Test
    void shouldSearchTaskByEmployee(){
        //given
        taskDAO.addEmployeeToTask(employeeId, taskId);
        Task task = taskDAO.read(taskId).orElseThrow();
        //when
        List<Task> tasks = taskDAO.searchTaskByEmployee(employeeId);
        //then
        assertTrue(tasks.contains(task));
    }

    @AfterEach
    void tearDown() {
        taskDAO.deleteAll();
        employeeDAO.deleteAll();
    }
}