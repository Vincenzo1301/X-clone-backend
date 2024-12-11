package hm.edu.x.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Tweet {

  @Id private final UUID id = UUID.randomUUID();

  @Column(nullable = false)
  private String content;

  @ManyToOne(optional = false)
  private User author;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tweet tweet = (Tweet) o;
    return id.equals(tweet.id)
        && Objects.equals(content, tweet.content)
        && Objects.equals(author, tweet.author);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + Objects.hashCode(content);
    result = 31 * result + Objects.hashCode(author);
    return result;
  }
}
