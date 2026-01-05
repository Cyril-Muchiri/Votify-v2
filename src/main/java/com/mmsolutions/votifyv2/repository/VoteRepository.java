package com.mmsolutions.votifyv2.repository;

import com.mmsolutions.votifyv2.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
