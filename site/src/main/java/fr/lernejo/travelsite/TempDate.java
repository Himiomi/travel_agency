package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TempDate(@JsonProperty("date")String date, @JsonProperty("temperature")double temperature) {}
