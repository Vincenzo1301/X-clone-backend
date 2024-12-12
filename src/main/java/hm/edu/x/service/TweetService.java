package hm.edu.x.service;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import java.util.List;
import org.springframework.lang.NonNull;

public interface TweetService {

  PostTweetResponse postTweet(@NonNull PostTweetRequest tweet);

  List<GetTweetResponse> getTweets();
}
