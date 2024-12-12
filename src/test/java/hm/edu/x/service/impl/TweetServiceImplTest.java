package hm.edu.x.service.impl;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.exception.BadRequestException;
import hm.edu.x.mapper.TweetMapper;
import hm.edu.x.model.Tweet;
import hm.edu.x.model.User;
import hm.edu.x.persistence.TweetRepository;
import hm.edu.x.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TweetServiceImplTest {

  private static final String MAIL_1 = "example1@gmail.com";
  private static final String MAIL_2 = "example2@gmail.com";
  private static final String USERNAME_1 = "example";
  private static final String USERNAME_2 = "example";
  private static final String FIRST_NAME_1 = "John";
  private static final String FIRST_NAME_2 = "Susan";
  private static final String LAST_NAME_1 = "Doe";
  private static final String LAST_NAME_2 = "Smith";
  private static final String TWEET_CONTENT = "Hello, World!";

  @Mock private TweetRepository tweetRepository;
  @Mock private UserService userService;
  @Mock private TweetMapper tweetMapper;

  @InjectMocks private TweetServiceImpl tweetService;

  @Test
  void shouldCreateTweet() {
    User expUser = createUser(MAIL_1, USERNAME_1, FIRST_NAME_1, LAST_NAME_1);
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
  void shouldThrowBadRequestExceptionWhenNoContentIsProvided() {
    User expUser = createUser(MAIL_1, USERNAME_1, FIRST_NAME_1, LAST_NAME_1);
    PostTweetRequest request = new PostTweetRequest(expUser.getId(), "");

    assertThrows(BadRequestException.class, () -> tweetService.postTweet(request));
  }

  @Test
  void shouldReturnAllExistingTweets() {
    User expUser1 = createUser(MAIL_1, USERNAME_1, FIRST_NAME_1, LAST_NAME_1);
    User expUser2 = createUser(MAIL_2, USERNAME_2, FIRST_NAME_2, LAST_NAME_2);
    Tweet expTweet1 = createTweet(expUser1, TWEET_CONTENT);
    Tweet expTweet2 = createTweet(expUser1, TWEET_CONTENT);
    Tweet expTweet3 = createTweet(expUser1, TWEET_CONTENT);
    Tweet expTweet4 = createTweet(expUser2, TWEET_CONTENT);
    Tweet expTweet5 = createTweet(expUser2, TWEET_CONTENT);

    when(tweetRepository.findAll())
        .thenReturn(List.of(expTweet1, expTweet2, expTweet3, expTweet4, expTweet5));
    when(tweetMapper.tweetsToGetTweetResponse(
            List.of(expTweet1, expTweet2, expTweet3, expTweet4, expTweet5)))
        .thenReturn(
            List.of(
                new GetTweetResponse(expTweet1.getId(), expUser1.getId(), expTweet1.getContent()),
                new GetTweetResponse(expTweet2.getId(), expUser1.getId(), expTweet2.getContent()),
                new GetTweetResponse(expTweet3.getId(), expUser1.getId(), expTweet3.getContent()),
                new GetTweetResponse(expTweet4.getId(), expUser2.getId(), expTweet4.getContent()),
                new GetTweetResponse(expTweet5.getId(), expUser2.getId(), expTweet5.getContent())));

    List<GetTweetResponse> response = tweetService.getTweets();

    assertEquals(5, response.size());
  }

  @Test
  void shouldReturnEmptyListWhenNoTweetsExist() {
    when(tweetRepository.findAll()).thenReturn(emptyList());
    when(tweetMapper.tweetsToGetTweetResponse(emptyList())).thenReturn(emptyList());

    List<GetTweetResponse> response = tweetService.getTweets();

    assertEquals(0, response.size());
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
