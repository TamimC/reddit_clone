package com.tamimtechnology.redditclone.controllers;

import com.tamimtechnology.redditclone.dto.SubredditDTO;
import com.tamimtechnology.redditclone.model.Subreddit;
import com.tamimtechnology.redditclone.services.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDTO> createSubreddit(@RequestBody SubredditDTO subredditDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubredditDTO>> getAllSubreddits(){
        return new ResponseEntity<List<SubredditDTO>>(subredditService.getAllSubReddits(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubredditByID(@PathVariable Long id){
        if (subredditService.findSubredditByID(id) == null){
            return new ResponseEntity<String>("There exists no subreddit with ID " + id, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<SubredditDTO>(subredditService.findSubredditByID(id), HttpStatus.OK);
        }
    }
}
