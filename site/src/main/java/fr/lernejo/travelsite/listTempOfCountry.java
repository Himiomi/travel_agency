package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record listTempOfCountry(@JsonProperty("country") String country, @JsonProperty("temperatures") List<TempDate> temperatures) {
}
