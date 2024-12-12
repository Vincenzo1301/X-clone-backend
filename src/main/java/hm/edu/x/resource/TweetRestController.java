package hm.edu.x.resource;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import hm.edu.x.data.request.PostTweetRequest;
import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.service.TweetService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<PostTweetResponse> postTweet(@Valid @RequestBody PostTweetRequest tweet) {
    return new ResponseEntity<>(tweetService.postTweet(tweet), CREATED);
  }

  @GetMapping
  public ResponseEntity<List<GetTweetResponse>> getTweets() {
    return new ResponseEntity<>(tweetService.getTweets(), OK);
  }
}
