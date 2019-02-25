package com.codecool.mini_series.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Season {
    @Id
    @GeneratedValue
    private Long id;


    private Integer numberOfEpisodes;

    private String name;

    @Transient
    private LocalDate releaseDate;

    @ManyToOne
    private Series series;

    @Singular
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Episode> episodes;


}
