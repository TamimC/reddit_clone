package com.tamimtechnology.redditclone.repositories;

import com.tamimtechnology.redditclone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findSubredditByName(String name);
}
