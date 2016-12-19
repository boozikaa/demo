package com.cp.cs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.cp.cs.model.Customer;
import com.cp.cs.repository.CustomerRepository;
import com.cp.cs.repository.PatientRepository;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
@Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
@CrossOrigin(origins={"*"})
public class CustomerApi {

    private CustomerRepository repository;

    @Autowired
    public CustomerApi(CustomerRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public int addPatient(@RequestBody Customer item) {
    	item.getAddress().setCustomer(item);
        repository.add(item);
        return item.getId();
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> getAllPatients() {
        return repository.getAll();
    }


    @RequestMapping(value = "/{hospitalNumber}", method = RequestMethod.GET)
    public Customer get(@PathVariable int id) {
        return repository.get(id);
    }


    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updatePatient(@RequestBody Customer customer) {
    	Customer item = repository.get(customer.getId());
    	item.setCode(customer.getCode());
    	item.setName(customer.getName());
        repository.add(item);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removePatient(@PathVariable int id) {
        Customer item = repository.get(id);
        repository.remove(item);
    }
}
