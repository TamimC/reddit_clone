package com.tamimtechnology.redditclone.services;

import com.tamimtechnology.redditclone.dto.PostRequest;
import com.tamimtechnology.redditclone.dto.PostResponse;
import com.tamimtechnology.redditclone.exceptions.PostNotFoundException;
import com.tamimtechnology.redditclone.exceptions.SubredditNotFoundException;
import com.tamimtechnology.redditclone.mapper.PostMapper;
import com.tamimtechnology.redditclone.model.Post;
import com.tamimtechnology.redditclone.model.Subreddit;
import com.tamimtechnology.redditclone.model.User;
import com.tamimtechnology.redditclone.repositories.PostRepository;
import com.tamimtechnology.redditclone.repositories.SubredditRepository;
import com.tamimtechnology.redditclone.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public Post save (PostRequest postRequest){
        Subreddit subreddit = subredditRepository.findSubredditByName(postRequest.
                getSubredditName()).orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();
        return postRepository.save(postMapper.map(postRequest, subreddit, currentUser));
    }

    @Transactional()
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDTO(post);
    }

    @Transactional()
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }

    @Transactional()
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubReddit(subreddit);
        return posts.stream().map(postMapper::mapToDTO).collect(toList());
    }

    @Transactional()
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findPostByUser(user)
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }
}
