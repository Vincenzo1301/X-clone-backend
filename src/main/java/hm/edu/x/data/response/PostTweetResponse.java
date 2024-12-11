package hm.edu.x.data.response;

import java.util.UUID;
import org.springframework.lang.NonNull;

public record PostTweetResponse(
    @NonNull UUID tweetId, @NonNull UUID authorId, @NonNull String content) {}
