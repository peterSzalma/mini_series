package com.codecool.mini_series.repository;

import com.codecool.mini_series.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
