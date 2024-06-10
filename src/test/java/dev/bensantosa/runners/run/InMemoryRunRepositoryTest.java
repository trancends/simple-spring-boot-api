package dev.bensantosa.runners.run;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * InMemoryRunRepositoryTest
 */
public class InMemoryRunRepositoryTest {

  InMemoryRunRepository repository;

  @BeforeEach
  void setUp() {
    repository = new InMemoryRunRepository();
    repository.create(new Run(1, "Monday Morning Run", LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(30), 6, Location.OUTDOOR));
    repository.create(new Run(2, "Wednesday Evening Run", LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(30), 6, Location.OUTDOOR));
  }

  @Test
  void shouldFindAllRuns() {
    List<Run> runs = repository.findAll();
    assertEquals(2, runs.size(), "Should have return 2 runs");
  }
  @Test
  void shouldFindRunWithValidId() {
    var run = repository.findById(1).get();
    assertEquals("Monday Morning Run", run.title());
    assertNotEquals(3, run.miles());
  }

  @Test
  void shouldNotFindRunWithInvalidId() {
    RunNotFoundException notFoundException = assertThrows(
            RunNotFoundException.class,
            () -> repository.findById(3).get()
    );

    assertEquals("Run Not Found", notFoundException.getMessage());
  }

  @Test
  void shouldCreateNewRun() {
    repository.create(new Run(3,
            "Friday Morning Run",
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(30),
            3,
            Location.INDOOR
            ));
    List<Run> runs = repository.findAll();
    assertEquals(3, runs.size());
  }

  @Test
  void shouldUpdateRun() {
    repository.update(new Run(1,
            "Monday Morning Run",
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(30),
            5,
            Location.OUTDOOR), 1);
    var run = repository.findById(1).get();
    assertEquals("Monday Morning Run", run.title());
    assertEquals(5, run.miles());
    assertEquals(Location.OUTDOOR, run.location());
  }

  @Test
  void shouldDeleteRun() {
    repository.delete(1);
    List<Run> runs = repository.findAll();
    assertEquals(1, runs.size());
  }
}
