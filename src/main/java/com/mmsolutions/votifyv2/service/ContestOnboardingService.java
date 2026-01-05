package com.mmsolutions.votifyv2.service;

import com.mmsolutions.votifyv2.dto.ContestDto;
import com.mmsolutions.votifyv2.entity.Contest;
import com.mmsolutions.votifyv2.mapper.ContestDtoMapper;
import com.mmsolutions.votifyv2.repository.ContestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestOnboardingService {

    ContestRepository contestRepository;
    ContestDtoMapper contestDtoMapper;

    public String createContest(ContestDto contestDto) {
        Contest contest = contestDtoMapper.mapToContestEntity(contestDto);
        contestRepository.save(contest);
        return "Contest with "+contest.getContestName()+" Created Successfully";
    }

}
