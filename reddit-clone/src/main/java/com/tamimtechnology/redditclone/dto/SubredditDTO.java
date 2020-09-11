package com.tamimtechnology.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDTO {
    private Long subredditId;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
