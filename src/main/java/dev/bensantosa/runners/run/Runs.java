package dev.bensantosa.runners.run;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Runs(@JsonProperty("runs") List<Run> Runs) {

}
