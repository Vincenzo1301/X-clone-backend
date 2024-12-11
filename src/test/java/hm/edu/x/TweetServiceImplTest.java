package hm.edu.x;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.service.impl.TweetServiceImpl;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class TweetServiceImplTest {

  private final TweetServiceImpl tweetService;

  public TweetServiceImplTest(TweetServiceImpl tweetService) {
    this.tweetService = tweetService;
  }

  @Test
  public void shouldCreateTweet() {
    UUID authorId = UUID.randomUUID();
    String content = "Hello, World!";
    PostTweetRequest request = new PostTweetRequest(authorId, content);

    PostTweetResponse response = tweetService.postTweet(request);

    assertNotNull(response.tweetId());
    assertEquals(request.authorId(), response.authorId());
    assertEquals(request.content(), response.content());
  }
}
