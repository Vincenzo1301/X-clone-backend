package hm.edu.x.service.impl;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import hm.edu.x.model.User;
import hm.edu.x.persistence.UserRepository;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  private static final String MAIL = "example@gmail.com";
  private static final String USERNAME = "example";
  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Doe";

  @Mock private UserRepository userRepository;

  @InjectMocks private UserServiceImpl userService;

  @Test
  void shouldReturnOptionalWithUser() {
    User expUser = createUser(MAIL, USERNAME, FIRST_NAME, LAST_NAME);
    when(userRepository.findById(expUser.getId())).thenReturn(of(expUser));

    Optional<User> optUser = userService.getUserById(expUser.getId());

    assertTrue(optUser.isPresent());
    assertEquals(expUser, optUser.get());
  }

  private User createUser(String email, String username, String firstName, String lastName) {
    return User.builder()
        .email(email)
        .username(username)
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }
}
