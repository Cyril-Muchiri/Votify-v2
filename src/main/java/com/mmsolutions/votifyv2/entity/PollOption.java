package com.mmsolutions.votifyv2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class PollOption {
    @Id
    @GeneratedValue
    private Long id;
    private String optionDescription;

    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;

    @OneToMany
    @JoinColumn(name="pollOption")
    private List<Vote> votes;

}
