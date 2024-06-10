package dev.bensantosa.runners.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRunRepository {

  private  final  static Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);
  private final List<Run> runs = new ArrayList<>();

  List<Run> findAll() {
    return runs;
  }

  Optional<Run> findById(Integer id) {
    return Optional.ofNullable(runs.stream()
            .filter(run -> run.id() == id)
            .findFirst()
            .orElseThrow(RunNotFoundException::new));  }

  void create(Run run) {
    runs.add(run);
  }

  void update(Run run, Integer id) {
    Optional<Run> existingRun = findById(id);
    if(existingRun.isPresent()) {
      var r = existingRun.get();
      log.info("Updating Existing Run: " + existingRun.get());
      runs.set(runs.indexOf(r),run);
    }  }

  void delete(Integer id) {
    runs.removeIf(run -> run.id().equals(id));
  }

  private void init() {
    runs.add(
        new Run(1, "Run 1 ", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 6, Location.INDOOR));
    runs.add(
        new Run(2, "Run 2", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), 10, Location.OUTDOOR));
  }
}
