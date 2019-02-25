package com.codecool.mini_series;

import com.codecool.mini_series.entity.Episode;
import com.codecool.mini_series.entity.Maker;
import com.codecool.mini_series.entity.Season;
import com.codecool.mini_series.entity.Series;
import com.codecool.mini_series.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class MiniSeriesApplication {

    @Autowired
    private SeriesRepository seriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(MiniSeriesApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Episode redWedding = Episode.builder()
                    .episodeNumberOfSeason(9)
                    .length(59.6)
                    .title("Red Wedding")
                    .actors(Arrays.asList("Emilia Clark", "Kit Harrington", "Peter Dinklage", "Sophie Turner"))
                    .build();
            Episode battleOfTheBastards = Episode.builder()
                    .episodeNumberOfSeason(9)
                    .length(59.6)
                    .title("Battle of the Bastards")
                    .actors(Arrays.asList("Emilia Clark", "Kit Harrington", "Peter Dinklage", "Sophie Turner"))
                    .build();
            Season season = Season.builder()
                    .name("2 season")
                    .numberOfEpisodes(10)
                    .releaseDate(LocalDate.of(2012,2,12))
                    .episode(redWedding)
                    .episode(battleOfTheBastards)
                    .build();
            Series gameOfThrones = Series.builder()
                    .name("Game of Thrones")
                    .maker(Maker.HBO)
                    .releaseDate(LocalDate.of(2011, 4, 17))
                    .season(season)
                    .build();

            redWedding.setSeason(season);
            battleOfTheBastards.setSeason(season);
            season.setSeries(gameOfThrones);

            seriesRepository.save(gameOfThrones);
        };
    }

}
