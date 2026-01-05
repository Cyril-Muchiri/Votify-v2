package com.mmsolutions.votifyv2.rest;

import com.mmsolutions.votifyv2.dto.ContestDto;
import com.mmsolutions.votifyv2.entity.Contest;
import com.mmsolutions.votifyv2.repository.ContestRepository;
import com.mmsolutions.votifyv2.service.ContestOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contest")
public class ContestRestApi {


    @Autowired
    ContestOnboardingService contestOnboardingService;

//    public ContestRestApi(ContestOnboardingService contestOnboardingService) {
//        this.contestOnboardingService = contestOnboardingService;
//    }

    @PostMapping("/create-contest")
    public ResponseEntity<String> createContest(@RequestBody ContestDto contestDto) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contestOnboardingService.createContest(contestDto));
    }
}
