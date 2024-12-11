package hm.edu.x.service.impl;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.model.Tweet;
import hm.edu.x.model.User;
import hm.edu.x.persistence.TweetRepository;
import hm.edu.x.service.TweetService;
import hm.edu.x.service.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final UserService userService;

  public TweetServiceImpl(TweetRepository tweetRepository, UserService userService) {
    this.tweetRepository = tweetRepository;
    this.userService = userService;
  }

  @Override
  public PostTweetResponse postTweet(@NonNull PostTweetRequest request) {
    UUID authorId = request.authorId();
    String content = request.content();

    User user =
        userService
            .getUserById(authorId)
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "User not found")); // TODO: 2021-07-07 07:57:00 - Add proper exception

    Tweet tweet = new Tweet();
    tweet.setAuthor(user);
    tweet.setContent(content);

    Tweet savedTweet = tweetRepository.save(tweet);

    return new PostTweetResponse(
        savedTweet.getId(),
        savedTweet.getAuthor().getId(),
        savedTweet
            .getContent()); // TODO: 2021-07-07 07:57:00 - Use mapper to map entity to response
  }

  @Override
  public List<GetTweetResponse> getTweets() {
    return tweetRepository.findAll().stream()
        .map(
            tweet ->
                new GetTweetResponse(tweet.getId(), tweet.getAuthor().getId(), tweet.getContent()))
        .toList();
  }
}
