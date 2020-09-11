package com.tamimtechnology.redditclone.repositories;

import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.Subreddit;
import com.tamimtechnology.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByUser(User user);
    List<Post> findAllBySubReddit(Subreddit subreddit);
}
