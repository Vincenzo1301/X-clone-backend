package hm.edu.x.service.impl;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.exception.BadRequestException;
import hm.edu.x.mapper.TweetMapper;
import hm.edu.x.model.Tweet;
import hm.edu.x.model.User;
import hm.edu.x.persistence.TweetRepository;
import hm.edu.x.service.UserService;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TweetServiceImplTest {

  private static final String MAIL = "example@gmail.com";
  private static final String USERNAME = "example";
  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Doe";
  private static final String TWEET_CONTENT = "Hello, World!";

  @Mock private TweetRepository tweetRepository;
  @Mock private UserService userService;
  @Mock private TweetMapper tweetMapper;

  @InjectMocks private TweetServiceImpl tweetService;

  @Test
  void shouldCreateTweet() {
    User expUser = createUser(MAIL, USERNAME, FIRST_NAME, LAST_NAME);
    Tweet expTweet = createTweet(expUser, TWEET_CONTENT);

    when(userService.getUserById(expUser.getId())).thenReturn(of(expUser));
    when(tweetRepository.save(any())).thenReturn(expTweet);
    when(tweetMapper.tweetToPostTweetResponse(expTweet))
        .thenReturn(
            new PostTweetResponse(expTweet.getId(), expUser.getId(), expTweet.getContent()));

    PostTweetRequest request = new PostTweetRequest(expUser.getId(), TWEET_CONTENT);

    PostTweetResponse response = tweetService.postTweet(request);

    assertEquals(expUser.getId(), response.authorId());
    assertEquals(expTweet.getId(), response.tweetId());
    assertEquals(expTweet.getContent(), response.content());
  }

  @Test
  void shouldThrowBadRequestExceptionWhenUserDoesNotExistDuringTweetCreation() {
    UUID nonExistingUserId = UUID.randomUUID();
    when(userService.getUserById(nonExistingUserId)).thenReturn(empty());
    when(userService.getUserById(nonExistingUserId)).thenReturn(empty());
    PostTweetRequest request = new PostTweetRequest(nonExistingUserId, TWEET_CONTENT);

    assertThrows(BadRequestException.class, () -> tweetService.postTweet(request));
  }

  private User createUser(String email, String username, String firstName, String lastName) {
    return User.builder()
        .email(email)
        .username(username)
        .firstName(firstName)
        .lastName(lastName)
        .build();
  }

  private Tweet createTweet(User user, String content) {
    return Tweet.builder().author(user).content(content).build();
  }
}
