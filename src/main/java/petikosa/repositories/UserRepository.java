package petikosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petikosa.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
