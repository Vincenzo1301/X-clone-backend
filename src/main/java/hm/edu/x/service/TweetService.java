package hm.edu.x.service;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TweetService {

  PostTweetResponse postTweet(@NonNull PostTweetRequest tweet);

  List<GetTweetResponse> getTweets();
}
