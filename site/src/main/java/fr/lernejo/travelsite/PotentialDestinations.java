package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PotentialDestinations(@JsonProperty("country") String country,@JsonProperty("temperature")  double temperature){
}
