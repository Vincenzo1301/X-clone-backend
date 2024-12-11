package hm.edu.x.persistence;

import hm.edu.x.model.Tweet;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {}
