package com.mmsolutions.votifyv2.repository;

import com.mmsolutions.votifyv2.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest,Long> {
}
