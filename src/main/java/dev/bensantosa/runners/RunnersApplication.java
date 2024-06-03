package dev.bensantosa.runners;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.bensantosa.runners.run.Location;
import dev.bensantosa.runners.run.Run;

@SpringBootApplication
public class RunnersApplication {

  private static final Logger logger = LoggerFactory.getLogger(RunnersApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(RunnersApplication.class, args);
    logger.info("Application started successfully");
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5,
          Location.OUTDOOR);
      logger.info("Run: {}", run);
    };
  }

}
