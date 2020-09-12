package pl.sda.dao;

import pl.sda.dto.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
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
    public Optional<Task> read(int id) {
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
    public void update(int id, Task newTask) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task oldTask = entityManager.find(Task.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
