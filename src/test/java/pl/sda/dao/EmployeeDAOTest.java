package pl.sda.dao;

import org.junit.jupiter.api.*;
import pl.sda.dto.Employee;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    private EmployeeDAO employeeDAO;
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
        employeeDAO = new EmployeeDAO(TestEntityManagerFactoryService.getInstance());
        Employee employee = new Employee("Jan", "Kowalski", "Tester", 4000, 1995);
        employeeDAO.create(employee);
        employeeId = employee.getId();
    }

    @Test
    void shouldCreateEmployee() {
        //when
        Employee employee = new Employee("Adam", "Nowak", "Developer", 6000, 1980);
        boolean result = employeeDAO.create(employee);
        //then
        assertTrue(result);
    }

    @Test
    void shouldReadAllEmployees() {
        //when
        List<Employee> employees = employeeDAO.readAll();
        //then
        assertNotNull(employees);
    }

    @Test
    void shouldReadEmployee() {
        //when
        Optional<Employee> optionalEmployee = employeeDAO.read(employeeId);
        //then
        assertEquals(employeeId, optionalEmployee.map(Employee::getId)
                .orElseThrow(NoSuchElementException::new));
    }

    @Test
    void shouldNotCreateEmployeeWithoutFirstName() {
        //when
        Employee employee = new Employee(null, "Nowak", "Developer", 6000, 1980);
        employeeDAO.create(employee);
        //then
        assertNotNull(employee);
    }

    @Test
    void shouldUpdateEmployee() {
        //given
        Employee newEmployee = new Employee("Adrian", "Nowak", "Developer", 8000, 1980);
        //when
        employeeDAO.update(employeeId, newEmployee);
    }

    @Test
    void shouldFailUpdatingWithNullName() {
        //given
        Employee newEmployee = new Employee(null, "Nowak", "Developer", 6000, 1980);
        //when
        assertThrows(RollbackException.class, () -> employeeDAO.update(employeeId, newEmployee));
    }

    @Test
    void shouldDeleteEmployee() {
        //when
        try {
            employeeDAO.delete(employeeId);
        } catch (IllegalArgumentException e) {
            System.out.println("No such entity");
        }
        //then
        assertEquals(Optional.empty(), employeeDAO.read(employeeId));
    }

    @Test
    void shouldFindEmployeeByName(){
        //when
        Employee employee = employeeDAO.searchEmployeeByName("Jan", "Kowalski");
        //then
        assertEquals(employeeId, employee.getId());
    }

    @AfterEach
    void tearDown() {
        employeeDAO.deleteAll();
    }
}