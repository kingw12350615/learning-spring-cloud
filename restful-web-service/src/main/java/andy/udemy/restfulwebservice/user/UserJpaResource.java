package andy.udemy.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import andy.udemy.restfulwebservice.jpa.PostRepository;
import andy.udemy.restfulwebservice.jpa.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/jpa/users")
public class UserJpaResource {

	private UserRepository userRepository;

	private PostRepository postRepository;

	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found, id: " + id);
		}

		Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");

		return EntityModel.of(user.get(), link);
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}

	@GetMapping("/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found, id: " + id);
		}

		return user.get().getPosts();
	}

	@PostMapping("/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable Integer id, @RequestBody @Valid Post post) {

		Optional<User> user = userRepository.findById(id);

		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found, id: " + id);
		}

		post.setUser(user.get());

		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedPost.getId())
		.toUri();

		return ResponseEntity.created(location).build();
	}


}
