package com.codecool.mini_series.repository;

import com.codecool.mini_series.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
