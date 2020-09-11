package com.tamimtechnology.redditclone.services;

import com.tamimtechnology.redditclone.dto.CommentsDTO;
import com.tamimtechnology.redditclone.exceptions.PostNotFoundException;
import com.tamimtechnology.redditclone.mapper.CommentMapper;
import com.tamimtechnology.redditclone.model.Comment;
import com.tamimtechnology.redditclone.model.NotificationEmail;
import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.User;
import com.tamimtechnology.redditclone.repositories.CommentRepository;
import com.tamimtechnology.redditclone.repositories.PostRepository;
import com.tamimtechnology.redditclone.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;

    public void save (CommentsDTO commentsDTO){
        Post post = postRepository.findById(commentsDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDTO.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDTO, post, authService.getCurrentUser());
        commentRepository.save(comment);
        String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDTO> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDTO> getAllCommentsForUser(String userName) {
        User user = userRepository.findUserByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
