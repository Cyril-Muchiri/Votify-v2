package com.mmsolutions.votifyv2.repository;

import com.mmsolutions.votifyv2.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
