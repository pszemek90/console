package pl.sda.dao;

import pl.sda.dto.Runner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class RunnerDAO implements DAO<Runner>{
    private EntityManagerFactory entityManagerFactory;

    public RunnerDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(Runner runner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
        entityManager.getTransaction().begin();
        entityManager.persist(runner);
        entityManager.getTransaction().commit();
        }catch (PersistenceException exception){
            entityManager.getTransaction().rollback();
            System.out.println("Couldn't create a user");
        }
        entityManager.close();
    }

    @Override
    public Optional<Runner> read(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Optional<Runner> optionalRunner = Optional.ofNullable(entityManager.find(Runner.class, id));
        entityManager.close();
        return optionalRunner;
    }

    @Override
    public List<Runner> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Runner> runners = entityManager.createQuery("SELECT r FROM Runner r", Runner.class).getResultList();
        entityManager.close();
        return runners;
    }

    @Override
    public void update(int id, Runner newRunner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Runner oldRunner = entityManager.find(Runner.class, id);
        oldRunner.setFirstName(newRunner.getFirstName());
        oldRunner.setLastName(newRunner.getLastName());
        oldRunner.setBestTime(newRunner.getBestTime());
        oldRunner.setCurrentTime(newRunner.getCurrentTime());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Runner runner = entityManager.find(Runner.class, id);
        entityManager.remove(runner);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
