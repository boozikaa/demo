package com.cp.cs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cp.cs.model.User;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public User get(int id) {
    	User patient = entityManager.getReference(User.class, id);
        return patient;
    }

    public User login(String user, String password) {
    	TypedQuery<User> query = entityManager.createQuery("select t from User t where t.username = :username and password = :password", User.class);
    	query.setParameter("username", user);
    	query.setParameter("password", password);
        return  query.getSingleResult();
    }


    public List<User> getAll() {
    	TypedQuery<User> query = entityManager.createQuery("select t from User t",
        		User.class);
        return query.getResultList();
    }


    public void add(User item) {
        entityManager.persist(item);
    }


    public void remove(User item) {
        entityManager.remove(item);
    }

}
