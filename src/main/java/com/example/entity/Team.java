package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tournament", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "tournament_id"))
    private List<Tournament> tournaments;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    List<Player> players;


}
