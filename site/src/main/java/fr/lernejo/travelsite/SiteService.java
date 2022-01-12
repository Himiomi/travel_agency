package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Service
public record SiteService(PredictionEngineClient predictionEngineClient) {
    public List<TempDate> getTemperature(String country) throws IOException {
        PrintWriter writer = new PrintWriter("SiteService.txt", StandardCharsets.UTF_8);
        writer.println("Demande de "+country);
        writer.close();
        return
                predictionEngineClient.getTemperature(country).execute().body()
                .temperatures();
    }
}
