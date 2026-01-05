package com.mmsolutions.votifyv2.dto;

import com.mmsolutions.votifyv2.entity.Candidate;
import com.mmsolutions.votifyv2.enums.ContestType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContestDto {
    private String name;
    private String description;

    private ContestType type;

    private String status;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private List<CandidateDto> candidates;
    private List<String> pollOptions;
}
