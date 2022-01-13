package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public record SiteService(PredictionEngineClient predictionEngineClient) {
    public ArrayList<PotentialDestinations> potentialCountries(Weather meteo, int minTemp, String currentCountry) throws IOException {
        String content = new String(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("countries.txt")).readAllBytes(), StandardCharsets.UTF_8);
        ArrayList<PotentialDestinations> listDest = new ArrayList<>();
        for (Iterator<String> it = content.lines().iterator(); it.hasNext(); ) {
            String country = it.next();
            try {
                Response<listTempOfCountry> response = predictionEngineClient.getTemperature(country).execute();
                assert response.body() != null;
                double moyTemp = (response.body().temperatures().get(0).temperature() + response.body().temperatures().get(0).temperature()) / 2;
                if (!currentCountry.equals(response.body().country())&& moyTemp > minTemp && meteo.equals(Weather.WARMER))listDest.add(new PotentialDestinations(country, moyTemp));
                else if (!currentCountry.equals(response.body().country())&&moyTemp < minTemp && meteo.equals(Weather.COLDER))listDest.add(new PotentialDestinations(country, moyTemp));
            } catch (Exception ex) {ex.printStackTrace();}
        }
        return listDest;
    }
}
