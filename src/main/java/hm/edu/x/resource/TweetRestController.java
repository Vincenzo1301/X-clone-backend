package hm.edu.x.resource;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.model.Tweet;
import hm.edu.x.service.TweetService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/twitter")
public class TweetRestController {

  private final TweetService tweetService;

  public TweetRestController(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  @PostMapping
  public ResponseEntity<PostTweetResponse> postTweet(@NonNull @RequestBody PostTweetRequest tweet) {
    return ResponseEntity.ok(tweetService.postTweet(tweet));
  }

  @GetMapping
  public ResponseEntity<List<Tweet>> getTweets() {
    return ResponseEntity.ok(tweetService.getTweets());
  }
}
