package hm.edu.x.model;

import static jakarta.persistence.CascadeType.ALL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
@Table(name = "USERS")
public class User {

  @Id private final UUID id = UUID.randomUUID();

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @OneToMany(mappedBy = "author", cascade = ALL, orphanRemoval = true)
  private final List<Tweet> tweets = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;
    return id.equals(user.id)
        && Objects.equals(email, user.email)
        && Objects.equals(username, user.username)
        && Objects.equals(firstName, user.firstName)
        && Objects.equals(lastName, user.lastName)
        && tweets.equals(user.tweets);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + Objects.hashCode(email);
    result = 31 * result + Objects.hashCode(username);
    result = 31 * result + Objects.hashCode(firstName);
    result = 31 * result + Objects.hashCode(lastName);
    result = 31 * result + tweets.hashCode();
    return result;
  }
}
