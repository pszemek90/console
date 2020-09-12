package pl.sda.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.dto.Employee;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {
    private EmployeeDAO employeeDAO;

    @BeforeEach
    void setUp() {
        TestEntityManagerFactoryService.start();
        employeeDAO = new EmployeeDAO(TestEntityManagerFactoryService.getInstance());
    }

    @Test
    void shouldCreateEmployee() {
        //when
        Employee employee = new Employee("Adam", "Nowak", "Developer", 6000, 1980);
        employeeDAO.create(employee);
        //then
        assertNotNull(employee);
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
        Optional<Employee> optionalEmployee = employeeDAO.read(1);
        //then
        assertEquals(1, optionalEmployee.map(Employee::getId).orElseThrow(NoSuchElementException::new));
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
    void shouldUpdateEmployee(){
        //given
        Employee newEmployee = new Employee("Adrian", "Nowak", "Developer", 8000, 1980);
        int id = 1;
        //when
        employeeDAO.update(id, newEmployee);
    }

    @Test
    void shouldFailUpdatingWithNullName(){
        //given
        Employee newEmployee = new Employee(null, "Nowak", "Developer", 6000, 1980);
        int id = 1;
        //when
//        employeeDAO.update(id, newEmployee);
        assertThrows(RollbackException.class, () -> employeeDAO.update(id, newEmployee));
    }

    @Test
    void shouldDeleteEmployee() {
        //when
        try {
            employeeDAO.delete(1);
        } catch (IllegalArgumentException e) {
            System.out.println("No such entity");
        }
        //then
        assertEquals(Optional.empty(),employeeDAO.read(1));
    }

    @AfterEach
    void tearDown() {
        TestEntityManagerFactoryService.close();
    }
}