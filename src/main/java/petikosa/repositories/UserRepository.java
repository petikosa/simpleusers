package petikosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petikosa.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
