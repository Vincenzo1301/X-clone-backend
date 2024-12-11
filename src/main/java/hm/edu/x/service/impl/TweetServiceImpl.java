package hm.edu.x.service.impl;

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
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final TweetMapper tweetMapper;
  private final UserService userService;

  public TweetServiceImpl(
      TweetRepository tweetRepository, TweetMapper tweetMapper, UserService userService) {
    this.tweetRepository = tweetRepository;
    this.tweetMapper = tweetMapper;
    this.userService = userService;
  }

  @Override
  public PostTweetResponse postTweet(@NonNull PostTweetRequest request) {
    UUID authorId = request.authorId();
    String content = request.content();

    Optional<User> optUser = userService.getUserById(authorId);
    if (optUser.isEmpty()) throw new BadRequestException("Please provide a existing authorId.");

    Tweet tweet = Tweet.builder().author(optUser.get()).content(content).build();

    Tweet savedTweet = tweetRepository.save(tweet);

    return tweetMapper.tweetToPostTweetResponse(savedTweet);
  }

  @Override
  public List<GetTweetResponse> getTweets() {
    return tweetMapper.tweetsToGetTweetResponse(tweetRepository.findAll());
  }
}
