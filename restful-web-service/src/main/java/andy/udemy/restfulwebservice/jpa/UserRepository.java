package andy.udemy.restfulwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andy.udemy.restfulwebservice.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
