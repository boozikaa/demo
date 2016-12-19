package com.cp.cs.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cp.cs.model.User;
import com.cp.cs.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
@CrossOrigin(origins = { "*" })
public class UserApi {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserRepository repository;

	@Autowired
	public UserApi(UserRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public int login(@RequestBody User item) {
		// repository.login(item.getUsername(),
		// passwordEncoder.encode(item.getPassword()));
		return item.getId();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public int add(@RequestBody User item) {
		item.setPassword(passwordEncoder.encode(item.getPassword()));
		repository.add(item);
		return item.getId();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllPatients() {
		return repository.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User get(@PathVariable int id) {
		return repository.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public void updatePatient(@RequestBody User User) {
		User item = repository.get(User.getId());
		item.setUsername(User.getUsername());
		item.setPassword(User.getPassword());
		repository.add(item);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void removePatient(@PathVariable int id) {
		User item = repository.get(id);
		repository.remove(item);
	}
}
