package andy.udemy.restfulwebservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import andy.udemy.restfulwebservice.user.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
