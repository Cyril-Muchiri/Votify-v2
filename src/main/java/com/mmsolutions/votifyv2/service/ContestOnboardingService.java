package com.mmsolutions.votifyv2.service;

import com.mmsolutions.votifyv2.dto.ContestDto;
import com.mmsolutions.votifyv2.entity.Contest;
import com.mmsolutions.votifyv2.mapper.ContestDtoMapper;
import com.mmsolutions.votifyv2.repository.ContestRepository;
import org.springframework.stereotype.Service;

@Service
public class ContestOnboardingService {

    ContestRepository contestRepository;
    ContestDtoMapper contestDtoMapper;

    public ContestOnboardingService(ContestRepository contestRepository, ContestDtoMapper contestDtoMapper) {
        this.contestRepository = contestRepository;
        this.contestDtoMapper = contestDtoMapper;
    }

    public String createContest(ContestDto contestDto) {
        Contest contest = contestDtoMapper.mapToContestEntity(contestDto);
        contestRepository.save(contest);
        return "Contest with "+contest.getContestName()+" Created Successfully";
    }

}
