package com.tamimtechnology.redditclone.mapper;

import com.tamimtechnology.redditclone.dto.PostRequest;
import com.tamimtechnology.redditclone.dto.PostResponse;
import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.Subreddit;
import com.tamimtechnology.redditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDTO(Post post);
}
