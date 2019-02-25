package com.codecool.mini_series.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Episode {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private double length;

    private Integer episodeNumberOfSeason;

    @ElementCollection
    @Singular
    private List<String> actors;

    @ManyToOne
    private Season season;
}
