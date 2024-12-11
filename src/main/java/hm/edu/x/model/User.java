package hm.edu.x.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class User {

  @Id
  private UUID id = UUID.randomUUID();

  private String email;

  private String username;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy = "author")
  private List<Tweet> tweets;
}
