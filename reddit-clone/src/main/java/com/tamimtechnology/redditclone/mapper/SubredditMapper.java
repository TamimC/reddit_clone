package com.tamimtechnology.redditclone.mapper;

import com.tamimtechnology.redditclone.dto.SubredditDTO;
import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDTO mapSubredditToDTO(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts){
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDTOToSubreddit(SubredditDTO subredditDTO);
}
