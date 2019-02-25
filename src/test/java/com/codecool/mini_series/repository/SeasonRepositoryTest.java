package com.codecool.mini_series.repository;

import com.codecool.mini_series.entity.Maker;
import com.codecool.mini_series.entity.Season;
import com.codecool.mini_series.entity.Series;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeasonRepositoryTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    public void seasonIsPersistedWithSeries() {
        Season season = Season.builder()
                .name("2 season")
                .releaseDate(LocalDate.of(2013, 2, 15))
                .build();

        Series series = Series.builder()
                .name("GoT")
                .maker(Maker.HBO)
                .season(season)
                .build();


        seriesRepository.save(series);

        List<Season> seasonList = seasonRepository.findAll();
        assertThat(seasonList)
                .hasSize(1)
                .allMatch(season1 -> season1.getId() > 0L);
    }

}