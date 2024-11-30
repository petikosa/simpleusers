package petikosa.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import petikosa.dtos.UserDto;
import petikosa.entities.User;
import petikosa.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    public UserDto getUserById(long id) {
        return convertToDto(findById(id));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).toList();
    }

    public void createUser(UserDto user) {
        userRepository.save(convertFromDto(user));
    }

    public void updateUser(UserDto user) {
        User existingUser = findById(user.id);
        existingUser.setName(user.name);
        existingUser.setUsername(user.username);
        if (!user.password.equals(existingUser.getPassword())) {
            checkCurrentUser(user.username);
            existingUser.setPassword(user.password);
        }
        userRepository.save(existingUser);
    }

    public void deleteUser(long id) {
        entityManager.remove(findById(id));
    }

    private User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getUsername(), user.getPassword());
    }

    private User convertFromDto(UserDto userDto) {
        return new User(userDto.name, userDto.username, userDto.password);
    }

    private void checkCurrentUser(String username) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String principalUsername = ((UserDetails) principal).getUsername();
            if (!principalUsername.equals(username)) {
                throw new SecurityException("User can modify only his own password");
            }
        } else {
            throw new SecurityException("Could not retrieve current user");
        }
    }
}
