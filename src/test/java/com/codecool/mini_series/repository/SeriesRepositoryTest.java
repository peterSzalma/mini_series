package com.codecool.mini_series.repository;

import com.codecool.mini_series.entity.Maker;
import com.codecool.mini_series.entity.Season;
import com.codecool.mini_series.entity.Series;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeriesRepositoryTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveOneSeries() {
        Series series = Series.builder()
                .name("Dr.Who")
                .releaseDate(LocalDate.of(2005, 5, 6))
                .maker(Maker.AMAZON)
                .build();

        seriesRepository.save(series);

        List<Series> seriesList = seriesRepository.findAll();
        assertThat(seriesList).hasSize(1);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqeFieldTwice() {
        Series series = Series.builder()
                .maker(Maker.AMAZON)
                .name("Got")
                .build();


        seriesRepository.save(series);

        Series series2 = Series.builder()
                .maker(Maker.HBO)
                .name("GoT")
                .build();


        seriesRepository.saveAndFlush(series2);
    }

    @Test
    public void seasonsArePersistedAndDeletedWithNewSeries() {
        Set<Season> seasons = IntStream.range(1, 10)
                .boxed()
                .map(integer -> Season.builder().name("2:" + integer + "season").build())
                .collect(Collectors.toSet());

        Series goT = Series.builder()
                .seasons(seasons)
                .name("GoT")
                .build();

        seriesRepository.save(goT);

        assertThat(seasonRepository.findAll())
                .hasSize(9)
                .anyMatch(season -> season.getName().equals("2:9season"));

        seriesRepository.deleteAll();

        assertThat(seasonRepository.findAll())
                .hasSize(0);
    }

}