package com.borayu.dao.jpa;

import com.borayu.dao.interfaces.IUserEntity;
import com.borayu.exception.ItemAlreadyExistException;
import com.borayu.exception.UnknownItemException;
import com.borayu.model.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.compass.core.Compass;
import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.CompassSearchSession;

public class JpaUserDao implements IUserEntity {

    private final EntityManagerFactory emf;
    private final Compass compass;

    public JpaUserDao(EntityManagerFactory emf, Compass compass) {
        this.emf = emf;
        this.compass = compass;
    }

    public List<UserEntity> searchUser(String query) {
        ArrayList<UserEntity> results = new ArrayList<UserEntity>();

        CompassSearchSession searchSession = compass.openSearchSession();
        try {
            CompassHits hits = searchSession.find(query);
            for (CompassHit compassHit : hits) {
                results.add((UserEntity) compassHit.data());
            }
        } finally {
            searchSession.close();
        }

        return results;
    }

    @Override
    public void add(UserEntity user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            if (em.find(UserEntity.class, user.getId()) != null) {
                throw new ItemAlreadyExistException(user);
            }

            em.persist(user);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Override
    public void remove(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            UserEntity user = em.find(UserEntity.class, id);
            if (user != null) {
                em.remove(user);
                em.getTransaction().commit();
            } else {
                throw new UnknownItemException(UserEntity.class, id);
            }
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @Override
    public UserEntity get(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            UserEntity user = em.find(UserEntity.class, id);
            if (user == null) {
                throw new UnknownItemException(UserEntity.class, id);
            }
            return user;
        } finally {
            em.close();
        }
    }

    @Override
    public List<UserEntity> getList() {
        List<UserEntity> users = new ArrayList<UserEntity>();

        EntityManager em = emf.createEntityManager();
        try {
            users.addAll(em.createQuery("SELECT s FROM user s").getResultList());
        } finally {
            em.close();
        }

        return users;
    }

    @Override
    public void update(UserEntity user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

        } catch (IllegalArgumentException e) {
            throw new UnknownItemException(UserEntity.class, user.getId());
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

}
