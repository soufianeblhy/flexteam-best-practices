package be.flexteam.flex_imdb;

import be.flexteam.flex_imdb.domain.port.input.ActorServicePort;
import be.flexteam.flex_imdb.domain.port.input.MovieServicePort;
import be.flexteam.flex_imdb.domain.port.output.ActorPersistencePort;
import be.flexteam.flex_imdb.domain.port.output.MoviePersistencePort;
import be.flexteam.flex_imdb.domain.service.ActorServiceImpl;
import be.flexteam.flex_imdb.domain.service.MovieServiceImpl;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class FlexIMDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlexIMDBApplication.class, args);
    }

    //TODO fix problem with IntelliJ (not show schema)
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
//        log.info("H2 tcp server configured");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    @Bean
    public ActorServicePort actorServicePort(ActorPersistencePort actorPersistencePort) {
        return new ActorServiceImpl(actorPersistencePort);
    }

    @Bean
    public MovieServicePort movieServicePort(MoviePersistencePort moviePersistencePort) {
        return new MovieServiceImpl(moviePersistencePort);
    }
}