package andy.udemy.restfulwebservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	static {
		users.add(new User(atomicInteger.getAndIncrement(), "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(atomicInteger.getAndIncrement(), "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(atomicInteger.getAndIncrement(), "Jim", LocalDate.now().minusYears(20)));
	}

	public List<User> findAll() {
		return users;
	}

	public Optional<User> findOne(Integer id) {
		return users.stream().filter(e -> id.equals(e.getId())).findFirst();
	}

	public User save(User user) {
		user.setId(atomicInteger.getAndIncrement());
		users.add(user);
		return user;
	}

	public void deleteById(Integer id) {
		users.removeIf(e -> id.equals(e.getId()));
	}

}
