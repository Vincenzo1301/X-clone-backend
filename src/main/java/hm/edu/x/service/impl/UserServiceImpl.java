package hm.edu.x.service.impl;

import hm.edu.x.model.User;
import hm.edu.x.persistence.UserRepository;
import hm.edu.x.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> getUserById(@NonNull UUID id) {
    return userRepository.findById(id);
  }
}
