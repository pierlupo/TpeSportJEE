package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String game;

    @Temporal(TemporalType.DATE)
    private Date tournamentDate;

    @ManyToMany(mappedBy = "tournaments",cascade = CascadeType.ALL)
    private List<Team> teams;


}
