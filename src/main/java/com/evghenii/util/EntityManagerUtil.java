package com.evghenii.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class EntityManagerUtil {
    private static final ThreadLocal<EntityManager> THREAD_LOCAL = new ThreadLocal<>();
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("myjpa");


    public static EntityManager getEntityManager() {
        if (Objects.isNull(THREAD_LOCAL.get())) {
            THREAD_LOCAL.set(FACTORY.createEntityManager());
        }

        return THREAD_LOCAL.get();
    }
}
