package dev.bensantosa.runners.run;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.aot.hint.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RunJsonDataLoader
 */
@Component
public class RunJsonDataLoader implements CommandLineRunner {

  private final static Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
  private final JdbcClientRunRepository runRepository;
  private final ObjectMapper objectMapper;

  public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
    this.runRepository = runRepository;
    this.objectMapper = objectMapper;
  }

  @Override
  public void run(String... args) throws Exception {
    if (runRepository.count() == 0) {
      try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
        Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
        log.info("Reading {} runs from JSON data and saving to in-memory collection.", allRuns.Runs().size());
        runRepository.saveAll(allRuns.Runs());
      } catch (IOException e) {
        throw new RuntimeException("Failed to read JSON data", e);
      }
    } else {
      log.info("Not loading Runs from JSON data because the collection contains data.");
    }
  }
}
