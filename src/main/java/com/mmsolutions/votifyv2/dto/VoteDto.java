package com.mmsolutions.votifyv2.dto;


import lombok.Data;

@Data
public class VoteDto {

    private Long appUserId;

    private Long contestId;

    private Long candidateId;

}
