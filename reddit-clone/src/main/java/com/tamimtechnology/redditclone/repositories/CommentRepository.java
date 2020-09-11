package com.tamimtechnology.redditclone.repositories;

import com.tamimtechnology.redditclone.model.Comment;
import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);

    List<Comment> findByPost(Post post);
}
