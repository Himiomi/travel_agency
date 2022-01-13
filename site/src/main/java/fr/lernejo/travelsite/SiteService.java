package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

@Service
public record SiteService(PredictionEngineClient predictionEngineClient) {
    public List<TempDate> getTemperature(String country) throws IOException {
        PrintWriter writer = new PrintWriter("SiteService.txt", StandardCharsets.UTF_8);
        writer.println("Demande de "+country);
        writer.close();
        return predictionEngineClient.getTemperature(country).execute().body().temperatures();
    }
    public ArrayList<PotentialDestinations> getCountries(Weather weatherExpectation, int minimumTemperatureDistance, String userCountry) throws IOException {
        String content = new String(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("countries.txt")).readAllBytes(), StandardCharsets.UTF_8);
        ArrayList<PotentialDestinations> listDest = new ArrayList<>();
        for (Iterator<String> it = content.lines().iterator(); it.hasNext(); ) {
            String country = it.next();
            try {
                Response<listTempOfCountry> response = predictionEngineClient.getTemperature(country).execute();
                double moyTemp = (response.body().temperatures().get(0).temperature() + response.body().temperatures().get(0).temperature()) / 2;
                if (moyTemp >= minimumTemperatureDistance && weatherExpectation.equals(Weather.WARMER))listDest.add(new PotentialDestinations(country, moyTemp));
                else if (moyTemp <= minimumTemperatureDistance && weatherExpectation.equals(Weather.COLDER))listDest.add(new PotentialDestinations(country, moyTemp));
            } catch (Exception ex) {ex.printStackTrace();}
        }
        return listDest;
    }
}
