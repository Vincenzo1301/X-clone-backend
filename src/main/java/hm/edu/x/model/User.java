package hm.edu.x.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

  @Id private final UUID id = UUID.randomUUID();

  private String email;

  private String username;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy = "author")
  private List<Tweet> tweets;
}
