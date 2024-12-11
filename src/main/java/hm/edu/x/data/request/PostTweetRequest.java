package hm.edu.x.data.request;

import java.util.UUID;
import org.springframework.lang.NonNull;

public record PostTweetRequest(@NonNull UUID authorId, @NonNull String content) {}
