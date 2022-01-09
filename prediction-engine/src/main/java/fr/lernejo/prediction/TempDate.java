package fr.lernejo.prediction;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TempDate( @JsonProperty("date") String date, @JsonProperty("temperature")double temperature) {}
