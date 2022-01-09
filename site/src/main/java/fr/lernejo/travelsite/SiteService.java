package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public record SiteService(PredictionEngineClient predictionEngineClient) {
    public List<TempDate> getTemperature(String country) throws IOException {
        return Objects.requireNonNull(
                predictionEngineClient.getTemperature(country).execute().body()
                ).temperatures();
    }
}
