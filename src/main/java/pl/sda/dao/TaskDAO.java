package pl.sda.dao;

import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class TaskDAO implements DAO<Task> {
    private EntityManagerFactory entityManagerFactory;

    public TaskDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean create(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
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
    public Optional<Task> read(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Optional<Task> optionalTask = Optional.ofNullable(entityManager.find(Task.class, id));
        entityManager.close();
        return optionalTask;
    }

    @Override
    public List<Task> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Task> tasks = entityManager.createQuery("FROM Task t", Task.class).getResultList();
        entityManager.close();
        return tasks;
    }

    @Override
    public void update(Integer id, Task newTask) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task oldTask = entityManager.find(Task.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Task").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void addEmployeeToTask(Integer employeeId, Integer taskId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, employeeId);
        Task task = entityManager.find(Task.class, taskId);
        List<Employee> employees = task.getEmployees();
        employees.add(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Task> searchTaskByEmployee(Integer employeeId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Task> query = entityManager
                .createQuery("select distinct t from Task t join t.employees employees where employees.id = :id", Task.class);
        query.setParameter("id", employeeId);
        List<Task> tasks = query.getResultList();
        entityManager.close();
        return tasks;
    }
}
