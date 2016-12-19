package com.cp.cs.repository;

import org.springframework.stereotype.Repository;

import com.cp.cs.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Customer get(int id) {
    	Customer patient = entityManager.getReference(Customer.class, id);
        return patient;
    }


    public List<Customer> getAll() {
        Query query = entityManager.createQuery("select t from Customer t",
        		Customer.class);
        return query.getResultList();
    }


    public void add(Customer item) {
        entityManager.persist(item);
    }


    public void remove(Customer item) {
        entityManager.remove(item);
    }

}
