package dev.bensantosa.runners.run;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface RunRepository extends ListCrudRepository<Run, Integer> {
  List<Run> findAll();

  void create(Run run);

  void update(Run run, Integer id);

  void delete(Integer id);

  void saveAll(List<Run> runs);

  List<Run> findByLocation(String location);

  List<Run> findAllByLocation(String location);

}
