package pl.sda.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.dao.EmployeeDAO;
import pl.sda.dao.TestEntityManagerFactoryService;
import pl.sda.dto.Employee;

import java.util.List;

class EmployeeManagerTest {
    private EmployeeDAO employeeDAO;
    private EmployeeManager employeeManager;

    @BeforeEach
    void setUp() {
        TestEntityManagerFactoryService.start();
        employeeDAO = new EmployeeDAO(TestEntityManagerFactoryService.getInstance());
        employeeManager = new EmployeeManager();
    }

    @Test
    void shouldPrintRunners() {
        //given
        List<Employee> employees = employeeManager.fetchEmployees(employeeDAO);
        //when
        employeeManager.printList(employees);
    }

    @AfterEach
    void tearDown() {
        TestEntityManagerFactoryService.close();
    }
}