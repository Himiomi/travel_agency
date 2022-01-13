package fr.lernejo.prediction;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public record listTempOfCountry(@NonNull @JsonProperty("country") String country,@NonNull @JsonProperty("temperatures")  List<TempDate> temperatures) {
    public listTempOfCountry(@NonNull String country, List<TempDate> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }
}
