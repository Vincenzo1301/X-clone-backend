package hm.edu.x.mapper;

import hm.edu.x.data.response.GetTweetResponse;
import hm.edu.x.data.response.PostTweetResponse;
import hm.edu.x.model.Tweet;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TweetMapper {

  @Mapping(target = "tweetId", source = "id")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "authorId", source = "author.id")
  PostTweetResponse tweetToPostTweetResponse(Tweet tweet);

  @Mapping(target = "authorId", source = "author.id")
  @Mapping(target = "tweetId", source = "id")
  @Mapping(target = "content", source = "content")
  GetTweetResponse tweetToGetTweetResponse(Tweet tweet);

  List<GetTweetResponse> tweetsToGetTweetResponse(List<Tweet> tweets);
}
