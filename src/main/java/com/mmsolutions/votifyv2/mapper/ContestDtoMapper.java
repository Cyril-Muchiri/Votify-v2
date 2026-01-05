package com.mmsolutions.votifyv2.mapper;

import com.mmsolutions.votifyv2.dto.ContestDto;
import com.mmsolutions.votifyv2.entity.Candidate;
import com.mmsolutions.votifyv2.entity.Contest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContestDtoMapper {

    public Contest mapToContestEntity(ContestDto contestDto) {
        Contest contest = new Contest();
        contest.setContestName(contestDto.getName());
        contest.setContestStatus(contestDto.getStatus());
        contest.setContestType(contestDto.getType());
        contest.setDescription(contestDto.getDescription());
        contest.setStartDate(contestDto.getStartDate());
        contest.setEndDate(contestDto.getEndDate());
        System.out.printf("Heyyyyyyyyyyyy "+contestDto.getCandidates().toString());
        if (contestDto.getCandidates() != null) {
            List<Candidate> candidates = contestDto.getCandidates().stream()
                    .map(dto -> {
                        Candidate c = new Candidate();
                        c.setParticipantName(dto.getCandidateName());
                        c.setContest(contest); // important
                        return c;
                    })
                    .toList();
            contest.setCandidates(candidates);
        }
        return contest;
    }

    public ContestDto mapToContestDto(Contest contest) {
        ContestDto contestDto = new ContestDto();
        contestDto.setName(contest.getContestName());
        contestDto.setStatus(contest.getContestStatus());
        contestDto.setType(contest.getContestType());
        contestDto.setDescription(contest.getDescription());
        contestDto.setStartDate(contest.getStartDate());
        contestDto.setEndDate(contest.getEndDate());
        List<Candidate> candidates = contestDto.getCandidates().stream()
                .map(id -> {
                    Candidate c = new Candidate();
                    c.setContest(contest); // important: set back-reference
                    return c;
                }).toList();

        contest.setCandidates(candidates);
        return contestDto;
    }

}
