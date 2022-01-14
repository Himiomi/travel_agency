package fr.lernejo.travelsite.ApiFunction;

import fr.lernejo.travelsite.PredictionEngineClient;
import fr.lernejo.travelsite.differentsClass.PotentialDestinations;
import fr.lernejo.travelsite.differentsClass.Registr;
import fr.lernejo.travelsite.differentsClass.Weather;
import fr.lernejo.travelsite.differentsClass.listTempOfCountry;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public record SiteService(PredictionEngineClient predictionEngineClient) {
    public ArrayList<PotentialDestinations> potentialCountries(Registr currentRegistr) throws IOException {
        Iterator<String> iterate = (new String(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("countries.txt")).readAllBytes(), StandardCharsets.UTF_8)).lines().iterator();;
        ArrayList<PotentialDestinations> listDest = new ArrayList<PotentialDestinations>();
        while(iterate.hasNext()){
            String country = iterate.next();
            try {
                Response<listTempOfCountry> response = predictionEngineClient.getTemperature(country).execute();
                assert response.body() != null;
                double moyTemp = (response.body().temperatures().get(0).temperature() + response.body().temperatures().get(0).temperature()) / 2;
                if (!currentRegistr.userCountry().equals(response.body().country())&& moyTemp >= currentRegistr.minimumTemperatureDistance() && currentRegistr.weatherExpectation().equals(Weather.WARMER))listDest.add(new PotentialDestinations(country, moyTemp));
                else if (!currentRegistr.userCountry().equals(response.body().country())&&moyTemp <= currentRegistr.minimumTemperatureDistance() && currentRegistr.weatherExpectation().equals(Weather.COLDER))listDest.add(new PotentialDestinations(country, moyTemp));
            } catch (Exception ex) {System.out.println(ex.getMessage());}
        }
        return listDest;
    }
}
