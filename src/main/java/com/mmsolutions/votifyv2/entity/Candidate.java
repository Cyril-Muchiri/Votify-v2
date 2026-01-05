package com.mmsolutions.votifyv2.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String participantName;

    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;

    @OneToMany(mappedBy = "candidate")
    private List <Vote> votes;

}
