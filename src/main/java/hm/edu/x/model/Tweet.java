package hm.edu.x.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tweet {

  @Id private UUID id = UUID.randomUUID();

  @Column(nullable = false)
  private String content;

  @ManyToOne private User author;
}
