package fr.lernejo.travelsite.differentsClass;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PotentialDestinations(
    @JsonProperty("country") String country,
    @JsonProperty("temperature") double temperature){
}
