package pl.sda.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestEntityManagerFactoryService{

    protected static String PERSISTENCE_UNIT_NAME = "test-db";
    protected static EntityManagerFactory INSTANCE;

    private TestEntityManagerFactoryService(){}

    public static void start(){
        INSTANCE = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManagerFactory getInstance(){
        return INSTANCE;
    }

    public static void close(){
        INSTANCE.close();
    }
}
