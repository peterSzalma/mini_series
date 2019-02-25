package com.codecool.mini_series.repository;

import com.codecool.mini_series.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
