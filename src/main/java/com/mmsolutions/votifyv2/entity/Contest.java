package com.mmsolutions.votifyv2.entity;

import com.mmsolutions.votifyv2.enums.ContestType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contestName;
    private String description;

    @Enumerated(EnumType.STRING)
    private ContestType contestType;

    private String contestStatus;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "contest",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Candidate>  candidates;

}
