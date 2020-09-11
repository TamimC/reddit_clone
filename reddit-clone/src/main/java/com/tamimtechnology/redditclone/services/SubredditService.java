package com.tamimtechnology.redditclone.services;

import com.tamimtechnology.redditclone.dto.SubredditDTO;
import com.tamimtechnology.redditclone.exceptions.SpringRedditException;
import com.tamimtechnology.redditclone.mapper.SubredditMapper;
import com.tamimtechnology.redditclone.model.Subreddit;
import com.tamimtechnology.redditclone.repositories.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDTO save (SubredditDTO subredditDTO){
        Subreddit save = subredditRepository.save(subredditMapper.mapDTOToSubreddit(subredditDTO));
        subredditDTO.setSubredditId(save.getSubredditId());
        return subredditDTO;
    }

    @Transactional()
    public List<SubredditDTO> getAllSubReddits() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDTO)
                .collect(toList());
    }

    @Transactional
    public SubredditDTO findSubredditByID(Long id){
        return subredditMapper.mapSubredditToDTO(subredditRepository.findById(id).orElse(null));
    }
}
