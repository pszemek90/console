package pl.sda.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryService{

    protected static String PERSISTENCE_UNIT_NAME = "jpa.hibernate";
    protected static EntityManagerFactory INSTANCE;

    private EntityManagerFactoryService(){}

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
