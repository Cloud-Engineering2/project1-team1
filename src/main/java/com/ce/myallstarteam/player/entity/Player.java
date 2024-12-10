package com.ce.myallstarteam.player.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int id;

    @Column(name = "p_name", length = 100)
    private String name;

    @Column(name = "p_team", length = 50)
    private String teamName;

    @Column(length = 30)
    private String dPosition;

    private int dErrors;

    private double dFieldingPercentage;

    private String dCsp;

    private double hBattingAvg;

    private int hRuns;

    private int hHits;

    private int hHomeRuns;

    private int Era;

    private int pWins;

    private int pSaves;

    private int pWinningPercentage;

    @Column(length = 50)
    private String pHitsAllows;

    private int pHra;

    private int pStrikeOuts;
}
