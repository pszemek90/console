package pl.sda.dao;

import pl.sda.dto.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements DAO<Employee> {
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
            System.out.println("Couldn't create an employee");
            entityManager.close();
            return false;
        }
        entityManager.close();
        return true;
    }

    @Override
    public Optional<Employee> read(Integer id) {
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
    public void update(Integer id, Employee newEmployee) {
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

    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Employee").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employee searchEmployeeByName(String firstName, String lastName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Employee> query = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        Employee employee = query.getSingleResult();
        entityManager.close();
        return employee;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
