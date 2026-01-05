package com.mmsolutions.votifyv2.service;


import com.mmsolutions.votifyv2.dto.VoteDto;
import com.mmsolutions.votifyv2.entity.AppUser;
import com.mmsolutions.votifyv2.entity.Candidate;
import com.mmsolutions.votifyv2.entity.Contest;
import com.mmsolutions.votifyv2.entity.Vote;
import com.mmsolutions.votifyv2.mapper.VoteDtoMapper;
import com.mmsolutions.votifyv2.repository.AppUserRepository;
import com.mmsolutions.votifyv2.repository.CandidateRepository;
import com.mmsolutions.votifyv2.repository.ContestRepository;
import com.mmsolutions.votifyv2.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingService {

   private final VoteDtoMapper voteDtoMapper;
   private final VoteRepository voteRepository;
   private final AppUserRepository appUserRepository;
   private final ContestRepository contestRepository;
   private final CandidateRepository candidateRepository;

    public Vote castVote(VoteDto voteDto) {
        AppUser appUser = appUserRepository.getReferenceById(voteDto.getAppUserId());
        Candidate candidate=candidateRepository.getReferenceById(voteDto.getCandidateId());
        Contest contest=contestRepository.getReferenceById(voteDto.getContestId());

        Vote vote=voteDtoMapper.maptoEntity(voteDto,appUser,candidate,contest);

        return voteRepository.save(vote);
    }

}
