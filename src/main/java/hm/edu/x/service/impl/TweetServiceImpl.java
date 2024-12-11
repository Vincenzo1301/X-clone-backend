package hm.edu.x.service.impl;

import static org.slf4j.LoggerFactory.getLogger;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.exception.BadRequestException;
import hm.edu.x.mapper.TweetMapper;
import hm.edu.x.model.Tweet;
import hm.edu.x.model.User;
import hm.edu.x.persistence.TweetRepository;
import hm.edu.x.service.TweetService;
import hm.edu.x.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

  private static final Logger log = getLogger(TweetServiceImpl.class);

  private final TweetRepository repository;
  private final TweetMapper mapper;
  private final UserService userService;

  public TweetServiceImpl(TweetRepository repository, TweetMapper mapper, UserService userService) {
    this.repository = repository;
    this.mapper = mapper;
    this.userService = userService;
  }

  @Override
  public PostTweetResponse postTweet(@NonNull PostTweetRequest request) {
    UUID authorId = request.authorId();
    String content = request.content();

    Optional<User> optUser = userService.getUserById(authorId);
    if (optUser.isEmpty()) {
      log.error("User with id {} not found while creating tweet.", authorId);
      throw new BadRequestException("Please provide a existing authorId.");
    }

    Tweet tweet = Tweet.builder().author(optUser.get()).content(content).build();
    User user = optUser.get();

    user.addTweet(tweet);
    Tweet savedTweet = repository.save(tweet);

    log.info("Tweet with id {} created by user with id {}", savedTweet.getId(), user.getId());
    return mapper.tweetToPostTweetResponse(savedTweet);
  }

  @Override
  public List<GetTweetResponse> getTweets() {
    return mapper.tweetsToGetTweetResponse(repository.findAll());
  }
}
