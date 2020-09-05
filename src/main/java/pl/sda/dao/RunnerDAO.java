package pl.sda.dao;

import pl.sda.dto.Runner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class RunnerDAO implements DAO<Runner>{
    private final String PERSISTENCE_UNIT_NAME;

    public RunnerDAO(String PERSISTENCE_UNIT_NAME) {
        this.PERSISTENCE_UNIT_NAME = PERSISTENCE_UNIT_NAME;
    }

    @Override
    public void create(Runner runner) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(runner);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public Optional<Runner> read(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Optional<Runner> optionalRunner = Optional.ofNullable(entityManager.find(Runner.class, id));
        entityManager.close();
        entityManagerFactory.close();
        return optionalRunner;
    }

    @Override
    public List<Runner> readAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Runner> runners = entityManager.createQuery("SELECT r FROM Runner r", Runner.class).getResultList();
        entityManager.close();
        entityManagerFactory.close();
        return runners;
    }

    @Override
    public void update(int id, Runner newRunner) {//TODO: robić update konkretnych rzeczy
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Runner oldRunner = entityManager.find(Runner.class, id);
        oldRunner.setFirstName(newRunner.getFirstName());
        oldRunner.setLastName(newRunner.getLastName());
        oldRunner.setBestTime(newRunner.getBestTime());
        oldRunner.setCurrentTime(newRunner.getCurrentTime());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delete(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Runner runner = entityManager.find(Runner.class, id);
        entityManager.remove(runner);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
