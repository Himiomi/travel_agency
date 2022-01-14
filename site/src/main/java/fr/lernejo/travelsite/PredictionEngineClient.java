package fr.lernejo.travelsite;

import fr.lernejo.travelsite.differentsClass.listTempOfCountry;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("api/temperature")
    Call<listTempOfCountry> getTemperature(@Query("country") String country);
}
