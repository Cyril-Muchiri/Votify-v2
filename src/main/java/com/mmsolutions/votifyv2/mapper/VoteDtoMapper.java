package com.mmsolutions.votifyv2.mapper;

import com.mmsolutions.votifyv2.dto.VoteDto;
import com.mmsolutions.votifyv2.entity.AppUser;
import com.mmsolutions.votifyv2.entity.Candidate;
import com.mmsolutions.votifyv2.entity.Contest;
import com.mmsolutions.votifyv2.entity.Vote;
import com.mmsolutions.votifyv2.repository.VoteRepository;
import org.springframework.stereotype.Component;

@Component
public class VoteDtoMapper {

    VoteRepository voteRepository;

    public VoteDto maptoDto(Vote vote) {
        VoteDto voteDto = new VoteDto();

        return voteDto;
    }
    public Vote maptoEntity(VoteDto voteDto, AppUser appUser, Candidate candidate, Contest contest) {
        Vote voteEntity = new Vote();
        voteEntity.setAppUser(appUser);
        voteEntity.setCandidate(candidate);
        voteEntity.setContest(contest);
        return voteEntity;
    }
}
