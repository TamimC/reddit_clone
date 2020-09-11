package com.tamimtechnology.redditclone.mapper;

import com.tamimtechnology.redditclone.dto.CommentsDTO;
import com.tamimtechnology.redditclone.model.Comment;
import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Comment map(CommentsDTO commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDTO mapToDto(Comment comment);
}