package com.mmsolutions.votifyv2.rest;

import com.mmsolutions.votifyv2.dto.VoteDto;
import com.mmsolutions.votifyv2.entity.Vote;
import com.mmsolutions.votifyv2.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VotingRestApi {

    @Autowired
    private VotingService votingService;


    @PostMapping("/cast")
    public ResponseEntity<String> castVote(@RequestBody VoteDto voteDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(votingService.castVote(voteDto) + " Casted Succesfully!!");

    }


}
