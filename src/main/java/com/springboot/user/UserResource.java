package com.springboot.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	/**
	 * getAllUsers method is used to fetch all the list of users.
	 * @return List<RegisterU>
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<RegisterU> getAllUsers(){
		return userRepository.findAll();
	}

	/**
	 * retrieveUser method is used to fetch the user details based on the id.
	 * @param long
	 * @return RegisterU
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public RegisterU retrieveUser(@PathVariable long id) {
		Optional<RegisterU> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		return user.get();
	}

	/**
	 * createUser method is used to create the user.
	 * @param RegisterU
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody RegisterU user){
		RegisterU registerUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(registerUser.getId()).toUri();

		if(registerUser.getId() != null){
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(user.getEmail());
			simpleMailMessage.setSubject("Regarding Registration");
			simpleMailMessage.setText("Thank you for registering");
			simpleMailMessage.setFrom("noreply@abcd.com");
	
			emailService.sendEmail(simpleMailMessage);
		}
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * deleteUser method is used to delete a particular user by id.
	 * @param long
	 */
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	/**
	 * updateUser method is used to update the user details based on id.
	 * @param RegisterU
	 * @param long
	 * @return ResponseEntity<Object>
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody RegisterU user, @PathVariable long id) {
		Optional<RegisterU> studentOptional = userRepository.findById(id);
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
}