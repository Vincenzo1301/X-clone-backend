package hm.edu.x.data.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PostTweetRequest(
    @NotNull(message = "Author ID cannot be null") UUID authorId,
    @NotEmpty(message = "Content cannot be null or empty") String content) {}
