package fr.lernejo.prediction;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public record TempDate(@NonNull @JsonProperty("date") String date, @NonNull  double temperature) {
    public TempDate(@NonNull String date,double temperature){
        this.date=date;
        this.temperature=temperature;
    }
}
