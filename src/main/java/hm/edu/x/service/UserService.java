package hm.edu.x.service;

import hm.edu.x.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.lang.NonNull;

public interface UserService {

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id the UUID of the user to retrieve; must not be null
   * @return an {@code Optional} containing the user if found, or empty if the user does not exist
   */
  Optional<User> getUserById(@NonNull UUID id);
}
