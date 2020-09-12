package pl.sda.dao;

import pl.sda.dto.Employee;
import pl.sda.dto.Runner;
import pl.sda.dto.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements DAO<Employee>{
    private EntityManagerFactory entityManagerFactory;

    public EmployeeDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean create(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
        } catch (PersistenceException exception) {
            entityManager.getTransaction().rollback();
            System.out.println("Couldn't create a task");
            entityManager.close();
            return false;
        }
        entityManager.close();
        return true;
    }

    @Override
    public Optional<Employee> read(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Optional<Employee> optionalEmployee = Optional.ofNullable(entityManager.find(Employee.class, id));
        entityManager.close();
        return optionalEmployee;
    }

    @Override
    public List<Employee> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        entityManager.close();
        return employees;
    }

    @Override
    public void update(int id, Employee newEmployee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee oldEmployee = entityManager.find(Employee.class, id);
        oldEmployee.setFirstName(newEmployee.getFirstName());
        oldEmployee.setLastName(newEmployee.getLastName());
        oldEmployee.setPosition((newEmployee.getPosition()));
        oldEmployee.setSalary(newEmployee.getSalary());
        oldEmployee.setBirthYear(newEmployee.getBirthYear());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
